package pl.sda.rentcar.controller.web;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.rentcar.dtos.HireDTO;
import pl.sda.rentcar.service.HireService;

@Controller
@RequestMapping("/hire")
@RequiredArgsConstructor
public class HireController {
    private final HireService service;

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
