package com.moka.factory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MokaFactoryPostDto {
    private String factoryName;
    private String factoryAddr;
    private String factoryLocationCode;
    private String factoryPhone;
}
