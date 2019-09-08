package pl.sda.rentcar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.rentcar.dtos.CarDTO;
import pl.sda.rentcar.dtos.HireDTO;
import pl.sda.rentcar.entity.CarEntity;
import pl.sda.rentcar.entity.HireEntity;
import pl.sda.rentcar.repository.HireRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HireService {
    private final HireRepository repository;

    @Autowired
    public HireService(HireRepository repository) {
        this.repository = repository;
    }

    public HireDTO add(HireDTO dto) {
        HireEntity entity = mapToEntity(dto);
        repository.save(entity);
        return mapToDTO(entity);
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
        repository.deleteById(id);
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
