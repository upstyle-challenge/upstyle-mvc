package br.com.fiap.upstyle.controller;

import br.com.fiap.upstyle.config.RabbitMqConfig;
import br.com.fiap.upstyle.model.AppUser;
import br.com.fiap.upstyle.model.Product;
import br.com.fiap.upstyle.service.OllamaService;
import br.com.fiap.upstyle.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OllamaService ollamaService;

    @Autowired
    private RabbitMqConfig mqConfig;

    @GetMapping
    public String homePage(Model model, HttpSession httpSession){
        AppUser user = (AppUser) httpSession.getAttribute("user");
        model.addAttribute("userFirstName", user.getFirstName());

        try{
            Product mainProduct = ollamaService.analisarCliente(user);
            model.addAttribute("featuredProduct", mainProduct);
            model.addAttribute("products", productService.findAll());

            return "home";
        }catch (Exception e){
            return "home";
        }

    }
}
