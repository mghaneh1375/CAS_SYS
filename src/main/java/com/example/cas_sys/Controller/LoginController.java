package com.example.cas_sys.Controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/oauth")
public class LoginController {

    @GetMapping("/login")
    @ResponseBody
    public String get() {
        return "ok";
    }

}
