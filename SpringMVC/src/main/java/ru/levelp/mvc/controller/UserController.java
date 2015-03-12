package ru.levelp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelp.mvc.forms.LoginForm;

/**
 *
 */
@Controller
@RequestMapping("/")
public class UserController {
    @RequestMapping(value = "/login")
    public String login(ModelMap model,
                        @RequestParam(value = "login", required = false) String login,
                        @RequestParam(value = "password", required = false) String password) {

        LoginForm loginForm = new LoginForm();
        if (loginForm.tryLogin(login, password))
            return "success";
        else
            return "login";
    }
}
