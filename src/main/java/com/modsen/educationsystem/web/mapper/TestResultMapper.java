package com.modsen.educationsystem.web.mapper;

import com.modsen.educationsystem.model.TestAttempt;
import com.modsen.educationsystem.model.TestResult;
import com.modsen.educationsystem.web.dto.TestResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface TestResultMapper {

    TestResultDto toDto(TestResult entity);
}