package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;

import java.util.List;

public interface TaskService {
    void save(TaskDTO dto);
    void delete(Long id);
    void deleteByProject(ProjectDTO dto);
    void update(TaskDTO dto);
    List<TaskDTO> listAllTasks();
    TaskDTO findById(Long id);
    List<TaskDTO> listAllTasksByStatusIsNot(Status status);
    List<TaskDTO> listAllTasksByStatus(Status status);
    int totalNonCompletedTasks(String projectCode);
    int totalCompletedTasks(String projectCode);

    void completeByProject(ProjectDTO projectdto);
}
