package com0.TrelloClone.user.controller;

import com0.TrelloClone.user.entity.UserEntity;
import com0.TrelloClone.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> signup(@RequestBody UserEntity userEntity) {
        boolean isValidPassword = userService.validatePassword(userEntity.getPassword());
        if (!isValidPassword) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid password format.");
        }
        boolean validUser = userService.toCheckSameUser(userEntity.getEmailID());
        if (!validUser) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid user");
        }
        if(userEntity.getSecurityAnswer()==null)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("security Answer is NULL");
        }
        if(!userService.userInformation(userEntity)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Information is missing");
        }
        UserEntity savedUser = userService.signup(userEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEntity userEntity){
        //holding false or true here if the given email and password are okay to go or not
        boolean isValidEmailAndPassword = userService.validateEmailAndPassword(userEntity.getEmailID(), userEntity.getPassword());
        if(!isValidEmailAndPassword){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sorry! email or/and password invalid");
        }
        else{
            return ResponseEntity.ok("Login Successfully!!!");
        }
    }
    @GetMapping("/forgotPassword")
    public ResponseEntity<String> forgotPassword(@RequestParam String email, @RequestParam String securityAnswer,@RequestParam String newPassword)
    {
        if(userService.forgotPassword(email, securityAnswer, newPassword)){
            return ResponseEntity.status(HttpStatus.OK).body("New password saved successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provided information is not correct");
    }

    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody UserEntity userEntity)
    {
        if(!userService.userInformation(userEntity)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Information is missing");
        }
        if(userService.updateUserEntity(userEntity))
        {
            return ResponseEntity.status(HttpStatus.OK).body("User information updated successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No! such user found");
    }


}