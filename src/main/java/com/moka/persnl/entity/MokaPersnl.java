package com.moka.persnl.entity;

import com.moka.common.entity.MokaCommonPersnlType;
import com.moka.common.entity.MokaCommonRankType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MokaPersnl {

    @Id
    private String persnlSerialNum;

    @Column(length = 30, unique = true)
    private String persnlId;

    @Column(length = 30)
    private String persnlPw;

    @Column(length = 13, unique = true)
    private String persnlResidentNum;

    @Column(length = 14)
    private String persnlPhone;

    @Column(length = 20)
    private String persnlName;

    @Column(length = 30)
    private String persnlEmail;

    @Column(length = 1000)
    private String persnlAddr;

    // 외래키가 있는 곳이 연관관계의 주인이다.

    @OneToOne(mappedBy = "mokaPersnl")
    private MokaCommonPersnlType mokaCommonPersnlType;

    @OneToOne(mappedBy = "mokaPersnl")
    private MokaCommonRankType mokaCommonRankType;

}
