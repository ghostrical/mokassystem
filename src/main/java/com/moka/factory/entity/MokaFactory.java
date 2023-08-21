package com.moka.factory.entity;

import com.moka.common.entity.MokaCommonLocation;
import com.moka.persnl.entity.MokaPersnl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class MokaFactory {

    @Id
    private String factorySerialNum;

    @Column(length = 50)
    private String factoryName;

    @Column(length = 1000)
    private String factoryAddr;

    @ManyToOne
    @JoinColumn(name = "factory_location_code")
    private MokaCommonLocation mokaCommonLocation;

    @Column(length = 14)
    private String factoryPhone;

}
