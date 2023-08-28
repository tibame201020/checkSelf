package com.custom.checkSelf.util;

import reactor.core.publisher.Flux;

import java.time.LocalDate;


/**
 * Date工具類別
 */
public class DateUtil {

    /**
     * 取得Flux<LocalDate>
     *
     * @param year
     * @param month
     * @return Flux<LocalDate>
     */
    public static Flux<LocalDate> getAllDaysInMonth(int year, int month) {
        return Flux.range(1, getDaysOfMonth(year, month))
                .map(day -> LocalDate.of(year, month, day));
    }

    /**
     * 取得當月份天數
     *
     * @param year
     * @param month
     * @return int 天數
     */
    public static int getDaysOfMonth(int year, int month) {
        return LocalDate.of(year, month, 1).lengthOfMonth();
    }
}
