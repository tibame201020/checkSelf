package com.custom.checkSelf.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * 對應Schedule與目標
 */
@Data
public class ViewGoal implements Serializable {
    private List<Goal> goalList;
    private LocalDate date;
    private String ps;
}
