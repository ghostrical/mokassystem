package com.moka.common.controller;


import com.moka.common.dto.*;
import com.moka.common.entity.MokaCommonRankType;
import com.moka.common.mapper.CommonMapper;
import com.moka.common.service.MokaCommonRankTypeService;
import com.moka.utils.UriCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/common/ranktype")
@Validated
public class MokaCommonRankTypeController {

    private final static String MOKA_COMMON_RANK_TYPE_DEFAULT_URL = "/v1/common/ranktype";

    private final MokaCommonRankTypeService mokaCommonRankTypeService;

    private final CommonMapper mapper;

    public MokaCommonRankTypeController(MokaCommonRankTypeService mokaCommonRankTypeService, CommonMapper mapper) {
        this.mokaCommonRankTypeService = mokaCommonRankTypeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postCommonRankType(@Valid @RequestBody MokaCommonRankTypePostDto requestBody){
        MokaCommonRankType mokaCommonRankType = mapper.mokaCommonRankTypePostDtoToMokaCommonRankType(requestBody);

        MokaCommonRankType createdMokaCommonRankType =
                mokaCommonRankTypeService.createMokaCommonRankType(mokaCommonRankType);

        URI location = UriCreator.createUri(MOKA_COMMON_RANK_TYPE_DEFAULT_URL, createdMokaCommonRankType.getCommonRankTypeCode());

        //http://localhost:8080/moka/v1/common/ranktype
//        {
//            "commonRankTypeCode":"RTA1",
//                "commonRankTypeName":"회장"
//        }

        return new ResponseEntity<>(
                mapper.mokaCommonRankTypeToMokaCommonRankTypeResponseDto (createdMokaCommonRankType)
                , HttpStatus.CREATED);

    }

    @PatchMapping("/{commonRankTypeCode}")
    public ResponseEntity patchCommonRankType(
            @PathVariable("commonRankTypeCode") String commonRankTypeCode,
            @Valid @RequestBody MokaCommonRankTypePatchDto requestBody ){

        requestBody.setCommonRankTypeCode(commonRankTypeCode);

        MokaCommonRankType mokaCommonRankType =
                mokaCommonRankTypeService.updateMokaCommonRankType(mapper.mokaCommonRankTypePatchDtoToMokaCommonRankType(requestBody));

        // http://localhost:8080/moka/v1/common/ranktype/RTA1
//        {
//            "commonRankTypeCode":"RTA1",
//                "commonRankTypeName":"회장"
//        }

        return new ResponseEntity<>(
                mapper.mokaCommonRankTypeToMokaCommonRankTypeResponseDto (mokaCommonRankType)
                , HttpStatus.OK);
    }

    @GetMapping("/{commonRankTypeCode}")
    public ResponseEntity getMokaCommonRankType(
            @PathVariable("commonRankTypeCode") String commonRankTypeCode ){
        MokaCommonRankType mokaCommonRankType =
                mokaCommonRankTypeService.findMokaCommonRankType(commonRankTypeCode);

        // http://localhost:8080/moka/v1/common/ranktype/RTA3


        return new ResponseEntity<>(
                mapper.mokaCommonRankTypeToMokaCommonRankTypeResponseDto (mokaCommonRankType)
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMokaCommonRankTypes(){
        List<MokaCommonRankType> mokaCommonRankTypes =
                mokaCommonRankTypeService.findMokaCommonRankTypes();

        List<MokaCommonRankTypeResponseDto> response =
                mokaCommonRankTypes.stream()
                        .map(mokaCommonRankType -> mapper.mokaCommonRankTypeToMokaCommonRankTypeResponseDto(mokaCommonRankType))
                        .collect(Collectors.toList());

        // http://localhost:8080/moka/v1/common/ranktype

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{commonRankTypeCode}")
    public ResponseEntity deleteMokaCommonRankType(
            @PathVariable("commonRankTypeCode") String commonRankTypeCode){
        mokaCommonRankTypeService.deleteMokaCommonRankType(commonRankTypeCode);

// http://localhost:8080/moka/v1/common/ranktype/RTAB

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
