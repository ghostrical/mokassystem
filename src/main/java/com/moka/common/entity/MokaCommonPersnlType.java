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

    @OneToOne
    @JoinColumn(name = "persnl_serial_num")
    private MokaPersnl mokaPersnl;

    public MokaCommonPersnlType(String commonPersnlTypeCode, String commonPersnlTypeName) {
        this.commonPersnlTypeCode = commonPersnlTypeCode;
        this.commonPersnlTypeName = commonPersnlTypeName;
    }
}
