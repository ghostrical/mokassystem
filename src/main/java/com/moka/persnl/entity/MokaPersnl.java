package com.moka.persnl.entity;

import com.moka.common.entity.MokaCommonPersnlType;
import com.moka.common.entity.MokaCommonRankType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column
    private String createPersnl;

    @CreatedDate
    @Column(name = "create_dttm")
    private LocalDateTime createDttm = LocalDateTime.now();

    @Column
    private String createIp;

    @Column
    private String updatePersnl;

    @LastModifiedDate
    @Column(name = "update_dttm")
    private LocalDateTime updateDttm;

    @Column
    private String updateIp;

    @OneToOne
    @JoinColumn(name = "persnl_type_code")
    private MokaCommonPersnlType mokaCommonPersnlType;

    @OneToOne
    @JoinColumn(name = "persnl_rank_type_code")
    private MokaCommonRankType mokaCommonRankType;

//    @OneToOne(mappedBy = "mokaPersnl")
//    private MokaCommonRankType mokaCommonRankType;

}
