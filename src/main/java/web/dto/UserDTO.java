package web.dto;

import javax.persistence.Transient;
import java.util.Set;
import java.util.stream.Collectors;


public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String city;
    private Set<RoleDTO> roles;

    public UserDTO () {
    }

    public UserDTO (Integer id, String username, String password, String email, String city, Set<RoleDTO> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.city = city;
        this.roles = roles;
    }

    public Integer getId () {
        return id;
    }

    public void setId (Integer id) {
        this.id = id;
    }

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }

    public Set<RoleDTO> getRoles () {
        return roles;
    }

    public void setRoles (Set<RoleDTO> roles) {
        this.roles = roles;
    }
    @Transient
    public String getStringRoles () {
        return this.getRoles()
                .stream()
                .map(roleDTO -> roleDTO.getRole())
                .map(s -> s.substring(s.indexOf('_') + 1))
                .collect(Collectors.joining(" "));

    }
    @Override
    public String toString () {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", roles=" + roles +
                '}';
    }
}
