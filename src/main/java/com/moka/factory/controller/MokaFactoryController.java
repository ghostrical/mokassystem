package com.moka.factory.controller;


import com.moka.factory.dto.MokaFactoryPatchDto;
import com.moka.factory.dto.MokaFactoryPostDto;
import com.moka.factory.dto.MokaFactoryResponseDto;
import com.moka.factory.entity.MokaFactory;
import com.moka.factory.mapper.MokaFactoryMapper;
import com.moka.factory.service.MokaFactoryService;
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
@RequestMapping("/v1/mokafactory")
@Validated
public class MokaFactoryController {

    private final static String MOKA_FACTORY_DEFAULT_URL = "/v1/mokafactory";

    private final MokaFactoryService mokaFactoryService;

    private final MokaFactoryMapper mapper;

    public MokaFactoryController(MokaFactoryService mokaFactoryService, MokaFactoryMapper mapper) {
        this.mokaFactoryService = mokaFactoryService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postFactory(@Valid @RequestBody MokaFactoryPostDto requestBody){
        MokaFactory mokaFactory = mapper.mokaFactoryPostDtoToMokaFactory(requestBody);

        MokaFactory createdMokaFactory =
                mokaFactoryService.createFactory(mokaFactory);

        URI location = UriCreator.createUri(MOKA_FACTORY_DEFAULT_URL, createdMokaFactory.getFactorySerialNum());

        //http://localhost:8080/moka/v1/mokafactory
//        {
//            "factoryName":"fanme2",
//                "factoryAddr":"성구",
//                "factoryLocationCode":"L001",
//                "factoryPhone":"010-9999-7777"
//        }

        return new ResponseEntity<>(
                mapper.mokaFactoryToMokaFactoryResponseDto (createdMokaFactory)
                , HttpStatus.CREATED);

    }

    @PatchMapping("/{factorySerialNum}")
    public ResponseEntity patchFactory(
            @PathVariable("factorySerialNum") String factorySerialNum,
            @Valid @RequestBody MokaFactoryPatchDto requestBody ){

        requestBody.setFactorySerialNum (factorySerialNum);

        MokaFactory mokaFactory =
                mokaFactoryService.updateFactory(mapper.mokaFactoryPatchDtoToMokaFactory(requestBody));

        // http://localhost:8080/moka/v1/mokafactory/FAC001
//        {
//            "factorySerialNum":"FAC001",
//                "factoryName":"fa22nme2",
//                "factoryAddr":"성22구",
//                "factoryLocationCode":"L003",
//                "factoryPhone":"010-2222-2222"
//        }

        return new ResponseEntity<>(
                mapper.mokaFactoryToMokaFactoryResponseDto (mokaFactory)
                , HttpStatus.OK);
    }

    @GetMapping("/{factorySerialNum}")
    public ResponseEntity getFactory(
            @PathVariable("factorySerialNum") String factorySerialNum ){
        MokaFactory mokaFactory =
                mokaFactoryService.findFactory(factorySerialNum);

        // http://localhost:8080/moka/v1/mokafactory/FAC002


        return new ResponseEntity<>(
                mapper.mokaFactoryToMokaFactoryResponseDto (mokaFactory)
                , HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getFactories(){

        List<MokaFactoryResponseDto> response = mapper.mokaFactoriesToMokaFactoryResponseDtos(
                mokaFactoryService.findFactories()
        );

        // http://localhost:8080/moka/v1/mokafactory

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{factorySerialNum}")
    public ResponseEntity deleteFactory(
            @PathVariable("factorySerialNum") String factorySerialNum){
        mokaFactoryService.deleteFactory(factorySerialNum);

// http://localhost:8080/moka/v1/mokafactory/FAC002

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
