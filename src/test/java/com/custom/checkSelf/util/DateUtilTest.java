package com.custom.checkSelf.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DateUtilTest {

    private final Log log = LogFactory.getLog(this.getClass());

    @Test
    void getAllDaysInMonth() {
        int year = 2023;
        int month = 6;
        LocalDate firstDayOfMonth = getFirstDayOfMonth(year, month);
        int remainDays = DateUtil.getDaysOfMonth(year, month) - 1;

        Flux<LocalDate> allDaysInMonth = DateUtil.getAllDaysInMonth(year, month).log();

        StepVerifier.create(allDaysInMonth)
                .expectNext(firstDayOfMonth)
                .expectNextCount(remainDays)
                .verifyComplete();
    }

    @Test
    void getDaysOfMonth() {
        int year = 2023;
        int month = 6;
        int expect = 30;

        int result = DateUtil.getDaysOfMonth(year, month);

        log.info("expect: " + expect);
        log.info("result: " + result);

        assertEquals(expect, result);
    }

    private LocalDate getFirstDayOfMonth(int year, int month) {
        return LocalDate.of(year, month, 1);
    }
}