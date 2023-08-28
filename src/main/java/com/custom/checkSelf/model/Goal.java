package com.custom.checkSelf.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 包裝checkList 包含period isDone
 */
@Data
@Document("goal")
public class Goal implements Serializable {
    @Id
    private CheckList checkList;
    private Period period;
    private boolean isDone;
}
