package pl.sda.rentcar.controller.web;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.rentcar.dtos.CarDTO;
import pl.sda.rentcar.service.CarService;

@Controller
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarListController {
    //private final static Logger LOGGER = LoggerFactory.getLogger(CarListController.class);
    private final CarService carService;

    @RequestMapping("/list")
    ModelAndView getCars() {
        ModelAndView modelAndView = new ModelAndView("/carlist.html");
        modelAndView.addObject("cars", carService.getAll());
        return modelAndView;
    }

    @GetMapping("/create")
    ModelAndView createCarView() {
        ModelAndView modelAndView = new ModelAndView("/createcar.html");
        modelAndView.addObject("car", new CarDTO());
        return modelAndView;
    }

    @PostMapping("/create")
    String createCar(CarDTO dto) {
        carService.add(dto);
        return "redirect:/";
    }
}
