package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.entity.Project;
import com.cydeo.entity.User;

import java.util.List;

public interface ProjectService{
    ProjectDTO findByProjectCode(String projectCode);
    List<ProjectDTO>listAllProjects();
    void save(ProjectDTO dto);
    ProjectDTO update(ProjectDTO dto);
    void delete(String projectCode);
    //void deleteByProjectCode(String projectCode);
    void complete(String projectCode);
    List<ProjectDTO> listAllProjectDetails();

    List<ProjectDTO> listNonCompletedProjectsByManager(UserDTO manager);
}
