package com.pivinadanangbe.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;


@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(String.class, Date.class);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

}
