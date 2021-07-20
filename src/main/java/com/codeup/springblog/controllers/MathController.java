package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public String addNumber(@PathVariable int num1, @PathVariable int num2) {
        return String.valueOf(num1 + num2);
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public String subtractNumber(@PathVariable int num1, @PathVariable int num2) {
        return String.valueOf(num2 - num1);
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public String multiplyNumber(@PathVariable int num1, @PathVariable int num2) {
        return String.valueOf(num1 * num2);
    }

    @GetMapping("/divide/{num1}/by/{num2}")
    @ResponseBody
    public String divideNumber(@PathVariable int num1, @PathVariable int num2) {
        return String.valueOf(num1 / num2);
    }
}
