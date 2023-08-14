package com.moka.common.mapper;

import com.moka.common.dto.MokaCommonLocationPatchDto;
import com.moka.common.dto.MokaCommonLocationPostDto;
import com.moka.common.dto.MokaCommonLocationResponseDto;
import com.moka.common.dto.MokaCommonOrderHistoryPatchDto;
import com.moka.common.dto.MokaCommonOrderHistoryPostDto;
import com.moka.common.dto.MokaCommonOrderHistoryResponseDto;
import com.moka.common.dto.MokaCommonOrderStatePatchDto;
import com.moka.common.dto.MokaCommonOrderStatePostDto;
import com.moka.common.dto.MokaCommonOrderStateResponseDto;
import com.moka.common.dto.MokaCommonPersnlTypePatchDto;
import com.moka.common.dto.MokaCommonPersnlTypePostDto;
import com.moka.common.dto.MokaCommonPersnlTypeResponseDto;
import com.moka.common.dto.MokaCommonRankTypePatchDto;
import com.moka.common.dto.MokaCommonRankTypePostDto;
import com.moka.common.dto.MokaCommonRankTypeResponseDto;
import com.moka.common.entity.MokaCommonLocation;
import com.moka.common.entity.MokaCommonOrderHistory;
import com.moka.common.entity.MokaCommonOrderState;
import com.moka.common.entity.MokaCommonPersnlType;
import com.moka.common.entity.MokaCommonRankType;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-15T02:11:54+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.18 (Azul Systems, Inc.)"
)
@Component
public class CommonMapperImpl implements CommonMapper {

    @Override
    public MokaCommonPersnlType mokaCommonPersnlTypePostDtoToMokaCommonPersnlType(MokaCommonPersnlTypePostDto mokaCommonPersnlTypePostDto) {
        if ( mokaCommonPersnlTypePostDto == null ) {
            return null;
        }

        MokaCommonPersnlType mokaCommonPersnlType = new MokaCommonPersnlType();

        mokaCommonPersnlType.setCommonPersnlTypeCode( mokaCommonPersnlTypePostDto.getCommonPersnlTypeCode() );
        mokaCommonPersnlType.setCommonPersnlTypeName( mokaCommonPersnlTypePostDto.getCommonPersnlTypeName() );

        return mokaCommonPersnlType;
    }

    @Override
    public MokaCommonPersnlType mokaCommonPersnlTypePatchDtoToMokaCommonPersnlType(MokaCommonPersnlTypePatchDto mokaCommonPersnlTypePatchDto) {
        if ( mokaCommonPersnlTypePatchDto == null ) {
            return null;
        }

        MokaCommonPersnlType mokaCommonPersnlType = new MokaCommonPersnlType();

        mokaCommonPersnlType.setCommonPersnlTypeCode( mokaCommonPersnlTypePatchDto.getCommonPersnlTypeCode() );
        mokaCommonPersnlType.setCommonPersnlTypeName( mokaCommonPersnlTypePatchDto.getCommonPersnlTypeName() );

        return mokaCommonPersnlType;
    }

    @Override
    public MokaCommonPersnlTypeResponseDto mokaCommonPersnlTypeToMokaCommonPersnlTypeResponseDto(MokaCommonPersnlType mokaCommonPersnlType) {
        if ( mokaCommonPersnlType == null ) {
            return null;
        }

        MokaCommonPersnlTypeResponseDto mokaCommonPersnlTypeResponseDto = new MokaCommonPersnlTypeResponseDto();

        mokaCommonPersnlTypeResponseDto.setCommonPersnlTypeCode( mokaCommonPersnlType.getCommonPersnlTypeCode() );
        mokaCommonPersnlTypeResponseDto.setCommonPersnlTypeName( mokaCommonPersnlType.getCommonPersnlTypeName() );

        return mokaCommonPersnlTypeResponseDto;
    }

    @Override
    public MokaCommonRankType mokaCommonRankTypePostDtoToMokaCommonRankType(MokaCommonRankTypePostDto mokaCommonRankTypePostDto) {
        if ( mokaCommonRankTypePostDto == null ) {
            return null;
        }

        MokaCommonRankType mokaCommonRankType = new MokaCommonRankType();

        mokaCommonRankType.setCommonRankTypeCode( mokaCommonRankTypePostDto.getCommonRankTypeCode() );
        mokaCommonRankType.setCommonRankTypeName( mokaCommonRankTypePostDto.getCommonRankTypeName() );

        return mokaCommonRankType;
    }

    @Override
    public MokaCommonRankType mokaCommonRankTypePatchDtoToMokaCommonRankType(MokaCommonRankTypePatchDto mokaCommonRankTypePatchDto) {
        if ( mokaCommonRankTypePatchDto == null ) {
            return null;
        }

        MokaCommonRankType mokaCommonRankType = new MokaCommonRankType();

        mokaCommonRankType.setCommonRankTypeCode( mokaCommonRankTypePatchDto.getCommonRankTypeCode() );
        mokaCommonRankType.setCommonRankTypeName( mokaCommonRankTypePatchDto.getCommonRankTypeName() );

        return mokaCommonRankType;
    }

    @Override
    public MokaCommonRankTypeResponseDto mokaCommonRankTypeToMokaCommonRankTypeResponseDto(MokaCommonRankType mokaCommonRankType) {
        if ( mokaCommonRankType == null ) {
            return null;
        }

        MokaCommonRankTypeResponseDto mokaCommonRankTypeResponseDto = new MokaCommonRankTypeResponseDto();

        mokaCommonRankTypeResponseDto.setCommonRankTypeCode( mokaCommonRankType.getCommonRankTypeCode() );
        mokaCommonRankTypeResponseDto.setCommonRankTypeName( mokaCommonRankType.getCommonRankTypeName() );

        return mokaCommonRankTypeResponseDto;
    }

    @Override
    public MokaCommonOrderState mokaCommonOrderStatePostDtoToMokaCommonOrderState(MokaCommonOrderStatePostDto mokaCommonOrderStatePostDto) {
        if ( mokaCommonOrderStatePostDto == null ) {
            return null;
        }

        MokaCommonOrderState mokaCommonOrderState = new MokaCommonOrderState();

        mokaCommonOrderState.setCommonOrderStateCode( mokaCommonOrderStatePostDto.getCommonOrderStateCode() );
        mokaCommonOrderState.setCommonOrderStateName( mokaCommonOrderStatePostDto.getCommonOrderStateName() );

        return mokaCommonOrderState;
    }

    @Override
    public MokaCommonOrderState mokaCommonOrderStatePatchDtoToMokaCommonOrderState(MokaCommonOrderStatePatchDto mokaCommonOrderStatePatchDto) {
        if ( mokaCommonOrderStatePatchDto == null ) {
            return null;
        }

        MokaCommonOrderState mokaCommonOrderState = new MokaCommonOrderState();

        mokaCommonOrderState.setCommonOrderStateCode( mokaCommonOrderStatePatchDto.getCommonOrderStateCode() );
        mokaCommonOrderState.setCommonOrderStateName( mokaCommonOrderStatePatchDto.getCommonOrderStateName() );

        return mokaCommonOrderState;
    }

    @Override
    public MokaCommonOrderStateResponseDto mokaCommonOrderStateToMokaCommonOrderStateResponseDto(MokaCommonOrderState mokaCommonOrderState) {
        if ( mokaCommonOrderState == null ) {
            return null;
        }

        MokaCommonOrderStateResponseDto mokaCommonOrderStateResponseDto = new MokaCommonOrderStateResponseDto();

        mokaCommonOrderStateResponseDto.setCommonOrderStateCode( mokaCommonOrderState.getCommonOrderStateCode() );
        mokaCommonOrderStateResponseDto.setCommonOrderStateName( mokaCommonOrderState.getCommonOrderStateName() );

        return mokaCommonOrderStateResponseDto;
    }

    @Override
    public MokaCommonLocation mokaCommonLocationPostDtoToMokaCommonLocation(MokaCommonLocationPostDto mokaCommonLocationPostDto) {
        if ( mokaCommonLocationPostDto == null ) {
            return null;
        }

        MokaCommonLocation mokaCommonLocation = new MokaCommonLocation();

        mokaCommonLocation.setCommonLocationName( mokaCommonLocationPostDto.getCommonLocationName() );
        mokaCommonLocation.setCommonLocationMapx( mokaCommonLocationPostDto.getCommonLocationMapx() );
        mokaCommonLocation.setCommonLocationMapy( mokaCommonLocationPostDto.getCommonLocationMapy() );

        return mokaCommonLocation;
    }

    @Override
    public MokaCommonLocation mokaCommonLocationPatchDtoToMokaCommonLocation(MokaCommonLocationPatchDto mokaCommonLocationPatchDto) {
        if ( mokaCommonLocationPatchDto == null ) {
            return null;
        }

        MokaCommonLocation mokaCommonLocation = new MokaCommonLocation();

        mokaCommonLocation.setCommonLocationCode( mokaCommonLocationPatchDto.getCommonLocationCode() );
        mokaCommonLocation.setCommonLocationName( mokaCommonLocationPatchDto.getCommonLocationName() );
        mokaCommonLocation.setCommonLocationMapx( mokaCommonLocationPatchDto.getCommonLocationMapx() );
        mokaCommonLocation.setCommonLocationMapy( mokaCommonLocationPatchDto.getCommonLocationMapy() );

        return mokaCommonLocation;
    }

    @Override
    public MokaCommonLocationResponseDto mokaCommonLocationToMokaCommonLocationResponseDto(MokaCommonLocation mokaCommonLocation) {
        if ( mokaCommonLocation == null ) {
            return null;
        }

        MokaCommonLocationResponseDto mokaCommonLocationResponseDto = new MokaCommonLocationResponseDto();

        mokaCommonLocationResponseDto.setCommonLocationCode( mokaCommonLocation.getCommonLocationCode() );
        mokaCommonLocationResponseDto.setCommonLocationName( mokaCommonLocation.getCommonLocationName() );
        mokaCommonLocationResponseDto.setCommonLocationMapx( mokaCommonLocation.getCommonLocationMapx() );
        mokaCommonLocationResponseDto.setCommonLocationMapy( mokaCommonLocation.getCommonLocationMapy() );

        return mokaCommonLocationResponseDto;
    }

    @Override
    public MokaCommonOrderHistory mokaCommonOrderHistoryPostDtoToMokaCommonOrderHistory(MokaCommonOrderHistoryPostDto mokaCommonOrderHistoryPostDto) {
        if ( mokaCommonOrderHistoryPostDto == null ) {
            return null;
        }

        MokaCommonOrderHistory mokaCommonOrderHistory = new MokaCommonOrderHistory();

        mokaCommonOrderHistory.setCommonOrderHistoryCode( mokaCommonOrderHistoryPostDto.getCommonOrderHistoryCode() );
        mokaCommonOrderHistory.setCommonOrderHistoryCode1( mokaCommonOrderHistoryPostDto.getCommonOrderHistoryCode1() );
        mokaCommonOrderHistory.setCommonOrderHistoryBefore( mokaCommonOrderHistoryPostDto.getCommonOrderHistoryBefore() );
        if ( mokaCommonOrderHistoryPostDto.getCommonOrderHistoryAfter() != null ) {
            mokaCommonOrderHistory.setCommonOrderHistoryAfter( Double.parseDouble( mokaCommonOrderHistoryPostDto.getCommonOrderHistoryAfter() ) );
        }
        mokaCommonOrderHistory.setCommonOrderHistorySettime( mokaCommonOrderHistoryPostDto.getCommonOrderHistorySettime() );

        return mokaCommonOrderHistory;
    }

    @Override
    public MokaCommonOrderHistory mokaCommonOrderHistoryPatchDtoToMokaCommonOrderHistory(MokaCommonOrderHistoryPatchDto mokaCommonOrderHistoryPatchDto) {
        if ( mokaCommonOrderHistoryPatchDto == null ) {
            return null;
        }

        MokaCommonOrderHistory mokaCommonOrderHistory = new MokaCommonOrderHistory();

        mokaCommonOrderHistory.setCommonOrderHistoryCode( mokaCommonOrderHistoryPatchDto.getCommonOrderHistoryCode() );
        mokaCommonOrderHistory.setCommonOrderHistoryCode1( mokaCommonOrderHistoryPatchDto.getCommonOrderHistoryCode1() );
        mokaCommonOrderHistory.setCommonOrderHistoryBefore( mokaCommonOrderHistoryPatchDto.getCommonOrderHistoryBefore() );
        if ( mokaCommonOrderHistoryPatchDto.getCommonOrderHistoryAfter() != null ) {
            mokaCommonOrderHistory.setCommonOrderHistoryAfter( Double.parseDouble( mokaCommonOrderHistoryPatchDto.getCommonOrderHistoryAfter() ) );
        }
        mokaCommonOrderHistory.setCommonOrderHistorySettime( mokaCommonOrderHistoryPatchDto.getCommonOrderHistorySettime() );

        return mokaCommonOrderHistory;
    }

    @Override
    public MokaCommonOrderHistoryResponseDto mokaCommonOrderHistoryToMokaCommonOrderHistoryResponseDto(MokaCommonOrderHistory mokaCommonOrderHistory) {
        if ( mokaCommonOrderHistory == null ) {
            return null;
        }

        MokaCommonOrderHistoryResponseDto mokaCommonOrderHistoryResponseDto = new MokaCommonOrderHistoryResponseDto();

        mokaCommonOrderHistoryResponseDto.setCommonOrderHistoryCode( mokaCommonOrderHistory.getCommonOrderHistoryCode() );
        mokaCommonOrderHistoryResponseDto.setCommonOrderHistoryCode1( mokaCommonOrderHistory.getCommonOrderHistoryCode1() );
        mokaCommonOrderHistoryResponseDto.setCommonOrderHistoryBefore( mokaCommonOrderHistory.getCommonOrderHistoryBefore() );
        mokaCommonOrderHistoryResponseDto.setCommonOrderHistoryAfter( String.valueOf( mokaCommonOrderHistory.getCommonOrderHistoryAfter() ) );
        mokaCommonOrderHistoryResponseDto.setCommonOrderHistorySettime( mokaCommonOrderHistory.getCommonOrderHistorySettime() );

        return mokaCommonOrderHistoryResponseDto;
    }
}
