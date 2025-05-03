package com.modsen.educationsystem.service.impl;

import com.modsen.educationsystem.common.exception.InvalidRequestException;
import com.modsen.educationsystem.model.Test;
import com.modsen.educationsystem.model.TestAttempt;
import com.modsen.educationsystem.model.TestResult;
import com.modsen.educationsystem.model.User;
import com.modsen.educationsystem.repository.TestAttemptRepository;
import com.modsen.educationsystem.repository.TestRepository;
import com.modsen.educationsystem.repository.TestResultRepository;
import com.modsen.educationsystem.security.service.UserHolder;
import com.modsen.educationsystem.service.CourseService;
import com.modsen.educationsystem.service.TestScoreCalculator;
import com.modsen.educationsystem.service.TestService;
import com.modsen.educationsystem.service.UserService;
import com.modsen.educationsystem.service.ValidationService;
import com.modsen.educationsystem.web.dto.TestResultDto;
import com.modsen.educationsystem.web.mapper.TestResultMapper;
import com.modsen.educationsystem.web.request.TestRequest;
import com.modsen.educationsystem.web.request.TestSubmissionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import static com.modsen.educationsystem.common.exception.ResourceNotFoundException.resourceNotFoundException;
import static com.modsen.educationsystem.common.util.ExceptionMessage.ACTIVE_ATTEMPT_EXISTS;
import static com.modsen.educationsystem.common.util.ExceptionMessage.ACTIVE_ATTEMPT_NOT_EXISTS;
import static com.modsen.educationsystem.common.util.ExceptionMessage.RESOURCE_NOT_FOUND_BY_ID;
import static com.modsen.educationsystem.common.util.ExceptionMessage.TIME_LIMIT_EXCEEDED;

@Service
@Transactional
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final CourseService courseService;
    private final UserService userService;
    private final TestRepository testRepository;
    private final UserHolder userHolder;
    private final ValidationService validationService;
    private final TestAttemptRepository testAttemptRepository;
    private final TestResultRepository testResultRepository;
    private final TestScoreCalculator testScoreCalculator;
    private final TestResultMapper testResultMapper;

    @Override
    public Test create(final Long courseId, final TestRequest request) {
        var course = courseService.getOrThrow(courseId);
        var test = new Test();

        test.setTitle(request.getTitle());
        test.setTimeLimitMinutes(request.getTimeLimitMinutes());
        test.setPassingScore(request.getPassingScore());
        test.setCourse(course);

        return testRepository.save(test);
    }

    @Override
    public Test update(final Long id, final TestRequest request) {
        var test = getOrThrow(id);

        test.setTitle(request.getTitle());
        test.setTimeLimitMinutes(request.getTimeLimitMinutes());
        test.setPassingScore(request.getPassingScore());

        return testRepository.save(test);
    }

    @Override
    public void delete(final Long id) {
        var course = getOrThrow(id);
        testRepository.delete(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Test> getTestsByCourse(final Long courseId) {
        courseService.getOrThrow(courseId);
        return testRepository.findByCourseId(courseId);
    }

    @Override
    @Transactional(readOnly = true)
    public Test getOrThrow(final Long id) {
        return testRepository.findById(id)
                .orElseThrow(resourceNotFoundException(RESOURCE_NOT_FOUND_BY_ID, id));
    }

    @Override
    public TestAttempt start(final Long id) {
        var test = getOrThrow(id);
        var securityUser = userHolder.getUser();
        var user = userService.getOrThrow(securityUser.getId());
        //TODO проверка доступности теста для польхователя
        validationService.validateTestAttempts(test, user);
        validationService.validateNoActiveAttempts(test, user);

        var attempt = new TestAttempt();
        attempt.setUser(user);
        attempt.setTest(test);
        attempt.setStartedAt(LocalDateTime.now());
        attempt.setStatus(TestAttempt.AttemptStatus.IN_PROGRESS);

        return testAttemptRepository.save(attempt);
    }

    @Override
    public TestResult submit(final Long id, final TestSubmissionRequest request) {
        var test = getOrThrow(id);
        var securityUser = userHolder.getUser();
        var user = userService.getOrThrow(securityUser.getId());

        var activeAttempt = getActiveAttemptOrThrow(user, test);
        validateTimeLimit(test, activeAttempt);

        var savedUnscoredTestResult = saveUnscoredTestResult(user, test, activeAttempt);

        int score = testScoreCalculator.calculateTestScoreAnsSaveAnswers(request, savedUnscoredTestResult);
        savedUnscoredTestResult.setScore(score);

        var testAttempt = completeActiveAttempt(activeAttempt);
        savedUnscoredTestResult.setTimeSpentSeconds((int) Duration.between(testAttempt.getStartedAt(), testAttempt.getFinishedAt()).toSeconds());

        return testResultRepository.save(savedUnscoredTestResult);
    }

    @Override
    public Page<TestResultDto> getTestResults(Long id, PageRequest of) {
        return testResultRepository.findByTestIdAndUserId(id, userHolder.getUser().getId(), of)
                .map(testResultMapper::toDto);
    }

    private TestAttempt completeActiveAttempt(TestAttempt activeAttempt) {
        activeAttempt.setStatus(TestAttempt.AttemptStatus.COMPLETED);
        activeAttempt.setFinishedAt(LocalDateTime.now());
        return testAttemptRepository.save(activeAttempt);
    }

    private TestResult saveUnscoredTestResult(User user, Test test, TestAttempt attempt) {
        var result = new TestResult();
        result.setUser(user);
        result.setTest(test);
        result.setCompletedAt(LocalDateTime.now());
        result.setTestAttempt(attempt);

        return testResultRepository.save(result);
    }

    private void validateTimeLimit(Test test, TestAttempt activeAttempt) {
        if (test.getTimeLimitMinutes() != null) {
            long minutesSpent = Duration.between(activeAttempt.getStartedAt(), LocalDateTime.now()).toMinutes();
            if (minutesSpent > test.getTimeLimitMinutes()) {
                activeAttempt.setStatus(TestAttempt.AttemptStatus.TIMED_OUT);
                testAttemptRepository.save(activeAttempt);
                throw new InvalidRequestException(TIME_LIMIT_EXCEEDED);
            }
        }
    }

    private TestAttempt getActiveAttemptOrThrow(User user, Test test) {
        return testAttemptRepository.findByUserAndTestAndStatus(user, test, TestAttempt.AttemptStatus.IN_PROGRESS)
                .orElseThrow(() -> new InvalidRequestException(ACTIVE_ATTEMPT_NOT_EXISTS));
    }
}