package com.moka.common.controller;

import com.moka.common.dto.*;
import com.moka.common.entity.*;
import com.moka.common.mapper.CommonMapper;
import com.moka.common.service.MokaCommonPersnlTypeService;
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
@RequestMapping("/v1/common/persnltype")
@Validated
public class MokaCommonPersnlTypeController {

    private final static String MOKA_COMMON_PERSNL_TYPE_DEFAULT_URL = "/v1/common/persnltype";

    private final MokaCommonPersnlTypeService mokaCommonPersnlTypeService;

    private final CommonMapper mapper;

    public MokaCommonPersnlTypeController(MokaCommonPersnlTypeService mokaCommonPersnlTypeService, CommonMapper mapper) {
        this.mokaCommonPersnlTypeService = mokaCommonPersnlTypeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postCommonPersnlType(@Valid @RequestBody MokaCommonPersnlTypePostDto requestBody){
        MokaCommonPersnlType mokaCommonPersnlType = mapper.mokaCommonPersnlTypePostDtoToMokaCommonPersnlType(requestBody);

        MokaCommonPersnlType createdMokaCommonPersnlType =
                mokaCommonPersnlTypeService.createMokaCommonPersnlType(mokaCommonPersnlType);

        URI location = UriCreator.createUri(MOKA_COMMON_PERSNL_TYPE_DEFAULT_URL, createdMokaCommonPersnlType.getCommonPersnlTypeCode());

        //http://localhost:8080/moka/v1/common/persnltype
//        {
//            "commonPersnlTypeCode":"PTA001",
//                "commonPersnlTypeName":"관리자"
//        }

        return new ResponseEntity<>(
                mapper.mokaCommonPersnlTypeToMokaCommonPersnlTypeResponseDto (createdMokaCommonPersnlType)
                , HttpStatus.CREATED);

    }

    @PatchMapping("/{commonPersnlTypeCode}")
    public ResponseEntity patchCommonPersnlType(
            @PathVariable("commonPersnlTypeCode") String commonPersnlTypeCode,
            @Valid @RequestBody MokaCommonPersnlTypePatchDto requestBody ){

        requestBody.setCommonPersnlTypeCode(commonPersnlTypeCode);

        MokaCommonPersnlType mokaCommonPersnlType =
                mokaCommonPersnlTypeService.updateMokaCommonPersnlType(mapper.mokaCommonPersnlTypePatchDtoToMokaCommonPersnlType(requestBody));

        // http://localhost:8080/moka/v1/common/persnltype/PTA001
//        {
//            "commonPersnlTypeCode":"PTA001",
//                "commonPersnlTypeName":"관리자"
//        }

        return new ResponseEntity<>(
                mapper.mokaCommonPersnlTypeToMokaCommonPersnlTypeResponseDto (mokaCommonPersnlType)
                , HttpStatus.OK);
    }

    @GetMapping("/{commonPersnlTypeCode}")
    public ResponseEntity getMokaCommonPersnlType(
            @PathVariable("commonPersnlTypeCode") String commonPersnlTypeCode ){
        MokaCommonPersnlType mokaCommonPersnlType =
                mokaCommonPersnlTypeService.findMokaCommonPersnlType(commonPersnlTypeCode);

        // http://localhost:8080/moka/v1/common/persnltype/PTA001


        return new ResponseEntity<>(
                mapper.mokaCommonPersnlTypeToMokaCommonPersnlTypeResponseDto (mokaCommonPersnlType)
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMokaCommonPersnlTypes(){
        List<MokaCommonPersnlType> mokaCommonPersnlTypes =
                mokaCommonPersnlTypeService.findMokaCommonPersnlTypes();

        List<MokaCommonPersnlTypeResponseDto> response =
                mokaCommonPersnlTypes.stream()
                        .map(mokaCommonPersnlType -> mapper.mokaCommonPersnlTypeToMokaCommonPersnlTypeResponseDto(mokaCommonPersnlType))
                        .collect(Collectors.toList());

        // http://localhost:8080/moka/v1/common/persnltype

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{commonPersnlTypeCode}")
    public ResponseEntity deleteMokaCommonPersnlType(
            @PathVariable("commonPersnlTypeCode") String commonPersnlTypeCode){
        mokaCommonPersnlTypeService.deleteMokaCommonPersnlType(commonPersnlTypeCode);

// http://localhost:8080/moka/v1/common/persnltype/PTE001

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
