package Bank.Bank.DTO;

import Bank.Bank.Entities.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserDTO implements Serializable {

    @JsonProperty("id")
    private long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("name")
    private String name;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("phone")
    private long phone;

    @JsonProperty("email")
    private String email;

    public UserDTO() {
        this.id = 0;
        this.username = "";
        this.password = "";
        this.name = "";
        this.lastName = "";
        this.phone = 0;
        this.email = "";
    }

    public UserDTO(long id, String username, String password, String name, String lastName, long phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.lastName = user.getLastName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
