package com.moka.factory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MokaFactoryPatchDto {
    private String factorySerialNum;
    private String factoryName;
    private String factoryAddr;
    private String factoryLocationCode;
    private String factoryPhone;
}
