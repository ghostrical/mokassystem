package com.moka.common.controller;


import com.moka.common.dto.MokaCommonOrderStatePatchDto;
import com.moka.common.dto.MokaCommonOrderStatePostDto;
import com.moka.common.dto.MokaCommonOrderStateResponseDto;
import com.moka.common.entity.MokaCommonOrderState;
import com.moka.common.mapper.CommonMapper;
import com.moka.common.service.MokaCommonOrderStateService;
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
@RequestMapping("/v1/common/orderstate")
@Validated
public class MokaCommonOrderStateController {

    private final static String MOKA_COMMON_ORDER_STATE_DEFAULT_URL = "/v1/common/orderstate";

    private final MokaCommonOrderStateService mokaCommonOrderStateService;

    private final CommonMapper mapper;

    public MokaCommonOrderStateController(MokaCommonOrderStateService mokaCommonOrderStateService, CommonMapper mapper) {
        this.mokaCommonOrderStateService = mokaCommonOrderStateService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postCommonOrderState(@Valid @RequestBody MokaCommonOrderStatePostDto requestBody){
        MokaCommonOrderState mokaCommonOrderState = mapper.mokaCommonOrderStatePostDtoToMokaCommonOrderState(requestBody);

        MokaCommonOrderState createdMokaCommonOrderState =
                mokaCommonOrderStateService.createMokaCommonOrderState(mokaCommonOrderState);

        URI location = UriCreator.createUri(MOKA_COMMON_ORDER_STATE_DEFAULT_URL, createdMokaCommonOrderState.getCommonOrderStateCode());

        //http://localhost:8080/moka/v1/common/orderstate
//        {
//            "commonOrderStateCode":"OSA6",
//                "commonOrderStateName":"삭제"
//        }

        return new ResponseEntity<>(
                mapper.mokaCommonOrderStateToMokaCommonOrderStateResponseDto (createdMokaCommonOrderState)
                , HttpStatus.CREATED);

    }

    @PatchMapping("/{commonOrderStateCode}")
    public ResponseEntity patchCommonOrderState(
            @PathVariable("commonOrderStateCode") String commonOrderStateCode,
            @Valid @RequestBody MokaCommonOrderStatePatchDto requestBody ){

        requestBody.setCommonOrderStateCode(commonOrderStateCode);

        MokaCommonOrderState mokaCommonOrderState =
                mokaCommonOrderStateService.updateMokaCommonOrderState(mapper.mokaCommonOrderStatePatchDtoToMokaCommonOrderState(requestBody));

        // http://localhost:8080/moka/v1/common/orderstate/OSA1
//        {
//            "commonOrderStateCode":"OSA1",
//                "commonOrderStateName":"주문"
//        }

        return new ResponseEntity<>(
                mapper.mokaCommonOrderStateToMokaCommonOrderStateResponseDto (mokaCommonOrderState)
                , HttpStatus.OK);
    }

    @GetMapping("/{commonOrderStateCode}")
    public ResponseEntity getMokaCommonOrderState(
            @PathVariable("commonOrderStateCode") String commonOrderStateCode ){
        MokaCommonOrderState mokaCommonOrderState =
                mokaCommonOrderStateService.findMokaCommonOrderState(commonOrderStateCode);

        // http://localhost:8080/moka/v1/common/orderstate/OSA2


        return new ResponseEntity<>(
                mapper.mokaCommonOrderStateToMokaCommonOrderStateResponseDto (mokaCommonOrderState)
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMokaCommonOrderStates(){
        List<MokaCommonOrderState> mokaCommonOrderStates =
                mokaCommonOrderStateService.findMokaCommonOrderStates();

        List<MokaCommonOrderStateResponseDto> response =
                mokaCommonOrderStates.stream()
                        .map(mokaCommonOrderState -> mapper.mokaCommonOrderStateToMokaCommonOrderStateResponseDto(mokaCommonOrderState))
                        .collect(Collectors.toList());

        // http://localhost:8080/moka/v1/common/orderstate

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{commonOrderStateCode}")
    public ResponseEntity deleteMokaCommonOrderState(
            @PathVariable("commonOrderStateCode") String commonOrderStateCode){
        mokaCommonOrderStateService.deleteMokaCommonOrderState(commonOrderStateCode);

// http://localhost:8080/moka/v1/common/orderstate/OSA6

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
