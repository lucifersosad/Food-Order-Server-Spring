package spring.api.uteating.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spring.api.uteating.entity.Role;
import spring.api.uteating.entity.User;
import spring.api.uteating.model.SignInDTO;
import spring.api.uteating.model.SignUpDTO;
import spring.api.uteating.model.UserModel;
import spring.api.uteating.repository.RoleRepository;
import spring.api.uteating.repository.UserRepository;
import spring.api.uteating.service.JwtService;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome bitch";
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDTO signUpDto) {
        User user = new User();
        BeanUtils.copyProperties(signUpDto, user);
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singleton(roles));

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserModel> loginUser(@RequestParam("usernameOrEmail") String usernameOrEmail,
                                               @RequestParam("password") String password){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usernameOrEmail, password));

        if (authentication.isAuthenticated()) {
            UserModel userModel = new UserModel();
            User user = userRepository.getUserByUsernameOrEmail(usernameOrEmail);
            BeanUtils.copyProperties(user, userModel);
            return ResponseEntity.ok(userModel);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(null);
        }
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody SignInDTO authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsernameOrEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsernameOrEmail());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
