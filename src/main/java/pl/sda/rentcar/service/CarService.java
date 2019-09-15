package pl.sda.rentcar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.rentcar.dtos.CarDTO;
import pl.sda.rentcar.entity.CarEntity;
import pl.sda.rentcar.exceptions.HireDTOException;
import pl.sda.rentcar.repository.CarRepository;

import javax.transaction.Transactional;
import java.awt.*;
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

    public void addOrUpdate(CarDTO carDTO) {
            CarEntity entity = CarEntity.builder()
                    .id(carDTO.getId())
                    .brand(carDTO.getBrand())
                    .model(carDTO.getModel())
                    .registration(carDTO.getRegistration())
                    .mileage(carDTO.getMileage())
                    .price(carDTO.getPrice())
                    .isAvailable(carDTO.isAvailable())
                    .build();
            repository.save(entity);
    }

    public boolean validate(CarDTO dto) {
        return dto.getBrand() != null && dto.getModel() != null && dto.getRegistration() != null && dto.getMileage() != null && dto.getPrice() != null;
    }

    public void returnCar(Long id) {
        repository.findById(id).ifPresent(e->e.setAvailable(true));
    }

    public List<CarDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(e -> new CarDTO(e.getId(), e.getBrand(), e.getModel(), e.getRegistration(), e.getMileage(), e.getPrice(), e.isAvailable()))
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
                carEntity.getPrice(),
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
                carDTO.getPrice(),
                carDTO.isAvailable()
        );
    }
}
