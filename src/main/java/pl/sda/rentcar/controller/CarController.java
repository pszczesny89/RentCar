package pl.sda.rentcar.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.rentcar.repository.CarRepository;
import pl.sda.rentcar.service.CarService;

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
}
