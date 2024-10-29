package br.com.fiap.upstyle.controller;

import br.com.fiap.upstyle.model.AppUser;
import br.com.fiap.upstyle.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @PostMapping
    public String registerUser(@ModelAttribute AppUser user, Model model, HttpServletRequest request) throws Exception {
        appUserService.registerUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute AppUser user){
        appUserService.edit(id, user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        appUserService.delete(id);
        return "redirect:/admin";
    }
}
