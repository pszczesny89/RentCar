package pl.sda.rentcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.rentcar.dtos.DriverDTO;
import pl.sda.rentcar.entity.Driver;
import pl.sda.rentcar.service.DriverService;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public Driver add(@RequestBody DriverDTO dto) {return driverService.save(dto);}
}
