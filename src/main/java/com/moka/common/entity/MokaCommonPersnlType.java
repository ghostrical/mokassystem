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
public class MokaCommonPersnlType {

    @Id
    private String commonPersnlTypeCode;

    @Column(length = 50)
    private String commonPersnlTypeName;

//    @OneToOne
//    @JoinColumn(name = "persnl_type_code")
//    private MokaPersnl mokaPersnl;

    @OneToOne(mappedBy = "mokaCommonPersnlType")
    private MokaPersnl mokaPersnl;

    public MokaCommonPersnlType(String commonPersnlTypeCode, String commonPersnlTypeName) {
        this.commonPersnlTypeCode = commonPersnlTypeCode;
        this.commonPersnlTypeName = commonPersnlTypeName;
    }
}
