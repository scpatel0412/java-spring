package com.example.demo.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtUtil;
import com.example.demo.extras.ResponseObject;
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsersService usersService;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthController(UsersService usersService,UsersRepository usersRepository,PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseObject<Users, String> demoFunction(@RequestBody Users users) {

        try {

            if (usersRepository.findByUsername(users.getUsername()).isPresent() || usersRepository.findByEmail(users.getEmail()).isPresent()) {
                return new ResponseObject<>(false,null,404,"user exists");
            }
    
            // Users users = new Users();
    
            users.setUsername(users.getUsername());
            users.setPassword(passwordEncoder.encode(users.getPassword()));
            users.setEmail(users.getEmail());
            users.setCity(users.getCity());
            users.setCountry(users.getCountry());
            users.setState(users.getState());
            users.setAddress(users.getAddress());
            users.setFirstName(users.getFirstname());
            users.setLastname(users.getLastname());
    
            Users users1 = usersRepository.save(users);
    
    
            return new ResponseObject<>(true,users1,200,null);
            
        } catch (Exception e) {
            return new ResponseObject<>(false,null,500,e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseObject<UserResponse,String> demoreFunction(@RequestBody LoginRequest loginRequest) {
        try {

            Optional<Users> optionalUser = usersRepository.findByEmail(loginRequest.getEmail());

            if (!optionalUser.isPresent()) {
                return new ResponseObject<>(false, null, 404, "User not found");
                // return "nouser found";
            }

            Users user = optionalUser.get();

            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return new ResponseObject<>(false, null, 401, "Invalid credentials");
                // return "Invalid Credentials";
            }


            String token = jwtUtil.generateToken(user.getUsername());

            UserResponse userResponse = new UserResponse(user, token);

            return new ResponseObject<>(true, userResponse, 200, null);

        } catch (Exception e) {
            System.out.print(e);
            return new ResponseObject<>(false, null, 500, "Something went wrong");
        }
        // return "hello";
    }
    
}
