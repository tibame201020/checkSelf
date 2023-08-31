package com.custom.checkSelf.service.impl;

import com.custom.checkSelf.model.Goal;
import com.custom.checkSelf.model.ViewGoal;
import com.custom.checkSelf.repo.GoalRepo;
import com.custom.checkSelf.service.GoalService;
import com.custom.checkSelf.util.DateUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

@Service
public class GoalServiceImpl implements GoalService {
    private final GoalRepo goalRepo;

    public GoalServiceImpl(GoalRepo goalRepo) {
        this.goalRepo = goalRepo;
    }


    @Override
    public Flux<ViewGoal> generateViewGoal(int year, int month) {
        Flux<Goal> goalFlux = goalRepo.findAll();
        Flux<LocalDate> localDateFlux = DateUtil.getAllDaysInMonth(year, month);

        return localDateFlux.flatMap(localDate -> goalFlux
                .buffer()
                .map(goalList -> {
                            ViewGoal viewGoal = new ViewGoal();
                            viewGoal.setGoalList(goalList);
                            viewGoal.setDate(localDate);
                            return viewGoal;
                        }
                ));
    }
}
