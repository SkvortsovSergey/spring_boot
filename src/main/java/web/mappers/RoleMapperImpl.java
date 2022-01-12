//package web.mappers;
//
//import org.springframework.stereotype.Component;
//import web.dto.RoleDTO;
//import web.model.Role;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Component
//public class RoleMapperImpl implements RoleMapper {
//
//
//
//    @Override
//    public RoleDTO toDTO (Role role) {
//        if (role == null) {
//            return null;
//        }
//
//        RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId(role.getId());
//        roleDTO.setRole(role.getRole());
//        return roleDTO;
//    }
//
//    @Override
//    public Role fromDTO (RoleDTO roleDTO) {
//        if (roleDTO == null) {
//            return null;
//        }
//
//        Role role = new Role();
//
//        return role;
//    }
//
//    @Override
//    public Set<RoleDTO> roleSetToDTO (Set<Role> roles) {
//        if (roles == null) {
//            return null;
//        }
//
//        Set<RoleDTO> set = new HashSet<RoleDTO>();
//        for (Role role : roles) {
//            set.add(toDTO(role));
//        }
//
//        return set;
//    }
//
//    @Override
//    public Set<Role> roleSetFromDTO (Set<RoleDTO> roles) {
//        if (roles == null) {
//            return null;
//        }
//
//        Set<Role> set = new HashSet<Role>();
//        for (RoleDTO role : roles) {
//            set.add(fromDTO(role));
//        }
//
//        return set;
//    }
//}
