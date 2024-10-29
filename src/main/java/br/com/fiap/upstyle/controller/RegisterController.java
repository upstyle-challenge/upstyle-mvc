package br.com.fiap.upstyle.controller;

import br.com.fiap.upstyle.model.AppUser;
import br.com.fiap.upstyle.service.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public String registerPage(){
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute AppUser user, Model model, HttpServletRequest request) throws Exception {
        try {
            AppUser appUser = appUserService.registerUser(user);
            System.out.println(appUser);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            HttpSession session = request.getSession(true);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            System.out.println("Usuário autenticado: " + appUser.getUsername());

            return "redirect:/home";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "redirect:/register";
        }
    }
}
