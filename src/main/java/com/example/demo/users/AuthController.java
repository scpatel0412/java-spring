package com.example.demo.users;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtUtil;
import com.example.demo.extras.ResponseObject;
import com.example.demo.user_roles.UserRoles;
import com.example.demo.user_roles.UserRolesRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsersService usersService;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRolesRepository userRolesRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthController(
        UsersService usersService,
        UsersRepository usersRepository,
        PasswordEncoder passwordEncoder,
        UserRolesRepository userRolesRepository
    ) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRolesRepository = userRolesRepository;
    }

    @PostMapping("/register")
    public ResponseObject<Users, String> demoFunction(
        @RequestBody RegisterRequest registerRequest
    ) {
        try {
            if (
                usersRepository
                    .findByUsername(registerRequest.getUsername())
                    .isPresent() ||
                usersRepository.findByEmail(registerRequest.getEmail()).isPresent()
            ) {
                return new ResponseObject<>(false, null, 404, "user exists");
            }

            Optional<UserRoles> userRole = userRolesRepository.findByRole(registerRequest.getRole());

            if (!userRole.isPresent()) {
                return new ResponseObject<>(false, null, 404, "no role found");
            }

            // if()

            Users users = new Users();

            users.setUsername(registerRequest.getUsername());
            users.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            users.setEmail(registerRequest.getEmail());
            users.setCity(registerRequest.getCity());
            users.setCountry(registerRequest.getCountry());
            users.setState(registerRequest.getState());
            users.setAddress(registerRequest.getAddress());
            users.setFirstName(registerRequest.getFirstname());
            users.setLastname(registerRequest.getLastname());
            users.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            users.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            users.setUserRole(userRole.get());

            Users users2 = usersRepository.save(users);

            return new ResponseObject<>(true, users2, 200, null);
        } catch (Exception e) {
            return new ResponseObject<>(false, null, 500, e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseObject<UserResponse, String> demoreFunction(
        @RequestBody LoginRequest loginRequest
    ) {
        try {
            Optional<Users> optionalUser = usersRepository.findByEmail(
                loginRequest.getEmail()
            );

            if (!optionalUser.isPresent()) {
                return new ResponseObject<>(false, null, 404, "User not found");
                // return "nouser found";
            }

            Users user = optionalUser.get();

            UserRoles userRole = user.getUserRole();

            if(userRole == null || !userRole.getRole().equals(loginRequest.getRole())){
                return new ResponseObject<>(false, null, 403, "User does not have this role");
            }

            if (
                !passwordEncoder.matches(
                    loginRequest.getPassword(),
                    user.getPassword()
                )
            ) {
                return new ResponseObject<>(
                    false,
                    null,
                    401,
                    "Invalid credentials"
                );
                // return "Invalid Credentials";
            }

            String token = jwtUtil.generateToken(user.getUsername());

            UserResponse userResponse = new UserResponse(user, token);

            return new ResponseObject<>(true, userResponse, 200, null);
        } catch (Exception e) {
            System.out.print(e);
            return new ResponseObject<>(
                false,
                null,
                500,
                "Something went wrong"
            );
        }
        // return "hello";
    }
}
