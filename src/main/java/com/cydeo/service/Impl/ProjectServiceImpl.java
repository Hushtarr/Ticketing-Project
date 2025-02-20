package com.cydeo.service.Impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import com.cydeo.mapper.UserMapper;
import com.cydeo.repository.ProjectRepository;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.entity.Project;
import com.cydeo.enums.Status;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TaskService taskService;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper, @Lazy UserService userService, UserMapper userMapper, TaskService taskService) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.taskService = taskService;
    }

    @Override
    public ProjectDTO findByProjectCode(String projectCode) {
        return projectMapper.convertToDTO(projectRepository.findByProjectCode(projectCode));
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO dto) {
        dto.setProjectStatus(Status.OPEN);
        Project project=projectMapper.convertToEntity(dto);
        projectRepository.save(project);
    }

    @Override
    public ProjectDTO update(ProjectDTO dto) {
    Project projectInDB=projectRepository.findByProjectCode(dto.getProjectCode());
    Project projectFromView=projectMapper.convertToEntity(dto);
        projectFromView.setId(projectInDB.getId());
        projectFromView.setProjectStatus(projectInDB.getProjectStatus());
        projectRepository.save(projectFromView);
        return findByProjectCode(dto.getProjectCode());
    }

    @Override
    public void delete(String projectCode) {
        Project project =projectRepository.findByProjectCode(projectCode);
        project.setIsDeleted(true);
        project.setProjectCode(project.getProjectCode() + "-" + project.getId());  // TODO
        taskService.deleteByProject(projectMapper.convertToDTO(project));

        projectRepository.save(project);
    }

    /* Override
    public void deleteByProjectCode(String projectCode) {
         //projectRepository.deleteByProjectCode(projectCode);
        //this will delete the data from the database
        //do not touch this part->pure note
    }*/

    @Override
    public void complete(String projectCode) {
        Project project= projectRepository.findByProjectCode(projectCode);
        project.setProjectStatus(Status.COMPLETE);
        projectRepository.save(project);
        taskService.completeByProject(projectMapper.convertToDTO(project));

    }

    @Override
    public List<ProjectDTO> listAllProjectDetails() {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        UserDTO userDTO=userService.findByUserName(username);
        User user = userMapper.convertToEntity(userDTO);
        List<Project> list=projectRepository.findAllByAssignedManager(user);
        return list.stream()
                .map(project -> {
                    ProjectDTO obj=projectMapper.convertToDTO(project);
                    obj.setCompleteTaskCounts(taskService.totalCompletedTasks(project.getProjectCode()));
                    obj.setUnfinishedTaskCounts(taskService.totalNonCompletedTasks(project.getProjectCode()));
                    return obj;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> listNonCompletedProjectsByManager(UserDTO manager) {
        List<Project> list=projectRepository.findAllByProjectStatusIsNotAndAssignedManager(Status.COMPLETE,userMapper.convertToEntity(manager));

        return list.stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }


}
