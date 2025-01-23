package com.example.demo.user_roles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.extras.ResponseObject;

@RestController
@RequestMapping("/api/user-roles")
public class UserRolesController {

    @Autowired
    private final UserRolesRepository userRolesRepository;

    public UserRolesController(UserRolesRepository userRolesRepository){
        this.userRolesRepository = userRolesRepository;
    }

    @GetMapping("/roles")
    public ResponseObject<List<UserRoles>,String> getAllRoles() {
        try {
            List<UserRoles> allRoles = userRolesRepository.findAll();
            return new ResponseObject<>(true,allRoles,200,null);    
        } catch (Exception e) {
            System.out.print(e);
            return new ResponseObject<>(false,null,500,"Something went wrong");
        }        
    }

    @GetMapping("/roles/{id}")
    public ResponseObject<Optional<UserRoles>,String> getMethodName(@PathVariable("id") Long id) {
        try {
            Optional<UserRoles> roleData = userRolesRepository.findById(id);
            if(!roleData.isPresent()){
                return new ResponseObject<>(false,null,404,"No role found");
            }
            return new ResponseObject<>(true,roleData,200,null);    
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseObject<>(false,null,500,"Something went wrong");
        }
    }    
}
