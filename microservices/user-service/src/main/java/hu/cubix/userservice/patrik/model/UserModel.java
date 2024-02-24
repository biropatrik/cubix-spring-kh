package hu.cubix.userservice.patrik.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import java.util.Set;

@Entity
public class UserModel {

    @Id
    private String username;
    private String password;
    private String email;
    private String facebookId;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles;

    public UserModel() {
    }

    public UserModel(String email, String password, Set<String> roles) {
        this.email = email;
        this.username = email;
        this.password = password;
        this.roles = roles;
    }

    public UserModel(String username, String email, String password, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
