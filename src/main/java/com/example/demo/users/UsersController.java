package com.example.demo.users;

import com.example.demo.extras.ResponseObject;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;
    private final UsersRepository usersRepository;

    public UsersController(
        UsersService usersService,
        UsersRepository usersRepository
    ) {
        this.usersService = usersService;
        this.usersRepository = usersRepository;
    }

    @PostMapping("me")
    public ResponseObject<Users, String> profileDetails() {
        try {
            Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

            Optional<Users> user = usersRepository.findByUsername(
                authentication.getName()
            );
            // System.out.print();

            if (!user.isPresent()) {
                return new ResponseObject<>(false, null, 404, "User not found");
            }

            Users user1 = user.get();

            System.out.print(user1);

            return new ResponseObject<>(false, user1, 200, null);
        } catch (Exception e) {
            System.out.print(e);
            return new ResponseObject<>(
                false,
                null,
                500,
                "Something went Wrong"
            );
        }
    }
}
