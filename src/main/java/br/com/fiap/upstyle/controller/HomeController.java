package br.com.fiap.upstyle.controller;

import br.com.fiap.upstyle.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public String homePage(Model model, HttpSession httpSession){
        String userName = (String) httpSession.getAttribute("firstName");
        model.addAttribute("userFirstName", userName);

        model.addAttribute("products", productService.findAll());
        return "home";
    }
}
