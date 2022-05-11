package com.example.demoFirstApp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthRestController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRep;

    @PostMapping(value = "/auth/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody User user) {
        // save new user to db
        User newUser = userRep.save(user);
        String token = jwtUtil.generateToken(Long.toString(newUser.getId()), newUser.getUserName(),newUser.getRole());
        Map<String, String> res = new HashMap<>();
        res.put("token", token);
        return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
    }

    @PostMapping(value = "/auth/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody User user) {
        // get user by userName and password from db
        User newUser = userRep.findByUserName(user.getUserName());
        if(newUser == null) {
            Map<String, String> res = new HashMap<String, String>();
            res.put("message", "username not correct");
            return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
        }
        if(!user.getPassWord().equals(newUser.getPassWord())) {
            Map<String, String> res = new HashMap<String, String>();
            res.put("message", "password not correct");
            return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
        }
        String token = jwtUtil.generateToken(Long.toString(newUser.getId()), newUser.getUserName(),newUser.getRole());
        Map<String, String> res = new HashMap<String, String>();
        res.put("token", token);
        return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
    }
}
