package com.moka.factory.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MokaFactoryResponseDto {
    private String factorySerialNum;
    private String factoryName;
    private String factoryAddr;
    private String factoryLocationCode;
    private String factoryPhone;
}
