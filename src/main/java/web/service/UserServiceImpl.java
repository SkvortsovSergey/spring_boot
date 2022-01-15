package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.dao.UserRepository;
import web.model.User;

import javax.transaction.Transactional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public Set<User> getAllUsersSet () {
        return userRepository.getAll();
    }

    @Override
    public User getUserById (Integer id) {
        return userRepository.getById(id);
    }



    @Override
    public void addUser (User user) {

        userRepository.addUser(user);
    }

    @Override
    public void deleteUser (User user) {
        userRepository.deleteUser(user);
    }

    @Override
    public void editUser (User user) {
        userRepository.editUser(user);
    }

    @Override
    public User getByName (String username) {
        return userRepository.getByUsername(username);
    }
    @Override
    public void deleteUser (Integer id) {
        userRepository.deleteUser(id);
    }
}