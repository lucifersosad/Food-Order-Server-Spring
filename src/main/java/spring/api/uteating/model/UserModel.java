package spring.api.uteating.model;

import lombok.Data;
import spring.api.uteating.entity.Role;

import java.util.Set;

@Data
public class UserModel {
    private String userId;
    private String username;
    private String email;
    private String avatarURL;
    private String phone;
    private Set<Role> roles;
}