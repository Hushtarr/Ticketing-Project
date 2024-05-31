package com.cydeo.service.Impl;

import com.cydeo.Repository.ProjectRepository;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.mapper.ProjectMapper;
import com.cydeo.service.ProjectService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Override
    public ProjectDTO findByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<ProjectDTO> listAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void save(ProjectDTO dto) {

    }

    @Override
    public void update(ProjectDTO dto) {

    }

    @Override
    public void delete(String projectCode) {

    }
}
