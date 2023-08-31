package com.custom.checkSelf.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.random.RandomGenerator;


class DateUtilTest {

    private final Log log = LogFactory.getLog(this.getClass());

    @Test
    void getAllDaysInMonth() {
        int year = RandomGenerator.getDefault().nextInt(1991, 2023);
        int month = RandomGenerator.getDefault().nextInt(1, 12);

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        int remainDays = LocalDate.of(year, month, 1).lengthOfMonth() - 1;

        Flux<LocalDate> allDaysInMonth = DateUtil.getAllDaysInMonth(year, month).log();

        StepVerifier.create(allDaysInMonth)
                .expectNext(firstDayOfMonth)
                .expectNextCount(remainDays)
                .verifyComplete();
    }


}