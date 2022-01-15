package web.service;

import web.model.User;

import java.util.Set;

public interface UserService  {

    Set<User> getAllUsersSet ();

    User getUserById (Integer id);

    void addUser (User user);

    void deleteUser (User user);

    void editUser (User user);

    User getByName (String username);


    void deleteUser (Integer id);
}
