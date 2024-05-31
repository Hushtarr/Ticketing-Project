package com.cydeo.mapper;

import com.cydeo.dto.UserDTO;
import com.cydeo.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public User convertToEntity(UserDTO dto) {
        return mapper.map(dto,User.class);
    }

    public UserDTO convertToDTO(User entity) {
        return mapper.map(entity,UserDTO.class);
    }
}
