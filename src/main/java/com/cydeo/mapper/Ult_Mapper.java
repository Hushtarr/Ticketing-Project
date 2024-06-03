package com.cydeo.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Ult_Mapper {
    private final ModelMapper mapper;

    public Ult_Mapper(ModelMapper mapper) {
        this.mapper = mapper;
    }


    public <T> T convert(Object objToBeConverted, Class<T> convertedObj){
        return mapper.map(objToBeConverted,convertedObj);
    }
}
// this class been used in RoleServiceImpl