package com.modsen.educationsystem.web.mapper;

import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.web.dto.TestDto;
import com.modsen.educationsystem.model.TestAttempt;
import com.modsen.educationsystem.web.dto.TestAttemptDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface TestMapper {

    @Mapping(source = "maxAttempts", target = "maxAttempts")
    TestDto toDto(Test entity);
    List<TestDto> toDto(List<Test> entities);

    Test toEntity(TestDto dto);

    TestAttemptDto toDto(TestAttempt entity);

}