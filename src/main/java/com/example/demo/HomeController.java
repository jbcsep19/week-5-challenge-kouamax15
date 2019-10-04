package com.example.demo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    GuestbookRepository guestbookRepository;

////    starting mapping with homepage
//    @RequestMapping("/")
//    public String homepage(){
//        return "homepage";
//    }

    @RequestMapping("/list")
    public String listForm(Model model){
        model.addAttribute("guests", guestbookRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String jobForm(Model model){
        model.addAttribute("guest", new Guest());
        return "guestform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Guest guest, BindingResult result){
        if(result.hasErrors()){
            return "guestform";
        }
//        if result has no errors then the redirect link should point to the guest(sign up)form
        guestbookRepository.save(guest);
        return "redirect:/list";
    }
    @RequestMapping("/detail/{id}")
    public String showGuest(@PathVariable("id") long id, Model model){
        model.addAttribute("guest", guestbookRepository.findById(id).get());
        return "homepage";
    }
    @RequestMapping("/update/{id}")
    public String updateGuest(@PathVariable("id") long id, Model model){
        model.addAttribute("guest", guestbookRepository.findById(id).get());
        return "guestform";
    }
    @RequestMapping("/delete/{id}")
    public String deleteGuest(@PathVariable("id") long id){
        guestbookRepository.deleteById(id);
        return "redirect:/list";
    }
}
