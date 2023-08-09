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
public class MokaCommonLocationPatchDto {

    @NotBlank
    private String commonLocationCode;

    private String commonLocationName;

    private double commonLocationMapx;

    private double commonLocationMapy;
}
