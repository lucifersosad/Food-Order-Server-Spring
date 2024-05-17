package spring.api.uteating.model;

import lombok.Data;

@Data
public class SignUpDTO {
    private String userId;
    private String username;
    private String email;
    private String password;
}
