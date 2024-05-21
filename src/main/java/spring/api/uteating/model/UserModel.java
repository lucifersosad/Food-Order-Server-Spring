package spring.api.uteating.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import spring.api.uteating.entity.Role;

import java.util.Set;

@Data
public class UserModel {
    private String userId;
    private String fullName;
    private String username;
    private String email;
    private String avatarURL;
    private String phone;
    @JsonProperty("admin")
    private boolean isAdmin;
}