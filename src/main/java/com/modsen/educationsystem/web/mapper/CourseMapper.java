package com.modsen.educationsystem.web.mapper;

import com.modsen.educationsystem.model.Course;
import com.modsen.educationsystem.web.dto.CourseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = UserMapper.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface CourseMapper {

    CourseDto toDto(Course entity);

    Course toEntity(CourseDto dto);

}