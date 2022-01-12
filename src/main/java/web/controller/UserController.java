package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dto.RoleDTO;
import web.dto.UserDTO;
import web.mappers.RoleMapper;
import web.mappers.UserMapper;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;


//
//    public UserController (UserService userService, UserMapper userMapper, RoleService roleService, RoleMapper roleMapper) {
//        this.userService = userService;
//        this.userMapper = userMapper;
//        this.roleService = roleService;
//        this.roleMapper = roleMapper;
//    }
//    private final RoleService roleService;
//    private final UserMapper userMapper;


    @GetMapping("/admin")
    public String getAllUsers (Model model, @CurrentSecurityContext(expression = "authentication.principal") User principal) {
        model.addAttribute("user", userMapper.toDTO(principal));
        model.addAttribute("roles", roleMapper.roleSetToDTO(roleService.getAllRolesSet()));
        model.addAttribute("users", userMapper.userSetToDTO(userService.getAllUsersSet()));
        return "admin";
    }


    @PostMapping("/adduser")
    public String saveUser (@ModelAttribute("user") UserDTO userDTO,
                            @RequestParam(value = "nameRoles") String[] nameRoles) {
        Set<RoleDTO> set = roleMapper.roleSetToDTO(roleService.getSetOfRoles(nameRoles));
        userDTO.setRoles(set);
        User user = userMapper.fromDTO(userDTO);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser (@ModelAttribute("user") UserDTO userDTO,
                              @RequestParam(value = "nameRoles") Set<String> nameRoles) {
        Set<RoleDTO> set = roleMapper.roleSetToDTO(roleService.getSetOfRoles(nameRoles));
        userDTO.setRoles(set);
        System.out.println(userDTO);
        User user = userMapper.fromDTO(userDTO);
        System.out.println(user);
        userService.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String showUser (Model model, @CurrentSecurityContext(expression = "authentication.principal") User principal) {
        model.addAttribute("user", userMapper.toDTO(principal));
        return "user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser (@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/")
    public String login () {
        return "login";
    }

}