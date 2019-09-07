package pl.sda.rentcar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.rentcar.dtos.CarDTO;
import pl.sda.rentcar.entity.CarEntity;
import pl.sda.rentcar.repository.CarRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class CarService {
    private final CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    private CarDTO mapToDTO(CarEntity carEntity) {
        return new CarDTO(
                carEntity.getId(),
                carEntity.getBrand(),
                carEntity.getModel(),
                carEntity.getMileage(),
                carEntity.isAvailable()
        );
    }

    private CarEntity mapToEntity(CarDTO carDTO) {
        return new CarEntity(
                carDTO.getId(),
                carDTO.getBrand(),
                carDTO.getModel(),
                carDTO.getMileage(),
                carDTO.isAvailable()
        );
    }
}
