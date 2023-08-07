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
public class MokaCommonRankTypeResponseDto {

    @NotBlank
    private String commonRankTypeCode;

    private String commonRankTypeName;

}
