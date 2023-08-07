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
public class MokaCommonRankType {

    @Id
    private String commonRankTypeCode;

    @Column(length = 50)
    private String commonRankTypeName;

    public MokaCommonRankType(String commonRankTypeCode, String commonRankTypeName) {
        this.commonRankTypeCode = commonRankTypeCode;
        this.commonRankTypeName = commonRankTypeName;
    }
}
