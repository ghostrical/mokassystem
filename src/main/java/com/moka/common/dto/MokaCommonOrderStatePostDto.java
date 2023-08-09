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
public class MokaCommonOrderStatePostDto {

    @NotBlank
    private String commonOrderStateCode;

    private String commonOrderStateName;

    public MokaCommonOrderStatePostDto(String commonOrderStateName) {
        this.commonOrderStateName = commonOrderStateName;
    }

}
