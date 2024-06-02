package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;

import java.util.List;

public interface TaskService {
    void save(TaskDTO dto);
    void delete(Long id);
    // void delete(TaskDTO dto)
    void update(TaskDTO dto);
    List<TaskDTO> listAllTasks();
    TaskDTO findById(Long id);

    int totalNonCompletedTasks(String projectCode);
    int totalCompletedTasks(String projectCode);
}
