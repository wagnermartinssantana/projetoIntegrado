package br.com.projetointegrado.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "/index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    
    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username, @RequestParam String password, Model model) {
        if ("cliente@gmail.com".equals(username) && "123456".equals(password)) {
            return "cliente";
        } else if ("adm@gmail.com".equals(username) && "123456".equals(password)) {
            return "admin";
        } else {
            model.addAttribute("loginError", "Usuário ou senha incorretos");
            return "index";
        }
    }
    
    @GetMapping("/cliente")
    public String cliente() {
    	return "/cliente";
    }
    
    @GetMapping("/admin")
    public String admin() {
    	return "/admin";
    }
    
    @GetMapping("/historico")
    public String historico() {
    	return "/historico-cliente";
    }
    
    @GetMapping("/historico-servicos")
    public String historicoServicos() {
    	return "/historico-admin";
    }
    
    @GetMapping("/avaliacao")
    public String avaliacao() {
    	return "/avaliacao";
    }
    
    @GetMapping("/agendar")
    public String agendar() {
    	return "/agendar";
    }
    
}