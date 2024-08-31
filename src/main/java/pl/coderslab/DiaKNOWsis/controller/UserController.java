package pl.coderslab.DiaKNOWsis.controller;

import pl.coderslab.DiaKNOWsis.model.User;
import pl.coderslab.DiaKNOWsis.service.UserService;
import pl.coderslab.DiaKNOWsis.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        User savedUser = userService.registerUser(user);
        model.addAttribute("user", savedUser);
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid email or password");
        }
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user") User user, Model model) {
        System.out.println("Metoda loginUser została wywołana");
        System.out.println("Email z formularza: " + user.getEmail());
        System.out.println("Hasło z formularza: " + user.getHaslo());

        User foundUser = userService.findByEmail(user.getEmail());
        if (foundUser != null) {
            System.out.println("Zakodowane hasło w bazie: " + foundUser.getHaslo());
            boolean passwordMatches = userService.getPasswordEncoder().matches(user.getHaslo(), foundUser.getHaslo());
            System.out.println("Czy hasło się zgadza: " + passwordMatches);
            if (passwordMatches) {
                return "redirect:/home";
            }
        }
        return "redirect:/users/login?error=true";
    }




    @GetMapping("/details")
    public String userDetails(@AuthenticationPrincipal UserDetails currentUser, Model model) {
        User user = userService.findByEmail(currentUser.getUsername());
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            return "redirect:/error";
        }
        return "details";
    }
}
