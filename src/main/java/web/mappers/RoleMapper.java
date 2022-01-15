package web.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import web.dto.RoleDTO;
import web.model.Role;

import java.util.Set;

    @Service
    @Mapper(componentModel = "spring")
    public interface RoleMapper {


        RoleDTO toDTO (Role role);

        Role fromDTO (RoleDTO roleDTO);

        Set<RoleDTO> roleSetToDTO (Set<Role> roles);

        Set<Role> roleSetFromDTO (Set<RoleDTO> roles);
    }

