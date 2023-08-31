package com.custom.checkSelf.service;

import com.custom.checkSelf.model.CheckList;
import com.custom.checkSelf.model.Goal;
import com.custom.checkSelf.model.Period;
import com.custom.checkSelf.model.ViewGoal;
import com.custom.checkSelf.repo.GoalRepo;
import com.custom.checkSelf.service.impl.GoalServiceImpl;
import net.bytebuddy.utility.RandomString;
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
import java.util.List;
import java.util.random.RandomGenerator;

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
        Goal goal = generateRandomGoal();
        Goal goal_II = generateRandomGoal();

        when(goalRepo.findAll()).thenReturn(Flux.just(goal, goal_II));

        int year = RandomGenerator.getDefault().nextInt(1991, 2023);
        int month = RandomGenerator.getDefault().nextInt(1, 12);
        ViewGoal result = mockViewGoal(year, month, List.of(goal, goal_II));
        int remainDays = LocalDate.of(year, month, 1).lengthOfMonth() - 1;


        Flux<ViewGoal> viewGoalFlux = goalService.generateViewGoal(year, month).log();

        StepVerifier.create(viewGoalFlux)
                .expectNext(result)
                .expectNextCount(remainDays)
                .verifyComplete();
    }

    private ViewGoal mockViewGoal(int year, int month, List<Goal> goalList) {
        ViewGoal viewGoal = new ViewGoal();
        viewGoal.setGoalList(goalList);
        viewGoal.setDate(LocalDate.of(year, month, 1));

        return viewGoal;
    }

    private Goal generateRandomGoal() {
        CheckList checkList = new CheckList();
        checkList.setName(RandomString.make());
        checkList.setDescription(RandomString.make());

        Goal goal = new Goal();
        goal.setCheckList(checkList);

        int pick = RandomGenerator.getDefault().nextInt(Period.values().length);
        goal.setPeriod(Period.values()[pick]);
        goal.setDone(RandomGenerator.getDefault().nextBoolean());

        return goal;
    }

}