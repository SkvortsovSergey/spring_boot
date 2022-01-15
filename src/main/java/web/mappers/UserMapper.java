package web.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;
import web.dto.UserDTO;
import web.model.User;

import java.util.Set;

@Mapper(componentModel = "spring")
@Service
public interface UserMapper {


    UserDTO toDTO (User user);

    User fromDTO (UserDTO userDTO);

    Set<UserDTO> userSetToDTO (Set<User> userList);

    Set<User> userSetFromDTO (Set<UserDTO> userSet);
}