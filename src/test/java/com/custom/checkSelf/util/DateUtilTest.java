package com.custom.checkSelf.util;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DateUtilTest {

    @Test
    void getAllDaysInMonth() {
        int year = 2023;
        int month = 6;
        LocalDate firstDayOfMonth = getFirstDayOfMonth(year, month);
        int remainDays = 29;

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
        assertEquals(expect, result);
    }

    private LocalDate getFirstDayOfMonth(int year, int month) {
        return LocalDate.of(year, month, 1);
    }
}