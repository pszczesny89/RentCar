package pl.sda.rentcar.controller;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.rentcar.service.CarService;

@Controller
@RequiredArgsConstructor
public class CarListController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CarListController.class);

    private final CarService carService;

    @RequestMapping
    ModelAndView getCars() {
        ModelAndView modelAndView = new ModelAndView("carlist.html");
        modelAndView.addObject("cars", carService.getAll());
        return modelAndView;
    }
}
