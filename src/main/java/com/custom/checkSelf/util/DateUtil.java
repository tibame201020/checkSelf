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
        return Flux.range(1, LocalDate.of(year, month, 1).lengthOfMonth())
                .map(day -> LocalDate.of(year, month, day));
    }

}
