package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "rates")
public class Rate {
    @Id
    private Long curId;
    private Date date;
    private String curAbbreviation;
    private Integer curScale;
    private String curName;
    private Double curOfficialRate;
}