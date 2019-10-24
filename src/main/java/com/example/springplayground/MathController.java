package com.example.springplayground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/math")
public class MathController {

    @Autowired
    private MathHelper mathHelper;

    @GetMapping("/pi")
    public String pi() {
        return "3.141592653589793";
    }

    @GetMapping("/calculate")
    public String calculate(
            @RequestParam(required = false, defaultValue = "add") String operation,
            @RequestParam int x,
            @RequestParam int y
    ) {
        return String.valueOf((int) mathHelper.calculate(operation, x, y));
    }

    @GetMapping("/sum")
    public String sum(
            @RequestParam(value = "n", required = false) List<Double> numbers
    ) {
        return String.valueOf((int) mathHelper.sum(numbers));
    }

    @RequestMapping("/volume/{x}/{y}/{z}")
    public String volume(
            @PathVariable int x,
            @PathVariable int y,
            @PathVariable int z
    ) {
        return String.valueOf(mathHelper.volume(x, y, z));
    }
}
