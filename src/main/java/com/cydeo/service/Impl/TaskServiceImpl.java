package com.cydeo.service.Impl;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.mapper.ProjectMapper;
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
    private final ProjectMapper projectMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, UserService userService, UserMapper userMapper, ProjectMapper projectMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.projectMapper = projectMapper;
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
        //[findById] method came from JpaRepo, so we need to add Optional to fit his functionality
        if(foundTask.isPresent()){
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }

    }

    @Override
    public void deleteByProject(ProjectDTO dto) {
        Project project=projectMapper.convertToEntity(dto);
        List<Task>tasks=taskRepository.findAllByProject(project);
        tasks.forEach(task->delete(task.getId()));
    }

    @Override
    public void update(TaskDTO dto) {
    Optional<Task>task=taskRepository.findById(dto.getId());
    Task convertedTask = taskMapper.convertToEntity(dto);
    if (task.isPresent()){
        convertedTask.setTaskStatus(dto.getTaskStatus()==null?task.get().getTaskStatus():dto.getTaskStatus());// Todo
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

    @Override
    public void completeByProject(ProjectDTO projectdto) {
        Project project=projectMapper.convertToEntity(projectdto);
        List<Task>tasks=taskRepository.findAllByProject(project);
        tasks.stream()
                .map(taskMapper::convertToDTO)
                .forEach(tasksDTO->{tasksDTO.setTaskStatus(Status.COMPLETE);
                update(tasksDTO);
                });
    }

    @Override
    public List<TaskDTO> listNonCompletedTasksByEmployee(UserDTO employee) {
        List<Task> list=taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(Status.COMPLETE,userMapper.convertToEntity(employee));
        return list.stream().map(taskMapper::convertToDTO).collect(Collectors.toList());
    }
}
