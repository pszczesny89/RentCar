package pl.sda.rentcar.controller.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.rentcar.dtos.HireDTO;
import pl.sda.rentcar.entity.CarEntity;
import pl.sda.rentcar.entity.Driver;
import pl.sda.rentcar.repository.CarRepository;
import pl.sda.rentcar.repository.DriverRepository;
import pl.sda.rentcar.repository.HireRepository;
import pl.sda.rentcar.service.HireService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hire")
@RequiredArgsConstructor
public class HireController {
    private final HireService service;
    private final HireRepository repository;
    private final CarRepository carRepository;
    private final DriverRepository driverRepository;

    @GetMapping
    public List<HireDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/create")
    ModelAndView createCarView() {
        ModelAndView modelAndView = new ModelAndView("/createhire.html");
        modelAndView.addObject("hire", new HireDTO());
        modelAndView.addObject("cars", carRepository.findAll().stream().filter(CarEntity::isAvailable).collect(Collectors.toList()));
        modelAndView.addObject("drivers", driverRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/create")
    String createCar(HireDTO dto) {
        service.add(dto);
        return "redirect:/";
    }

    @GetMapping("/return")
    String returnCar(@RequestParam Long id) {
        service.removeHire(id);
        return "redirect:/";
    }
}
