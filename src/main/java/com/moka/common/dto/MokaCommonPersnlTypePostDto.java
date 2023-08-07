package com.moka.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MokaCommonPersnlTypePostDto {

    @NotBlank
    private String commonPersnlTypeCode;

    private String commonPersnlTypeName;

    public MokaCommonPersnlTypePostDto(String commonPersnlTypeName) {
        this.commonPersnlTypeName = commonPersnlTypeName;
    }

}
