package com.dihardmg.kayrawanapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author : Otorus
 * @since : 1/4/18
 */
@Controller
public class LoginContrller {

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }
}
