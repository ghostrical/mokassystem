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
import java.util.ArrayList;
import java.util.List;

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

    @Column(length = 30, nullable = false)
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

    // @ElementCollection 애너테이션을 이용해 사용자 등록 시, 사용자의 권한을 등록하기 위한 권한 테이블을 생성합니다.
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    ///////// 만약 이미 테이블 있다고 하면 한번 드롭해볼지도

//    @ElementCollection(fetch = FetchType.EAGER)
//    private List<String> roles = new ArrayList<>();
//    을 추가했을 때 member_roles 라는 테이블이 생김.
//    member_roles는 member_member_id long형과 roles String형 컬럼이 생성된다.

    ////////

//    @OneToOne(mappedBy = "mokaPersnl")
//    private MokaCommonRankType mokaCommonRankType;

}
