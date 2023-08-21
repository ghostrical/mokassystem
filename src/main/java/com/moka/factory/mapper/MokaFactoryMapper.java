package com.moka.factory.mapper;


import com.moka.factory.dto.MokaFactoryPatchDto;
import com.moka.factory.dto.MokaFactoryPostDto;
import com.moka.factory.dto.MokaFactoryResponseDto;
import com.moka.factory.entity.MokaFactory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MokaFactoryMapper {

    @Mapping(source = "factoryLocationCode" , target = "mokaCommonLocation.commonLocationCode")
    MokaFactory mokaFactoryPostDtoToMokaFactory(MokaFactoryPostDto mokaFactoryPostDto);

    @Mapping(source = "factoryLocationCode" , target = "mokaCommonLocation.commonLocationCode")
    MokaFactory mokaFactoryPatchDtoToMokaFactory(MokaFactoryPatchDto mokaFactoryPatchDto);

    @Mapping(source = "mokaCommonLocation.commonLocationCode" , target = "factoryLocationCode")
    MokaFactoryResponseDto mokaFactoryToMokaFactoryResponseDto(MokaFactory mokaFactory);

    default List<MokaFactoryResponseDto> mokaFactoriesToMokaFactoryResponseDtos(
            List<MokaFactory> mokaFactories) {

        return mokaFactories.stream()
                .map(mokaFactory -> MokaFactoryResponseDto
                        .builder()
                        .factorySerialNum(mokaFactory.getFactorySerialNum())
                        .factoryName(mokaFactory.getFactoryName())
                        .factoryLocationCode(mokaFactory.getMokaCommonLocation().getCommonLocationCode())
                        .factoryAddr(mokaFactory.getFactoryAddr())
                        .factoryPhone(mokaFactory.getFactoryPhone())
                        .build()
                ).collect(Collectors.toList());

    }
}
