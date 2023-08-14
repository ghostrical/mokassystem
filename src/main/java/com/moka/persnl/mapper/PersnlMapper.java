package com.moka.persnl.mapper;

import com.moka.common.entity.MokaCommonPersnlType;
import com.moka.common.entity.MokaCommonRankType;
import com.moka.persnl.dto.MokaPersnlPatchDto;
import com.moka.persnl.dto.MokaPersnlPostDto;
import com.moka.persnl.dto.MokaPersnlResponseDto;
import com.moka.persnl.entity.MokaPersnl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersnlMapper {

//    MokaPersnl mokaPersnlPostDtoToMokaPersnl(MokaPersnlPostDto mokaPersnlPostDto);
//
//    MokaPersnl mokaPersnlPatchDtoToMokaPersnl(MokaPersnlPatchDto mokaPersnlPatchDto);
//
//    MokaPersnlResponseDto mokaPersnlToMokaPersnlResponseDto(MokaPersnl mokaPersnl);

    default MokaPersnl mokaPersnlPostDtoToMokaPersnl(MokaPersnlPostDto mokaPersnlPostDto){
        MokaPersnl mokaPersnl = new MokaPersnl();

        mokaPersnl.setPersnlId(mokaPersnlPostDto.getPersnlId());
        mokaPersnl.setPersnlPw(mokaPersnlPostDto.getPersnlPw());
        mokaPersnl.setPersnlResidentNum(mokaPersnlPostDto.getPersnlResidentNum());
        mokaPersnl.setPersnlPhone(mokaPersnlPostDto.getPersnlPhone());
        mokaPersnl.setPersnlName(mokaPersnlPostDto.getPersnlName());
        mokaPersnl.setPersnlEmail(mokaPersnlPostDto.getPersnlEmail());
        mokaPersnl.setPersnlAddr(mokaPersnlPostDto.getPersnlAddr());

        MokaCommonPersnlType mokaCommonPersnlType = new MokaCommonPersnlType();
        mokaCommonPersnlType.setCommonPersnlTypeCode(mokaPersnlPostDto.getPersnlTypeCode());
        mokaPersnl.setMokaCommonPersnlType(mokaCommonPersnlType);

        MokaCommonRankType mokaCommonRankType = new MokaCommonRankType();
        mokaCommonRankType.setCommonRankTypeCode(mokaPersnlPostDto.getPersnlRankTypeCode());
        mokaPersnl.setMokaCommonRankType(mokaCommonRankType);

        // persnl는 세션 만들어지면 해봄.
        mokaPersnl.setCreatePersnl("AAAA");

        // 일단 기본 CRUD 확인 후 IP 추가해봄.
        mokaPersnl.setCreateIp("0.0.0.0");

        return mokaPersnl;

    }

    default MokaPersnl mokaPersnlPatchDtoToMokaPersnl(MokaPersnlPatchDto mokaPersnlPatchDto){
        MokaPersnl mokaPersnl = new MokaPersnl();

        mokaPersnl.setPersnlSerialNum(mokaPersnlPatchDto.getPersnlSerialNum());
        mokaPersnl.setPersnlPw(mokaPersnlPatchDto.getPersnlPw());
        mokaPersnl.setPersnlResidentNum(mokaPersnlPatchDto.getPersnlResidentNum());
        mokaPersnl.setPersnlPhone(mokaPersnlPatchDto.getPersnlPhone());
        mokaPersnl.setPersnlName(mokaPersnlPatchDto.getPersnlName());
        mokaPersnl.setPersnlEmail(mokaPersnlPatchDto.getPersnlEmail());
        mokaPersnl.setPersnlAddr(mokaPersnlPatchDto.getPersnlAddr());

        MokaCommonPersnlType mokaCommonPersnlType = new MokaCommonPersnlType();
        mokaCommonPersnlType.setCommonPersnlTypeCode(mokaPersnlPatchDto.getPersnlTypeCode());
        mokaPersnl.setMokaCommonPersnlType(mokaCommonPersnlType);

        MokaCommonRankType mokaCommonRankType = new MokaCommonRankType();
        mokaCommonRankType.setCommonRankTypeCode(mokaPersnlPatchDto.getPersnlRankTypeCode());
        mokaPersnl.setMokaCommonRankType(mokaCommonRankType);

        // persnl는 세션 만들어지면 해봄.
        mokaPersnl.setUpdatePersnl ("BBBB");

        // 일단 기본 CRUD 확인 후 IP 추가해봄.
        mokaPersnl.setUpdateIp("1.1.1.1");

        return  mokaPersnl;
    }

    default MokaPersnlResponseDto mokaPersnlToMokaPersnlResponseDto(MokaPersnl mokaPersnl) {
        MokaPersnlResponseDto mokaPersnlResponseDto = new MokaPersnlResponseDto();

        mokaPersnlResponseDto.setPersnlSerialNum(mokaPersnl.getPersnlSerialNum());
        mokaPersnlResponseDto.setPersnlId(mokaPersnl.getPersnlId());
        mokaPersnlResponseDto.setPersnlPw(mokaPersnl.getPersnlPw());
        mokaPersnlResponseDto.setPersnlResidentNum(mokaPersnl.getPersnlResidentNum());
        mokaPersnlResponseDto.setPersnlPhone(mokaPersnl.getPersnlPhone());
        mokaPersnlResponseDto.setPersnlName(mokaPersnl.getPersnlName());
        mokaPersnlResponseDto.setPersnlEmail(mokaPersnl.getPersnlEmail());
        mokaPersnlResponseDto.setPersnlAddr(mokaPersnl.getPersnlAddr());
        mokaPersnlResponseDto.setPersnlTypeCode(mokaPersnl.getMokaCommonPersnlType().getCommonPersnlTypeCode());
        mokaPersnlResponseDto.setPersnlRankTypeCode(mokaPersnl.getMokaCommonRankType().getCommonRankTypeCode());
        mokaPersnlResponseDto.setCreatePersnl(mokaPersnl.getCreatePersnl());
        mokaPersnlResponseDto.setCreateDttm(mokaPersnl.getCreateDttm());
        mokaPersnlResponseDto.setCreateIp(mokaPersnl.getCreateIp());
        mokaPersnlResponseDto.setUpdatePersnl(mokaPersnl.getUpdatePersnl());
        mokaPersnlResponseDto.setUpdateDttm(mokaPersnl.getUpdateDttm());
        mokaPersnlResponseDto.setUpdateIp(mokaPersnl.getUpdateIp());

        return mokaPersnlResponseDto;
    }
}
