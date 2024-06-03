package com.cydeo.service.Impl;

import com.cydeo.entity.Role;
import com.cydeo.mapper.Ult_Mapper;
import com.cydeo.repository.RoleRepository;
import com.cydeo.dto.RoleDTO;
import com.cydeo.mapper.RoleMapper;
import com.cydeo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;
  private final Ult_Mapper ultMapper;

    public RoleServiceImpl(RoleRepository roleRepository, RoleMapper roleMapper, Ult_Mapper ultMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
        this.ultMapper = ultMapper;
    }

    @Override
    public List<RoleDTO> listAllRoles() {
//        return roleRepository.findAll().stream().map(roleMapper::convertToDTO).collect(Collectors.toList());
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream().map(roleEntity -> ultMapper.convert(roleEntity, RoleDTO.class)).collect(Collectors.toList());

    }

    @Override
    public RoleDTO findById(Long id) {
        return roleMapper.convertToDTO(roleRepository.findById(id).get());
    }
}
