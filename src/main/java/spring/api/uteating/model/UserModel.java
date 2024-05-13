package spring.api.uteating.model;

import lombok.Data;

@Data
public class UserModel {
    private String userId;
    private String username;
    private String email;
    private String avatarURL;
    private String phone;
}