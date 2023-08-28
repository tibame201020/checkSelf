package com.custom.checkSelf.service;

import com.custom.checkSelf.model.ViewGoal;
import reactor.core.publisher.Flux;

public interface GoalService {
    /**
     * 創建對應LocalDate與Goal的ViewGoal
     * @param year 西元年
     * @param month 月份
     * @return ViewGoal Flux
     */
    Flux<ViewGoal> generateViewGoal(int year, int month);
}
