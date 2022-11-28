package org.isj.ing4.isi.music.presentation.controller;

import org.isj.ing4.isi.music.dto.UserDto;
import org.isj.ing4.isi.music.model.User;
import org.isj.ing4.isi.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public ModelAndView loginPage(HttpSession session) {
        final ModelAndView modelAndView = new ModelAndView();
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        final User user = userService.findUserByEmail(auth.getName());

        if(user != null) {
            session.setAttribute("userName",  user.getName() + " " + user.getLastName());
        } else {
            session.setAttribute("userName",  "");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("registration")
    public String registerPage(Model model) {
        model.addAttribute("user", new UserDto());

        return "register";
    }

    @PostMapping("registration")
    public ModelAndView createNewUser(@Valid UserDto user, BindingResult bindingResult) {

        final ModelAndView modelAndView = new ModelAndView();

        final User userExists = userService.findUserByEmail(user.getEmail());

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("register");
        } else {

            userService.saveClient(user);

            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("register");

        }
        return modelAndView;
    }


}
