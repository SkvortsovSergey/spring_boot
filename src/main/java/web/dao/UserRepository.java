package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.Set;

@Repository
public interface UserRepository  {

    User getById (Integer id);


    void addUser (User user);

    void deleteUser (User user);

    void deleteUser (Integer id);

    void editUser (User user);

    User getByUsername (String username);

    Set<User> getAll ();

}