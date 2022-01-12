package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.dao.UserRepository;
import web.dto.UserDTO;
import web.mappers.UserMapper;
import web.model.User;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserDetailsService (UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername (String s) throws UsernameNotFoundException {
        System.out.println(userRepository.getByUsername(s));
        UserDTO userDTO = userMapper.toDTO(userRepository.getByUsername(s));
        System.out.println(userDTO);
        User user = userMapper.fromDTO(userDTO);
        System.out.println(user);
        if (user == null || user.getUsername() == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", s));
        }
        return user;
    }
}
