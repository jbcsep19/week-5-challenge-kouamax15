package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    GuestbookRepository guestbookRepository;

//    starting mapping with homepage
    @GetMapping("/")
    public String homepage(){
        return "show";
    }

    @RequestMapping("/")
    public String listForm(Model model){
        model.addAttribute("guests", guestbookRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model){
        model.addAttribute("guest", new Guestbook());
        return "guestsignform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Guestbook guestbook, BindingResult result){
        if(result.hasErrors()){
            return "guestsignform";
        }
//        if result has no errors then the redirect link should point to the signupform
        guestbookRepository.save(guestbook);
        return "redirect:/";
    }
}
