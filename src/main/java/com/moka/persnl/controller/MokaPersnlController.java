package com.moka.persnl.controller;


import com.moka.utils.HttpServletRequestFactory;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/v1/persnl")
@Validated
public class MokaPersnlController {

    // 로그인 : http://localhost:8080/moka/v1/auth/login

    private final static String MOKA_PERSNL_DEFAULT_URL = "/v1/persnl";

    private final MokaPersnlService mokaPersnlService;

    private final PersnlMapper mapper;

    public MokaPersnlController(MokaPersnlService mokaPersnlService, PersnlMapper mapper) {
        this.mokaPersnlService = mokaPersnlService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postPersnl(@Valid @RequestBody MokaPersnlPostDto requestBody, HttpServletRequest request){

        MokaPersnl mokaPersnl = mapper.mokaPersnlPostDtoToMokaPersnl(requestBody);

        /// IP

        String clientIp = new HttpServletRequestFactory().getClientIp(request);
        System.out.println("clint IP : "+clientIp);
        mokaPersnl.setCreateIp(clientIp);

        ///

        MokaPersnl createdMokaPersnl =
                mokaPersnlService.createPersnl(mokaPersnl);

        URI location = UriCreator.createUri(MOKA_PERSNL_DEFAULT_URL, createdMokaPersnl.getPersnlSerialNum());

        //http://localhost:8080/moka/v1/persnl
//        {
//            "persnlId":"ID3",
//                "persnlPw":"id1pw1",
//                "persnlResidentNum":"0000003111111",
//                "persnlPhone":"010-1111-2222",
//                "persnlName":"김김김",
//                "persnlEmail":"aaa@aaa.com",
//                "persnlAddr":"주소주소1",
//                "persnlRankTypeCode":"RTA1",
//                "persnlTypeCode":"PTA007"
//        }

        return new ResponseEntity<>(
                mapper.mokaPersnlToMokaPersnlResponseDto (createdMokaPersnl)
                , HttpStatus.CREATED);

    }

    @PatchMapping("/{persnlSerialNum}")
    public ResponseEntity patchPersnl(
            @PathVariable("persnlSerialNum") String persnlSerialNum,
            @Valid @RequestBody MokaPersnlPatchDto requestBody, HttpServletRequest request ){

        requestBody.setPersnlSerialNum(persnlSerialNum);

        MokaPersnl mappedPersnl = mapper.mokaPersnlPatchDtoToMokaPersnl(requestBody);

        /// IP

        String clientIp = new HttpServletRequestFactory().getClientIp(request);
        System.out.println("clint IP : "+clientIp);
        mappedPersnl.setUpdateIp(clientIp);

        ///

        MokaPersnl mokaPersnl =
                mokaPersnlService.updateMokaPersnl(mappedPersnl);



        // http://localhost:8080/moka/v1/persnl/A001
//        {
//            "persnlPw":"id3pw3",
//                "persnlResidentNum":"2222223111111",
//                "persnlPhone":"010-5555-6666",
//                "persnlName":"박박박",
//                "persnlEmail":"ccc@ccc.com",
//                "persnlAddr":"주소주소3",
//                "persnlRankTypeCode":"RTA3",
//                "persnlTypeCode":"PTC001"
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

        // http://localhost:8080/moka/v1/persnl/A002


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

        // http://localhost:8080/moka/v1/persnl

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{persnlSerialNum}")
    public ResponseEntity deleteMokaPersnl(
            @PathVariable("persnlSerialNum") String persnlSerialNum){
        mokaPersnlService.deleteMokaPersnl(persnlSerialNum);

// http://localhost:8080/moka/v1/persnl/A002

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
