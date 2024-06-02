package com.cydeo.service.Impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.TaskRepository;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserService userService;
    private final UserMapper userMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, UserService userService, UserMapper userMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public void save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        taskRepository.save(taskMapper.convertToEntity(dto));
    }

    @Override
    public void delete(Long id) {
        Optional<Task>foundTask =taskRepository.findById(id);
        //findById came from JpaRepo so we need to add Optional to fit his functionality
        if(foundTask.isPresent()){
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }

    }

    @Override
    public void update(TaskDTO dto) {
    Optional<Task>task=taskRepository.findById(dto.getId());
    Task convertedTask = taskMapper.convertToEntity(dto);
    if (task.isPresent()){
        convertedTask.setTaskStatus(task.get().getTaskStatus());
        convertedTask.setAssignedDate(task.get().getAssignedDate());
        taskRepository.save(convertedTask);
    }
    }

    @Override
    public List<TaskDTO> listAllTasks() {
        return taskRepository.findAll().stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task>foundTask = taskRepository.findById(id);
        return foundTask.map(taskMapper::convertToDTO).orElse(null);

    }

    @Override
    public List<TaskDTO> listAllTasksByStatusIsNot(Status status) {
        UserDTO loginUser=userService.findByUserName("john@employee.com");
        List<Task>tasks=taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status,userMapper.convertToEntity(loginUser));
        return tasks.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(Status status) {
        UserDTO loginUser=userService.findByUserName("john@employee.com");
        List<Task>tasks=taskRepository.findAllByTaskStatusAndAssignedEmployee(status,userMapper.convertToEntity(loginUser));
        return tasks.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }


    @Override
    public int totalNonCompletedTasks(String projectCode) {
        return taskRepository.totalNonCompleteTasks(projectCode);
    }

    @Override
    public int totalCompletedTasks(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }
}
