package com.example.demoFirstApp.profile;

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
    private ProfileRepository profileRepository;

    @PostMapping(value = "/auth/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody Profile profile) {
        // save new Profile to db
        Profile newProfile = profileRepository.save(profile);
        String token = jwtUtil.generateToken(Long.toString(newProfile.getId()), newProfile.getFirstName(),newProfile.getRole());
        Map<String, String> res = new HashMap<>();
        res.put("token", token);
        return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
    }

    @PostMapping(value = "/auth/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Profile profile) {
        // get Profile by firstName and password from db
        Profile newProfile = profileRepository.findByFirstName(profile.getFirstName());
        if(newProfile == null) {
            Map<String, String> res = new HashMap<String, String>();
            res.put("message", "firstName not correct");
            return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
        }
        if(!profile.getPassWord().equals(newProfile.getPassWord())) {
            Map<String, String> res = new HashMap<String, String>();
            res.put("message", "password not correct");
            return new ResponseEntity<Map<String, String>>(res, HttpStatus.BAD_REQUEST);
        }
        String token = jwtUtil.generateToken(Long.toString(newProfile.getId()), newProfile.getFirstName(),newProfile.getRole());
        Map<String, String> res = new HashMap<String, String>();
        res.put("token", token);
        return new ResponseEntity<Map<String, String>>(res, HttpStatus.OK);
    }
}
