package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

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
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @PostMapping("/adduser")
    public String saveUser (@RequestParam("username") String username,
                            @RequestParam("city") String city,
                            @RequestParam("email") String email,
                            @RequestParam("password") String password,
                            @RequestParam(required = false, name = "ROLE_ADMIN") Set<String> roleAdmin) {

        Set<Role> roles = new HashSet<>();
        if (roleAdmin.size() == 1 && roleAdmin.contains("ROLE_ADMIN")) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        } else {
            roles.add(roleService.getRoleByName("ROLE_USER"));
        }
        System.out.println(roleAdmin);
        if (roleAdmin.size() == 2) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
            roles.add(roleService.getRoleByName("ROLE_USER"));
        }
        User user = new User(username, city, email, password, roles);
        user.setRoles(roles);
        try {
            userService.addUser(user);
        } catch (Exception ignored) {

        }
        roleAdmin.clear();
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser (@ModelAttribute("User") User user,
                              @RequestParam("username") String username,
                              @RequestParam("city") String city,
                              @RequestParam("email") String email,
                              @RequestParam("password") String password,
                              @RequestParam(required = false, name = "ROLE_ADMIN") Set<String> roleAdmin) {

        Set<Role> roles = new HashSet<>();

        if (roleAdmin.size() == 1 && roleAdmin.contains("ROLE_ADMIN")) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
        } else {
            roles.add(roleService.getRoleByName("ROLE_USER"));
        }
        System.out.println(roleAdmin);

        if (roleAdmin.size() == 2) {
            roles.add(roleService.getRoleByName("ROLE_ADMIN"));
            roles.add(roleService.getRoleByName("ROLE_USER"));
        }

        user.setUserName(username);
        user.setCity(city);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);
        try {
            userService.addUser(user);
        } catch (Exception ignored) {
        }
        user.setRoles(roles);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser (@PathVariable("id") int id) {
        userService.deleteUser(userService.getUser(id));
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