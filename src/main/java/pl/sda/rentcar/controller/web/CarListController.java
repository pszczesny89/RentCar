package pl.sda.rentcar.controller.web;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.rentcar.dtos.CarDTO;
import pl.sda.rentcar.service.CarService;
import pl.sda.rentcar.service.finder.CarFinder;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarListController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CarListController.class);
    private final CarService carService;
    private final CarFinder finder;

    @RequestMapping("/list")
    ModelAndView getCars() {
        ModelAndView modelAndView = new ModelAndView("/carlist.html");
        modelAndView.addObject("cars", carService.getAll());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    ModelAndView createCarView() {
        ModelAndView modelAndView = new ModelAndView("/createcar.html");
        modelAndView.addObject("car", new CarDTO());
        return modelAndView;
    }

    @PostMapping("/create")
    String createCar(@ModelAttribute CarDTO dto) {
        carService.addOrUpdate(dto);
        return "redirect:/";
    }

    @RequestMapping("/car/{id}")
    ModelAndView getCar(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/car.html");
        modelAndView.addObject("car", carService.getOne(id));
        return modelAndView;
    }

    @GetMapping("/delete")
    String deleteCar(@RequestParam Long id) {
        carService.removeCar(id);
        return "redirect:/";
    }

    @GetMapping("/edit")
    ModelAndView editCar(@RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("createcar.html");
        modelAndView.addObject("car", finder.findById(id));
        return modelAndView;
    }
}
