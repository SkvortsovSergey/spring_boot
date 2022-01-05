package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

@Controller
public class UserController {

    private UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController (UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String getAllUsers (Model model, @CurrentSecurityContext(expression = "authentication.principal") User principal) {
        model.addAttribute("user", principal);
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/adduser")
    public String saveUser (@ModelAttribute("user") User user,
                            @RequestParam("username") String username,
                            @RequestParam("city") String city,
                            @RequestParam("email") String email,
                            @RequestParam("password") String password,
                            @RequestParam(value = "nameRoles") String [] nameRoles) {
        user.setUserName(username);
        user.setCity(city);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roleService.getSetOfRoles(nameRoles));
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser (@ModelAttribute("user") User user,
                              @RequestParam("username") String username,
                              @RequestParam("city") String city,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam(value = "nameRoles") String [] nameRoles) {
        user.setUserName(username);
        user.setCity(city);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roleService.getSetOfRoles(nameRoles));
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public ModelAndView showUser () {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/")
    public String login () {
        return "login";
    }
}