package spring.api.uteating.model;

import lombok.Data;

@Data
public class SignInDTO {
    private String usernameOrEmail;
    private String password;
}
