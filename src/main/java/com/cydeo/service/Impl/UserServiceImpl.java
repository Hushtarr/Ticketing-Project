package com.cydeo.service.Impl;

import com.cydeo.Repository.UserRepository;
import com.cydeo.dto.RoleDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Role;
import com.cydeo.entity.User;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, RoleMapper roleMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        return userRepository.findAll().stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String userName) {
        return userMapper.convertToDTO(userRepository.findUserByUserName(userName));
    }

    @Override
    public void Save(UserDTO user) {
        userRepository.save(userMapper.convertToEntity(user));
    }

    @Override
    public void deleteByUserName(String userName) {
        userRepository.deleteByUserName(userName);
    }

    @Override
    public UserDTO update(UserDTO user) {
        //find current user in DB then take into the container
    User original=userRepository.findUserByUserName(user.getUserName());
        //convert current user to entity
    User New=userMapper.convertToEntity(user);
    //settle the new entity by give the place of original
        New.setId(original.getId());
        userRepository.save(New);
        return  findByUserName(user.getUserName());
    }

    @Override
    public void delete(String userName) {
        //find current user in DB then take into the container
    User user=userRepository.findUserByUserName(userName);
    user.setIsDeleted(true);
    userRepository.save(user);
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User>users=userRepository.findByRoleDescriptionIgnoreCase(role);
        return users.stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }
}
