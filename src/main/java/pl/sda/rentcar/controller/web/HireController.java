package pl.sda.rentcar.controller.web;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.rentcar.dtos.HireDTO;
import pl.sda.rentcar.repository.HireRepository;
import pl.sda.rentcar.service.HireService;

import java.util.List;

@RestController
@RequestMapping("/hire")
@RequiredArgsConstructor
public class HireController {
    private final HireService service;
    private final HireRepository repository;

    @GetMapping
    public List<HireDTO> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/create")
    ModelAndView createCarView() {
        ModelAndView modelAndView = new ModelAndView("/createhire.html");
        modelAndView.addObject("hire", new HireDTO());
        return modelAndView;
    }

    @PostMapping("/create")
    String createCar(HireDTO dto) {
        service.add(dto);
        return "redirect:/";
    }
}
