package pl.sda.rentcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.rentcar.dtos.DriverDTO;
import pl.sda.rentcar.entity.Driver;
import pl.sda.rentcar.service.DriverService;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public Driver add(@RequestBody DriverDTO dto) {return driverService.save(dto);}

    @GetMapping
    public List<DriverDTO> getAll() {return driverService.getAll();}

    @GetMapping("/{id}")
    public Driver getOne(@PathVariable("id") Long id){
    return driverService.getOne(id);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id){driverService.remove(id);}

}
