package com.moka.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MokaCommonLocationPostDto {

    @NotBlank
    private String commonLocationCode;

    private String commonLocationName;

    private double commonLocationMapx;

    private double commonLocationMapy;

    public MokaCommonLocationPostDto(String commonLocationName, double commonLocationMapx, double commonLocationMapy) {
        this.commonLocationName = commonLocationName;
        this.commonLocationMapx = commonLocationMapx;
        this.commonLocationMapy = commonLocationMapy;
    }
}
