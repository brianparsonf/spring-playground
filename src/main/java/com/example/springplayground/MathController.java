package com.example.springplayground;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestParam(value="n", required=false) List<Double> numbers
    ) {
        return String.valueOf((int) mathHelper.sum(numbers));
    }
}
