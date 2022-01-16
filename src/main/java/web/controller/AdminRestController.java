package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.*;
import web.dto.RoleDTO;
import web.dto.UserDTO;
import web.mappers.RoleMapper;
import web.mappers.UserMapper;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.Set;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private final RoleService roleService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public AdminRestController (RoleService roleService, UserService userService, UserMapper userMapper, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @GetMapping("/principal")
    public UserDTO getCurrentUser (@CurrentSecurityContext(expression = "authentication.principal") User principal) {
        return userMapper.toDTO(principal);
    }

    @GetMapping("/users")
    public Set<UserDTO> getAllUsers () {
        return userMapper.userSetToDTO(userService.getAllUsersSet());
    }

    @GetMapping("/roles")
    public Set<RoleDTO> getAllRoles () {
        return roleMapper.roleSetToDTO(roleService.getAllRolesSet());
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserById (@PathVariable Integer id) {
        return userMapper.toDTO(userService.getUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById (@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PostMapping("/users")
    public void addNewUser (@RequestBody UserDTO userDTO) {
        User user = userMapper.fromDTO(userDTO);
        userService.addUser(user);
    }

    @PutMapping("/users")
    public void updateUser (@RequestBody UserDTO userDTO) {
        User user = userMapper.fromDTO(userDTO);
        userService.editUser(user);
    }
}

