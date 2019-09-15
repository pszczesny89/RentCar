package pl.sda.rentcar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.rentcar.dtos.HireDTO;
import pl.sda.rentcar.entity.HireEntity;
import pl.sda.rentcar.exceptions.HireDTOException;
import pl.sda.rentcar.repository.CarRepository;
import pl.sda.rentcar.repository.DriverRepository;
import pl.sda.rentcar.repository.HireRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HireService {
    private final HireRepository repository;
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public HireService(HireRepository repository, CarRepository carRepository, DriverRepository driverRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
        this.driverRepository = driverRepository;
    }

    public void add(HireDTO dto) {
        /*if(validate(dto)) {*/
            HireEntity entity = HireEntity.builder()
                    .id(dto.getId())
                    .carId(dto.getCarId())
                    .driverId(dto.getDriverId())
                    .hireDate(dto.getHireDate())
                    .returnDate(dto.getReturnDate())
                    .mileage(dto.getMileage())
                    .build();
            carRepository.findById(dto.getCarId()).ifPresent(e->e.setAvailable(false));
            repository.save(entity);

        /*} else {
            throw new HireDTOException();
        }*/
    }

    private boolean validate(HireDTO dto) {
        if (dto.getHireDate().isBefore(LocalDate.now())) {
            return false;
        } else return dto.getHireDate().isAfter(dto.getReturnDate());
     }

    public List<HireDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(e -> new HireDTO(e.getId(), e.getCarId(), e.getDriverId(), e.getHireDate(), e.getReturnDate(), e.getMileage()))
                .collect(Collectors.toList());
    }

    public HireDTO getOne(Long id) {
        return mapToDTO(repository.getById(id));
    }

    public void removeHire(Long id) {
        carRepository.getById(id).setAvailable(true);
        HireEntity entity = repository.findAll().stream().filter(e->e.getCarId().equals(id)).findFirst().get();
        repository.deleteById(entity.getId());
    }

    public Integer getPrice(HireDTO dto) {
        int pricePerDay = carRepository.getById(dto.getCarId()).getPrice();
        return (int) ChronoUnit.DAYS.between(dto.getHireDate(), dto.getReturnDate())*pricePerDay;
    }


    private HireDTO mapToDTO(HireEntity hireEntity) {
        return new HireDTO(
                hireEntity.getId(),
                hireEntity.getCarId(),
                hireEntity.getDriverId(),
                hireEntity.getHireDate(),
                hireEntity.getReturnDate(),
                hireEntity.getMileage()
        );
    }

    private HireEntity mapToEntity(HireDTO hireDTO) {
        return new HireEntity(
               hireDTO.getId(),
               hireDTO.getCarId(),
               hireDTO.getDriverId(),
               hireDTO.getHireDate(),
               hireDTO.getReturnDate(),
               hireDTO.getMileage()
        );
    }
}