package br.com.fiap.upstyle.controller;

import br.com.fiap.upstyle.model.Product;
import br.com.fiap.upstyle.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public String saveProduct(@ModelAttribute Product product){
        productService.save(product);
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String saveProduct(@PathVariable Long id, @ModelAttribute Product product){
        productService.edit(id, product);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/admin";
    }
}
