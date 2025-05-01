package com.modsen.educationsystem.web.mapper;

import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.web.dto.UserDto;
import com.modsen.educationsystem.web.request.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User fromRegistrationRequest(RegistrationRequest dto);

    UserDto toDto(User user);

}