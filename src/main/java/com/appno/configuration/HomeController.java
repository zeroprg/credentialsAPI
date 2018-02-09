package com.appno.configuration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home redirection to swagger api documentation 
 */

@Controller
public class HomeController {

    @RequestMapping("/register-demo")
    String login() {
        return "register-demo";
    }
	@RequestMapping(value = "/")
	public String index() {
		System.out.println("swagger-ui.html");
		return "redirect:swagger-ui.html";
	}
	
/*	@RequestMapping(value = "/login")
	public String login() {
		System.out.println("login.html");
		return "redirect:templates/home.html";
	}
*/
}
