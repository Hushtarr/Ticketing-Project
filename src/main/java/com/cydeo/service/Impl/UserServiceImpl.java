package com.cydeo.service.Impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Project;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.repository.UserRepository;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.mapper.UserMapper;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProjectService projectService;
    private final TaskService taskService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, @Lazy ProjectService projectService, @Lazy TaskService taskService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.projectService = projectService;
        this.taskService = taskService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> listAllUsers() {
        List<User> userList = userRepository.findAllByIsDeletedOrderByUserName(false);
        return userList.stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String userName) {
        User user = userRepository.findByUserNameAndIsDeleted(userName, false);
        return userMapper.convertToDTO(user);

    }

    @Override
    public void save(UserDTO user) {
        user.setEnabled(true);
        User obj =userMapper.convertToEntity(user);
        obj.setPassWord(passwordEncoder.encode(obj.getPassWord()));
        userRepository.save(obj);
    }

    @Override
    public void deleteByUserName(String userName) {
        // userRepository.deleteByUserName(userName);
        //this will delete the data from the database
    }

    @Override
    public UserDTO update(UserDTO user) {
        //find the current user in DB then take into the container
    User original=userRepository.findByUserNameAndIsDeleted(user.getUserName(),false);
        //convert current user to entity
    User New=userMapper.convertToEntity(user);
    //settle the new entity by give the place of original
        New.setId(original.getId());
        userRepository.save(New);
        return  findByUserName(user.getUserName());
    }
    private boolean deletableUser(User user){
        switch (user.getRole().getDescription()) {
                case"Manager":
                List<ProjectDTO>projectsList=projectService.listNonCompletedProjectsByManager(userMapper.convertToDTO(user));
                return projectsList.isEmpty();

                case"Employee":
                List<TaskDTO>tasksList=taskService.listNonCompletedTasksByEmployee(userMapper.convertToDTO(user));
                return tasksList.isEmpty();

            default:
                    return true;
        }
    }

    @Override
    public void delete(String userName) {
        //find the current user in DB then take into the container
    User user=userRepository.findByUserNameAndIsDeleted(userName,false);
    if (deletableUser(user)){
        user.setIsDeleted(true);
        user.setUserName(user.getUserName() + "-" + user.getId());  // harold@manager.com-2//Todo
        userRepository.save(user);
    }
    }

    @Override
    public List<UserDTO> listAllByRole(String role) {
        List<User>users=userRepository.findByRoleDescriptionIgnoreCaseAndIsDeleted(role,false);
        return users.stream().map(userMapper::convertToDTO).collect(Collectors.toList());
    }
}
