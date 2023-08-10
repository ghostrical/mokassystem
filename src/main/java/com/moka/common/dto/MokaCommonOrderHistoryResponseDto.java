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
public class MokaCommonOrderHistoryResponseDto {

    private long commonOrderHistoryCode;

    private String commonOrderHistoryCode1;

    private String commonOrderHistoryBefore;

    private String commonOrderHistoryAfter;

    private LocalDateTime commonOrderHistorySettime;


}
