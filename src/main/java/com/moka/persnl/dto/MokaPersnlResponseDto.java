package com.moka.persnl.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MokaPersnlResponseDto {

    private String persnlSerialNum;

    private String persnlId;

    private String persnlPw;

    private String persnlResidentNum;

    private String persnlPhone;

    private String persnlName;

    private String persnlEmail;

    private String persnlAddr;

    private String persnlRankTypeCode;

    private String persnlTypeCode;

    private String createPersnl;

    private LocalDateTime createDttm;

    private String createIp;

    private String updatePersnl;

    private LocalDateTime updateDttm;

    private String updateIp;

}
