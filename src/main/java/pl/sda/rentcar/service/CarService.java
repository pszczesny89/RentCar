package pl.sda.rentcar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.rentcar.dtos.CarDTO;
import pl.sda.rentcar.entity.CarEntity;
import pl.sda.rentcar.repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarService {
    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public CarDTO add(CarDTO carDTO) {
        CarEntity entity = mapToEntity(carDTO);
        repository.save(entity);
        return mapToDTO(entity);
    }

    public List<CarDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(e -> new CarDTO(e.getId(), e.getBrand(), e.getModel(), e.getRegistration(), e.getMileage(), e.isAvailable()))
                .collect(Collectors.toList());
    }

    public CarDTO getOne(Long id) {
        return mapToDTO(repository.getById(id));
    }

    public void removeCar(Long id) {
        repository.deleteById(id);
    }

    private CarDTO mapToDTO(CarEntity carEntity) {
        return new CarDTO(
                carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getModel(),
                carEntity.getRegistration(),
                carEntity.getMileage(),
                carEntity.isAvailable()
        );
    }

    private CarEntity mapToEntity(CarDTO carDTO) {
        return new CarEntity(
                carDTO.getId(),
                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getRegistration(),
                carDTO.getMileage(),
                carDTO.isAvailable()
        );
    }
}
