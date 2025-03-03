package com.nexblue.nbms.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nexblue.nbms.web.dto.User;

@Mapper
public interface UserMapper {
    List<User> findAll();

    User find(String id);

    void create(User user);

    void update(User user);

    void delete(String id);
}
