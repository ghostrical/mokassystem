package com.moka.common.mapper;

import com.moka.common.dto.*;
import com.moka.common.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommonMapper {
    MokaCommonPersnlType mokaCommonPersnlTypePostDtoToMokaCommonPersnlType(MokaCommonPersnlTypePostDto mokaCommonPersnlTypePostDto);

    MokaCommonPersnlType mokaCommonPersnlTypePatchDtoToMokaCommonPersnlType(MokaCommonPersnlTypePatchDto mokaCommonPersnlTypePatchDto);

    MokaCommonPersnlTypeResponseDto mokaCommonPersnlTypeToMokaCommonPersnlTypeResponseDto(MokaCommonPersnlType mokaCommonPersnlType);

    MokaCommonRankType mokaCommonRankTypePostDtoToMokaCommonRankType(MokaCommonRankTypePostDto mokaCommonRankTypePostDto);

    MokaCommonRankType mokaCommonRankTypePatchDtoToMokaCommonRankType(MokaCommonRankTypePatchDto mokaCommonRankTypePatchDto);

    MokaCommonRankTypeResponseDto mokaCommonRankTypeToMokaCommonRankTypeResponseDto(MokaCommonRankType mokaCommonRankType);

}
