package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;

import java.util.List;

public interface ProjectService{
    ProjectDTO findByProjectCode(String projectCode);
    List<ProjectDTO>listAllProjects();
    void save(ProjectDTO dto);
    ProjectDTO update(ProjectDTO dto);
    void delete(String projectCode);
    void deleteByProjectCode(String projectCode);
    void complete(String projectCode);
}
