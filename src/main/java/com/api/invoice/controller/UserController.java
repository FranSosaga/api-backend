package com.api.invoice.controller;


import com.api.invoice.dto.UserDTO;
import com.api.invoice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/saveUser")
    public void saveUser(@RequestBody UserDTO userDTO){
        try {
            userService.saveUser(userDTO);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @GetMapping("/listUsers")
    public List<UserDTO> getUsers(){
        try {
            return  userService.getUsers();
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @GetMapping("/hola")
    public String hola(){
        try {
            return "Hola";
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
