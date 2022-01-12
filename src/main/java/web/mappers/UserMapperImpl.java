//package web.mappers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import web.dto.RoleDTO;
//import web.dto.UserDTO;
//import web.model.Role;
//import web.model.User;
//import web.service.RoleService;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//
//@Component
//public class UserMapperImpl implements UserMapper {
//    private final RoleService roleService;
//
//    private final RoleMapper roleMapper;
//
//    @Autowired
//    public UserMapperImpl (RoleService roleService, RoleMapper roleMapper) {
//        this.roleService = roleService;
//        this.roleMapper = roleMapper;
//    }
//
//    @Override
//    public UserDTO toDTO (User user) {
//        if (user == null) {
//            return null;
//        }
//
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setUsername(user.getUsername());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setCity(user.getCity());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setRoles(new HashSet<RoleDTO>(){for(Role role: )});
//
//
//
//        return userDTO;
//    }
//
//    @Override
//    public User fromDTO (UserDTO userDTO) {
//        if (userDTO == null) {
//            return null;
//        }
//
//        User user = new User();
//        user.setId(userDTO.getId());
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(userDTO.getPassword());
//        user.setCity(userDTO.getCity());
//        user.setEmail(userDTO.getEmail());
//        user.setRoles(new HashSet<Role>(userDTO
//                .getRoles()
//                .stream()
//                .map(role->roleService.getRoleByRole(role.getRole()))
//                .collect(Collectors.toSet())));
//        return user;
//    }
//
//    @Override
//    public Set<UserDTO> userSetToDTO (Set<User> userSet) {
//        if (userSet == null) {
//            return null;
//        }
//
//        Set<UserDTO> set = new HashSet<>();
//        for (User user : userSet) {
//            set.add(toDTO(user));
//        }
//
//        return set;
//    }
//    @Override
//    public Set<User> userSetFromDTO (Set<UserDTO> userSet) {
//        if (userSet == null) {
//            return null;
//        }
//
//        Set<User> set = new HashSet<>();
//        for (UserDTO user : userSet) {
//            set.add(fromDTO(user));
//        }
//
//        return set;
//    }
//}
