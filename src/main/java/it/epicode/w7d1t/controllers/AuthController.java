package it.epicode.w7d1t.controllers;

import it.epicode.w7d1t.exceptions.BadRequestException;
import it.epicode.w7d1t.exceptions.FailedLoginException;
import it.epicode.w7d1t.models.objects.User;
import it.epicode.w7d1t.models.requests.LoginRequest;
import it.epicode.w7d1t.models.requests.UserRequest;
import it.epicode.w7d1t.security.JwtTools;
import it.epicode.w7d1t.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTools    jwtTools;

    @PostMapping("/auth/register")
    public User register(@RequestBody @Validated UserRequest userRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        return userService.save(userRequest);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody @Validated LoginRequest loginRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.getAllErrors().toString());
        }

        User user = userService.getByUsername(loginRequest.getUsername());

        if(user.getPassword().equals(loginRequest.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new FailedLoginException("Username or password are incorrect.");
        }

    }
}
