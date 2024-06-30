package org.example.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private int curScale;
    private String curName;
    private double curOfficialRate;

}