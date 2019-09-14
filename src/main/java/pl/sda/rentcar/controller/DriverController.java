package pl.sda.rentcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.rentcar.dtos.DriverDTO;
import pl.sda.rentcar.entity.Driver;
import pl.sda.rentcar.repository.DriverRepository;
import pl.sda.rentcar.service.DriverService;

import java.util.List;


@RequestMapping("/driver")
@RestController
public class DriverController {


    private final DriverService driverService;
    private final DriverRepository driverRepository;

    @Autowired
    public DriverController(DriverService driverService, DriverRepository driverRepository) {
        this.driverService = driverService;
        this.driverRepository = driverRepository;
    }

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
