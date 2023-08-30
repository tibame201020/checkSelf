package com.custom.checkSelf.controller;

import com.custom.checkSelf.model.CheckList;
import com.custom.checkSelf.model.Goal;
import com.custom.checkSelf.repo.CheckListRepo;
import com.custom.checkSelf.repo.GoalRepo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Goal Controller
 */
@RestController
@RequestMapping("/api")
public class GoalController {

    private final Log log;
    private final GoalRepo goalRepo;
    private final CheckListRepo checkListRepo;

    public GoalController(GoalRepo goalRepo, CheckListRepo checkListRepo) {
        this.log = LogFactory.getLog(this.getClass());
        this.goalRepo = goalRepo;
        this.checkListRepo = checkListRepo;
    }

    @RequestMapping("/addCheckList")
    public Mono<CheckList> addCheckList(@RequestBody CheckList checkList) {
        return checkListRepo.save(checkList);
    }

    @RequestMapping("/addGoal")
    public Mono<Goal> addGoal(@RequestBody Goal goal) {
        return goalRepo.save(goal);
    }

    @RequestMapping("/getAllGoal")
    public Flux<Goal> getAllGoal() {
        return goalRepo.findAll();
    }
}
