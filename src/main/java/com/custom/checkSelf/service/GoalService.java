package com.custom.checkSelf.service;

import com.custom.checkSelf.model.ViewGoal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import reactor.core.publisher.Flux;

public interface GoalService {
    Log log = LogFactory.getLog(GoalService.class);

    /**
     * 創建對應LocalDate與Goal的ViewGoal
     *
     * @param year  西元年
     * @param month 月份
     * @return ViewGoal Flux
     */
    Flux<ViewGoal> generateViewGoal(int year, int month);
}
