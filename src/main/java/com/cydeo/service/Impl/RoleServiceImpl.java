package com.cydeo.service.Impl;

import com.cydeo.Repository.RoleRepository;
import com.cydeo.dto.RoleDTO;
import com.cydeo.entity.Role;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
  private RoleRepository roleRepository;
  private RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleDTO> listAllRoles() {
        return roleRepository.findAll().stream().map(roleMapper::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public RoleDTO findById(Long id) {
        return roleMapper.convertToDTO(roleRepository.findById(id).get());
    }
}
