package com.moka.common.controller;


import com.moka.common.dto.MokaCommonLocationPatchDto;
import com.moka.common.dto.MokaCommonLocationPostDto;
import com.moka.common.dto.MokaCommonLocationResponseDto;
import com.moka.common.entity.MokaCommonLocation;
import com.moka.common.mapper.CommonMapper;
import com.moka.common.service.MokaCommonLocationService;
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
@RequestMapping("/v1/common/location")
@Validated
public class MokaCommonLocationController {

    private final static String MOKA_COMMON_LOCATION_DEFAULT_URL = "/v1/common/location";

    private final MokaCommonLocationService mokaCommonLocationService;

    private final CommonMapper mapper;

    public MokaCommonLocationController(MokaCommonLocationService mokaCommonLocationService, CommonMapper mapper) {
        this.mokaCommonLocationService = mokaCommonLocationService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postCommonLocation(@Valid @RequestBody MokaCommonLocationPostDto requestBody){
        MokaCommonLocation mokaCommonLocation = mapper.mokaCommonLocationPostDtoToMokaCommonLocation(requestBody);

        MokaCommonLocation createdMokaCommonLocation =
                mokaCommonLocationService.createMokaCommonLocation(mokaCommonLocation);

        URI location = UriCreator.createUri(MOKA_COMMON_LOCATION_DEFAULT_URL, createdMokaCommonLocation.getCommonLocationCode());

        //http://localhost:8080/moka/v1/common/location
//        {
//            "commonLocationCode":"L001",
//                "commonLocationName":"서울 김포시",
//                "commonLocationMapx":2.56,
//                "commonLocationMapy":10.67
//        }
//     그런데 POST는 3개만 넣고 PK는 자동증가하도록 만들긴 해야함.

        return new ResponseEntity<>(
                mapper.mokaCommonLocationToMokaCommonLocationResponseDto (createdMokaCommonLocation)
                , HttpStatus.CREATED);

    }

    @PatchMapping("/{commonLocationCode}")
    public ResponseEntity patchCommonLocation(
            @PathVariable("commonLocationCode") String commonLocationCode,
            @Valid @RequestBody MokaCommonLocationPatchDto requestBody ){

        requestBody.setCommonLocationCode(commonLocationCode);

        MokaCommonLocation mokaCommonLocation =
                mokaCommonLocationService.updateMokaCommonLocation(mapper.mokaCommonLocationPatchDtoToMokaCommonLocation(requestBody));

        // http://localhost:8080/moka/v1/common/location/L001
//        {
//            "commonLocationCode":"L001",
//                "commonLocationName":"경기 파주시",
//                "commonLocationMapx":12.92,
//                "commonLocationMapy":15.94
//        }

        return new ResponseEntity<>(
                mapper.mokaCommonLocationToMokaCommonLocationResponseDto (mokaCommonLocation)
                , HttpStatus.OK);
    }

    @GetMapping("/{commonLocationCode}")
    public ResponseEntity getMokaCommonLocation(
            @PathVariable("commonLocationCode") String commonLocationCode ){
        MokaCommonLocation mokaCommonLocation =
                mokaCommonLocationService.findMokaCommonLocation(commonLocationCode);

        // http://localhost:8080/moka/v1/common/location/L001


        return new ResponseEntity<>(
                mapper.mokaCommonLocationToMokaCommonLocationResponseDto (mokaCommonLocation)
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMokaCommonLocations(){
        List<MokaCommonLocation> mokaCommonLocations =
                mokaCommonLocationService.findMokaCommonLocations();

        List<MokaCommonLocationResponseDto> response =
                mokaCommonLocations.stream()
                        .map(mokaCommonLocation -> mapper.mokaCommonLocationToMokaCommonLocationResponseDto(mokaCommonLocation))
                        .collect(Collectors.toList());

        // http://localhost:8080/moka/v1/common/location

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{commonLocationCode}")
    public ResponseEntity deleteMokaCommonLocation(
            @PathVariable("commonLocationCode") String commonLocationCode){
        mokaCommonLocationService.deleteMokaCommonLocation(commonLocationCode);

// http://localhost:8080/moka/v1/common/location/L004

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
