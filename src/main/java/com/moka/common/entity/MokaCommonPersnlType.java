package com.moka.common.entity;

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

    public MokaCommonPersnlType(String commonPersnlTypeCode, String commonPersnlTypeName) {
        this.commonPersnlTypeCode = commonPersnlTypeCode;
        this.commonPersnlTypeName = commonPersnlTypeName;
    }
}
