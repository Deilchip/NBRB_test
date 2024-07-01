package org.example.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
public class RateDTO {
    @SerializedName("Cur_ID")
    private Long curId;
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    @SerializedName("Date")
    private Date date;
    @SerializedName("Cur_Abbreviation")
    private String curAbbreviation;
    @SerializedName("Cur_Scale")
    private Integer curScale;
    @SerializedName("Cur_Name")
    private String curName;
    @SerializedName("Cur_OfficialRate")
    private Double curOfficialRate;
}