package com.custom.checkSelf.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * checkList 最小元件
 */
@Data
@Document("check_list")
public class CheckList implements Serializable {
    @Id
    private String name;
    private String description;
}
