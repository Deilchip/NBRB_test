package org.example.dto;

import lombok.Data;

import java.util.Date;
@Data
public class RateDTO {

    private Long curId;
    private Date date;
    private String curAbbreviation;
    private Integer curScale;
    private String curName;
    private Double curOfficialRate;
}