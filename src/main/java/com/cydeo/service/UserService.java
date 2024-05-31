package com.cydeo.service;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;

import java.util.List;

public interface UserService {
    List<UserDTO> listAllUsers();
    UserDTO findByUserName (String userName);
    void Save(UserDTO user);
    void deleteByUserName(String userName);
    UserDTO update(UserDTO user);
    void delete(String userName);
}
