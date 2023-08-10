package com.moka.common.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class MokaCommonOrderState {

    @Id
    private String commonOrderStateCode;

    @Column(length = 50)
    private String commonOrderStateName;

    public MokaCommonOrderState(String commonOrderStateCode, String commonOrderStateName) {
        this.commonOrderStateCode = commonOrderStateCode;
        this.commonOrderStateName = commonOrderStateName;
    }
}
