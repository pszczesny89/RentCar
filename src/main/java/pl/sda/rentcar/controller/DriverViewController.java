package pl.sda.rentcar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.rentcar.dtos.DriverDTO;
import pl.sda.rentcar.service.DriverFinder;
import pl.sda.rentcar.service.DriverService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverViewController {

    private final DriverService driverService;
    private final DriverFinder driverFinder;

    @RequestMapping
    ModelAndView driverView(){
    ModelAndView mav = new ModelAndView("drivers.html");
    mav.addObject("drivers", driverService.getAll());
    return mav;
    }

    @GetMapping("/create")
    ModelAndView createDriverView() {
        ModelAndView modelAndView = new ModelAndView("createDriver.html");
        modelAndView.addObject("driver", new DriverDTO());
        return modelAndView;
    }

    @PostMapping("/create")
    String createDriver(@ModelAttribute DriverDTO driver) {
        driverService.save(driver);

        return "redirect:/driver";
    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/edit")
    ModelAndView editDriver(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("createDriver.html");
        modelAndView.addObject("driver", driverFinder.findById(id));
        return modelAndView;
    }

    @GetMapping("/delete")
    String deleteDriver(@RequestParam Long id) {
        driverService.remove(id);
        return "redirect:/driver";
    }
}
