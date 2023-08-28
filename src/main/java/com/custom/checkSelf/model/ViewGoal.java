package com.custom.checkSelf.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 對應Schedule與目標
 */
@Data
public class ViewGoal implements Serializable {
    private Goal goal;
    private LocalDate date;
    private String ps;
}
