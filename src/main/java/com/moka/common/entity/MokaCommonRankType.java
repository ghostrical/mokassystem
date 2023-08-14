package com.moka.common.entity;

import com.moka.persnl.entity.MokaPersnl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class MokaCommonRankType {

    @Id
    private String commonRankTypeCode;

    @Column(length = 50)
    private String commonRankTypeName;

//    @OneToOne
//    @JoinColumn(name = "persnl_rank_type_code")
//    private MokaPersnl mokaPersnl;

    @OneToOne(mappedBy = "mokaCommonRankType")
    private MokaPersnl mokaPersnl;

    public MokaCommonRankType(String commonRankTypeCode, String commonRankTypeName) {
        this.commonRankTypeCode = commonRankTypeCode;
        this.commonRankTypeName = commonRankTypeName;
    }
}
