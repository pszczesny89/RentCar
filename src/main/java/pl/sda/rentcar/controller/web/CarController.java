package pl.sda.rentcar.controller.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.sda.rentcar.dtos.CarDTO;
import pl.sda.rentcar.repository.CarRepository;
import pl.sda.rentcar.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService service;
    private final CarRepository repository;

    @Autowired
    public CarController(CarService service, CarRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PutMapping()
    public void add(@RequestBody CarDTO dto) {
        service.addOrUpdate(dto);
    }

    @GetMapping()
    public List<CarDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public CarDTO getOne(@PathVariable("id") Long id) {
        return service.getOne(id);
    }

    @DeleteMapping("/{id}")
    public void removeOne(@PathVariable("id") Long id) {
        service.removeCar(id);
    }
}
