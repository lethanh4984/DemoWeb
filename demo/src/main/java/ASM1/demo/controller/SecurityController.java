package ASM1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String login(){
        System.out.println("Dang o login hang 11");
        return "public/login";
    }

    @GetMapping("/deny")
    public String denyPage(){
        System.out.println("dang bi deny");
        return "public/access_deny";
    }
}
