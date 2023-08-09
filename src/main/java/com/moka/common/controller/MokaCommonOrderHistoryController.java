package com.moka.common.controller;


import com.moka.common.dto.MokaCommonOrderHistoryPatchDto;
import com.moka.common.dto.MokaCommonOrderHistoryPostDto;
import com.moka.common.dto.MokaCommonOrderHistoryResponseDto;
import com.moka.common.entity.MokaCommonOrderHistory;
import com.moka.common.mapper.CommonMapper;
import com.moka.common.service.MokaCommonOrderHistoryService;
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
@RequestMapping("/v1/common/orderhistory")
@Validated
public class MokaCommonOrderHistoryController {

    private final static String MOKA_COMMON_ORDER_HISTORY_DEFAULT_URL = "/v1/common/orderhistory";

    private final MokaCommonOrderHistoryService mokaCommonOrderHistoryService;

    private final CommonMapper mapper;

    public MokaCommonOrderHistoryController(MokaCommonOrderHistoryService mokaCommonOrderHistoryService, CommonMapper mapper) {
        this.mokaCommonOrderHistoryService = mokaCommonOrderHistoryService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postCommonOrderHistory(@Valid @RequestBody MokaCommonOrderHistoryPostDto requestBody){

        System.out.println("DTO L1 : "+requestBody.getCommonOrderHistoryCode());
        System.out.println("DTO L2 : "+requestBody.getCommonOrderHistoryCode1());
        System.out.println("DTO DTTM : "+requestBody.getCommonOrderHistorySettime());


        MokaCommonOrderHistory mokaCommonOrderHistory = mapper.mokaCommonOrderHistoryPostDtoToMokaCommonOrderHistory(requestBody);

        MokaCommonOrderHistory createdMokaCommonOrderHistory =
                mokaCommonOrderHistoryService.createMokaCommonOrderHistory(mokaCommonOrderHistory);

        URI location = UriCreator.createUri(MOKA_COMMON_ORDER_HISTORY_DEFAULT_URL, createdMokaCommonOrderHistory.getCommonOrderHistoryCode());

        //http://localhost:8080/moka/v1/common/location
//        {
//            "commonLocationCode":"L001",
//                "commonLocationName":"서울 김포시",
//                "commonLocationMapx":2.56,
//                "commonLocationMapy":10.67
//        }
//     그런데 POST는 3개만 넣고 PK는 자동증가하도록 만들긴 해야함.

        return new ResponseEntity<>(
                mapper.mokaCommonOrderHistoryToMokaCommonOrderHistoryResponseDto (createdMokaCommonOrderHistory)
                , HttpStatus.CREATED);

    }

    @PatchMapping("/{commonOrderHistoryCode}")
    public ResponseEntity patchCommonOrderHistory(
            @PathVariable("commonOrderHistoryCode") long commonOrderHistoryCode,
            @Valid @RequestBody MokaCommonOrderHistoryPatchDto requestBody ){

        requestBody.setCommonOrderHistoryCode(commonOrderHistoryCode);

        MokaCommonOrderHistory mokaCommonOrderHistory =
                mokaCommonOrderHistoryService.updateMokaCommonOrderHistory(mapper.mokaCommonOrderHistoryPatchDtoToMokaCommonOrderHistory(requestBody));

        // http://localhost:8080/moka/v1/common/location/L001
//        {
//            "commonLocationCode":"L001",
//                "commonLocationName":"경기 파주시",
//                "commonLocationMapx":12.92,
//                "commonLocationMapy":15.94
//        }

        return new ResponseEntity<>(
                mapper.mokaCommonOrderHistoryToMokaCommonOrderHistoryResponseDto (mokaCommonOrderHistory)
                , HttpStatus.OK);
    }

    @GetMapping("/{commonOrderHistoryCode}")
    public ResponseEntity getMokaCommonOrderHistory(
            @PathVariable("commonOrderHistoryCode") long commonOrderHistoryCode ){
        MokaCommonOrderHistory mokaCommonOrderHistory =
                mokaCommonOrderHistoryService.findMokaCommonOrderHistory(commonOrderHistoryCode);

        // http://localhost:8080/moka/v1/common/location/L001


        return new ResponseEntity<>(
                mapper.mokaCommonOrderHistoryToMokaCommonOrderHistoryResponseDto (mokaCommonOrderHistory)
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMokaCommonOrderHistorys(){
        List<MokaCommonOrderHistory> mokaCommonOrderHistorys =
                mokaCommonOrderHistoryService.findMokaCommonOrderHistorys();

        List<MokaCommonOrderHistoryResponseDto> response =
                mokaCommonOrderHistorys.stream()
                        .map(mokaCommonOrderHistory -> mapper.mokaCommonOrderHistoryToMokaCommonOrderHistoryResponseDto(mokaCommonOrderHistory))
                        .collect(Collectors.toList());

        // http://localhost:8080/moka/v1/common/location

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{commonOrderHistoryCode}")
    public ResponseEntity deleteMokaCommonOrderHistory(
            @PathVariable("commonOrderHistoryCode") long commonOrderHistoryCode){
        mokaCommonOrderHistoryService.deleteMokaCommonOrderHistory(commonOrderHistoryCode);

// http://localhost:8080/moka/v1/common/location/L004

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
