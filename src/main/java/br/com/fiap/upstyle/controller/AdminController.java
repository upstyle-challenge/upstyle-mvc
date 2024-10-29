package br.com.fiap.upstyle.controller;

import br.com.fiap.upstyle.service.AppUserService;
import br.com.fiap.upstyle.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AppUserService appUserService;

    @GetMapping
    public String adminPage(Model model){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("users", appUserService.findAll());

        model.addAttribute("userCount", productService.totalCount());
        model.addAttribute("productCount", appUserService.totalCount());

        return "admin";
    }
}
