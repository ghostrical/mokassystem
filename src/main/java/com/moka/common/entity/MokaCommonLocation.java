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
public class MokaCommonLocation {

    @Id
    private String commonLocationCode;

    @Column(length = 100)
    private String commonLocationName;

    @Column
    private double commonLocationMapx;

    @Column
    private double commonLocationMapy;


}
