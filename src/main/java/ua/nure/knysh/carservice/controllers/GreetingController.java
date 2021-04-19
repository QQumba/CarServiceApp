package ua.nure.knysh.carservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.nure.knysh.carservice.entities.Car;
import ua.nure.knysh.carservice.services.CarService;

import java.util.List;

@Controller
@RequestMapping("/")
public class GreetingController {
    private final CarService carService;

    public GreetingController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String index(Model model){
        List<Car> cars = carService.getAll();
        model.addAttribute("cars", cars);
        return "cars";
    }
}
