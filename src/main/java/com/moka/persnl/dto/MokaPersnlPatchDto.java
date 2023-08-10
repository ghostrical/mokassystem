package com.moka.persnl.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MokaPersnlPatchDto {

    private String persnlSerialNum;

    @NotBlank
    private String persnlPw;

    @NotBlank
    private String persnlResidentNum;

    private String persnlPhone;

    private String persnlName;

    private String persnlEmail;

    private String persnlAddr;

    private String persnlRankTypeCode;

    private String persnlTypeCode;

}
