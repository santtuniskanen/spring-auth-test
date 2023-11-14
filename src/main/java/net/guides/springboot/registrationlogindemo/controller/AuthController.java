package net.guides.springboot.registrationlogindemo.controller;

import jakarta.validation.Valid;
import net.guides.springboot.registrationlogindemo.dto.UserDto;
import net.guides.springboot.registrationlogindemo.entity.User;
import net.guides.springboot.registrationlogindemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping("/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("user", users);
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
    BindingResult result, Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null){
            result.rejectValue("email", "null",
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
}
