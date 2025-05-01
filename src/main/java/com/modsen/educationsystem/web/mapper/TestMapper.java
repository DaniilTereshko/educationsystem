package com.modsen.educationsystem.web.mapper;

import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.web.dto.TestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface TestMapper {

    TestDto toDto(Test entity);
    List<TestDto> toDto(List<Test> entities);

    Test toEntity(TestDto dto);

}