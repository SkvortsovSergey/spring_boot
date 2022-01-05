package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers () {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteUser(userService.getUser(id));
    }
}
