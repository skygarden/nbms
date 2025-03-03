package com.nexblue.nbms.web.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexblue.nbms.web.dto.User;
import com.nexblue.nbms.web.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserMapper userMapper;

    public List<User> findAll() {
        return userMapper.findAll();
    }

    public User find(String id) {
        return userMapper.find(id);
    }

    public void create(User user) {
        userMapper.create(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void delete(String id) {
        userMapper.delete(id);
    }
}
