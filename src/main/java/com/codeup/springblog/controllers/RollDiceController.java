package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String getRollDicePage(){
        return "rolldice";
    }

    @GetMapping("/roll-dice/{num}")
    public String getGuess(@PathVariable int num, Model model) {
        int randNum = new Random().nextInt(6 - 1 + 1) + 1;
        String message;
        if (randNum == num) {
            message = "Good guess! You win!";
        } else {
            message = "Better luck next time.";
        }
        model.addAttribute("message", message);
        model.addAttribute("num", num);
        model.addAttribute("randNum", randNum);
        return "rolldice";
    }
}
