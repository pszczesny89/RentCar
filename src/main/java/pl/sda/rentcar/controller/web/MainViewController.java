package pl.sda.rentcar.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class MainViewController {
    @RequestMapping("/")
    ModelAndView mainView() {
        ModelAndView mav = new ModelAndView("index.html");
        return mav;
    }
}
