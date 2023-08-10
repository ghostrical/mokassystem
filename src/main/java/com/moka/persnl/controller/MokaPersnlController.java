package com.moka.persnl.controller;


import com.moka.persnl.dto.MokaPersnlPatchDto;
import com.moka.persnl.dto.MokaPersnlPostDto;
import com.moka.persnl.dto.MokaPersnlResponseDto;
import com.moka.persnl.entity.MokaPersnl;
import com.moka.persnl.mapper.PersnlMapper;
import com.moka.persnl.service.MokaPersnlService;
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
@RequestMapping("/v1/persnl")
@Validated
public class MokaPersnlController {

    private final static String MOKA_PERSNL_DEFAULT_URL = "/v1/persnl";

    private final MokaPersnlService mokaPersnlService;

    private final PersnlMapper mapper;

    public MokaPersnlController(MokaPersnlService mokaPersnlService, PersnlMapper mapper) {
        this.mokaPersnlService = mokaPersnlService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postPersnl(@Valid @RequestBody MokaPersnlPostDto requestBody){
        MokaPersnl mokaPersnl = mapper.mokaPersnlPostDtoToMokaPersnl(requestBody);

        MokaPersnl createdMokaPersnl =
                mokaPersnlService.createPersnl(mokaPersnl);

        URI location = UriCreator.createUri(MOKA_PERSNL_DEFAULT_URL, createdMokaPersnl.getPersnlSerialNum());

        //http://localhost:8080/moka/v1/common/ranktype
//        {
//            "commonRankTypeCode":"RTA1",
//                "commonRankTypeName":"회장"
//        }

        return new ResponseEntity<>(
                mapper.mokaPersnlToMokaPersnlResponseDto (createdMokaPersnl)
                , HttpStatus.CREATED);

    }

    @PatchMapping("/{persnlSerialNum}")
    public ResponseEntity patchPersnl(
            @PathVariable("persnlSerialNum") String persnlSerialNum,
            @Valid @RequestBody MokaPersnlPatchDto requestBody ){

        requestBody.setPersnlSerialNum(persnlSerialNum);

        MokaPersnl mokaPersnl =
                mokaPersnlService.updateMokaPersnl(mapper.mokaPersnlPatchDtoToMokaPersnl(requestBody));

        // http://localhost:8080/moka/v1/common/ranktype/RTA1
//        {
//            "commonRankTypeCode":"RTA1",
//                "commonRankTypeName":"회장"
//        }

        return new ResponseEntity<>(
                mapper.mokaPersnlToMokaPersnlResponseDto (mokaPersnl)
                , HttpStatus.OK);
    }

    @GetMapping("/{persnlSerialNum}")
    public ResponseEntity getMokaPersnl(
            @PathVariable("persnlSerialNum") String persnlSerialNum ){
        MokaPersnl mokaPersnl =
                mokaPersnlService.findMokaPersnl(persnlSerialNum);

        // http://localhost:8080/moka/v1/common/ranktype/RTA3


        return new ResponseEntity<>(
                mapper.mokaPersnlToMokaPersnlResponseDto (mokaPersnl)
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMokaPersnls(){
        List<MokaPersnl> mokaPersnls =
                mokaPersnlService.findMokaPersnls();

        List<MokaPersnlResponseDto> response =
                mokaPersnls.stream()
                        .map(mokaPersnl -> mapper.mokaPersnlToMokaPersnlResponseDto(mokaPersnl))
                        .collect(Collectors.toList());

        // http://localhost:8080/moka/v1/common/ranktype

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{persnlSerialNum}")
    public ResponseEntity deleteMokaPersnl(
            @PathVariable("persnlSerialNum") String persnlSerialNum){
        mokaPersnlService.deleteMokaPersnl(persnlSerialNum);

// http://localhost:8080/moka/v1/common/ranktype/RTAB

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
