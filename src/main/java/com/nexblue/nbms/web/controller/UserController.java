package com.nexblue.nbms.web.controller;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import com.nexblue.nbms.web.dto.User;
import com.nexblue.nbms.web.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<?> findAll() {
        try {
            List<User> userList = userService.findAll();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            MultiValueMap<String, String> errMap = new LinkedMultiValueMap<String, String>();
            errMap.add("msg", e.getMessage());
            return new ResponseEntity<>(errMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> find(@PathVariable String id) {
        try {
            User user = userService.find(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user")
    public ResponseEntity<?> create(@RequestBody User user) {
        try {
            userService.create(user);
            return new ResponseEntity<>("Create Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<?> update(@RequestBody User user) {
        try {
            userService.update(user);
            return new ResponseEntity<>("Update Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        try {
            userService.delete(id);
            return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
