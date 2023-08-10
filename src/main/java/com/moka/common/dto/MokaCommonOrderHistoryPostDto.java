package com.moka.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MokaCommonOrderHistoryPostDto {

    private long commonOrderHistoryCode;

    private String commonOrderHistoryCode1;

    private String commonOrderHistoryBefore;

    private String commonOrderHistoryAfter;

    private LocalDateTime commonOrderHistorySettime;

    public MokaCommonOrderHistoryPostDto(String commonOrderHistoryCode1, String commonOrderHistoryBefore, String commonOrderHistoryAfter, LocalDateTime commonOrderHistorySettime) {
        this.commonOrderHistoryCode1 = commonOrderHistoryCode1;
        this.commonOrderHistoryBefore = commonOrderHistoryBefore;
        this.commonOrderHistoryAfter = commonOrderHistoryAfter;
        this.commonOrderHistorySettime = commonOrderHistorySettime;
    }
}
