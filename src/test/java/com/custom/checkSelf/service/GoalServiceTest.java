package com.custom.checkSelf.service;

import com.custom.checkSelf.model.CheckList;
import com.custom.checkSelf.model.Goal;
import com.custom.checkSelf.model.Period;
import com.custom.checkSelf.model.ViewGoal;
import com.custom.checkSelf.repo.GoalRepo;
import com.custom.checkSelf.service.impl.GoalServiceImpl;
import com.custom.checkSelf.util.DateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GoalServiceTest {
    private final Log log = LogFactory.getLog(this.getClass());
    @Mock
    private GoalRepo goalRepo;
    @InjectMocks
    private GoalServiceImpl goalService;


    @Test
    void generateViewGoal() {
        int year = 2023;
        int month = 8;
        String goalName = "testTargetName";
        String goalDescription = "testTargetDescription";
        Period goalPeriod = Period.MONTH;
        boolean goalIsDone = false;

        ViewGoal result = mockViewGoal(year, month, goalName, goalDescription, goalPeriod, goalIsDone);
        int remainDays = DateUtil.getDaysOfMonth(year, month) - 1;

        when(goalRepo.findAll()).thenReturn(mockFakeGoalAll(goalName, goalDescription, goalPeriod, goalIsDone));


        Flux<ViewGoal> viewGoalFlux = goalService.generateViewGoal(year, month).log();

        StepVerifier.create(viewGoalFlux)
                .expectNext(result)
                .expectNextCount(remainDays)
                .verifyComplete();
    }

    private Flux<Goal> mockFakeGoalAll(String goalName, String goalDescription, Period goalPeriod, boolean goalIsDone) {
        return Flux.just(
                generateGoal(goalName, goalDescription, goalPeriod, goalIsDone)
        );
    }

    private ViewGoal mockViewGoal(int year, int month, String goalName, String goalDescription, Period goalPeriod, boolean goalIsDone) {
        ViewGoal viewGoal = new ViewGoal();
        viewGoal.setDate(LocalDate.of(year, month, 1));
        Goal goal = generateGoal(goalName, goalDescription, goalPeriod, goalIsDone);
        viewGoal.setGoal(goal);
        return viewGoal;
    }

    private Goal generateGoal(String name, String description, Period period, boolean isDone) {
        CheckList checkList = new CheckList();
        checkList.setName(name);
        checkList.setDescription(description);
        Goal goal = new Goal();
        goal.setCheckList(checkList);
        goal.setPeriod(period);
        goal.setDone(isDone);

        return goal;
    }
}