package com.moka.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class MokaCommonOrderHistory {

    @Id
    private long commonOrderHistoryCode;

    @Column(length = 10)
    private String commonOrderHistoryCode1;

    @Column(length = 4)
    private String commonOrderHistoryBefore;

    @Column(length = 4)
    private double commonOrderHistoryAfter;

    @Column
    private LocalDateTime commonOrderHistorySettime;


}
