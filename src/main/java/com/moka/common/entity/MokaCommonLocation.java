package com.moka.common.entity;

import com.moka.factory.entity.MokaFactory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "mokaCommonLocation")
    private List<MokaFactory> mokaFactory = new ArrayList<>();


}
