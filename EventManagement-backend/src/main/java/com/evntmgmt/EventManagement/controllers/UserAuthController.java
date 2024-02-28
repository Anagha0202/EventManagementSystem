package com.evntmgmt.EventManagement.controllers;

import com.evntmgmt.EventManagement.dto.UserAuthDto;
import com.evntmgmt.EventManagement.dto.UserSignupDto;
import com.evntmgmt.EventManagement.response.Command;
import com.evntmgmt.EventManagement.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/user")
public class UserAuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public Command signup(@Valid @RequestBody UserSignupDto userSignupDto) {
        return userService.signUp(userSignupDto.getFirstName(), userSignupDto.getLastName(), userSignupDto.getEmail(), userSignupDto.getPassword());
    }
    @PostMapping("/login")
    public Command login(@Valid @RequestBody UserAuthDto loginRequestDto) {
        return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }
}
