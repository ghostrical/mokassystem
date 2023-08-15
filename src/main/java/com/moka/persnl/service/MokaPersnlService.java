package com.moka.persnl.service;

import com.moka.auth.utils.CustomAuthorityUtils;
import com.moka.common.entity.MokaCommonRankType;
import com.moka.common.repository.MokaCommonRankTypeRepository;
import com.moka.exception.BusinessLogicException;
import com.moka.exception.ExceptionCode;
import com.moka.persnl.entity.MokaPersnl;
import com.moka.persnl.repository.MokaPersnlRepository;
import com.moka.utils.CustomBeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class MokaPersnlService {

    private final MokaPersnlRepository mokaPersnlRepository;

    private final CustomBeanUtils<MokaPersnl> beanUtils;

    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    public MokaPersnlService(MokaPersnlRepository mokaPersnlRepository,
                                     CustomBeanUtils<MokaPersnl> beanUtils,
                             PasswordEncoder passwordEncoder,
                             CustomAuthorityUtils authorityUtils) {
        this.mokaPersnlRepository = mokaPersnlRepository;
        this.beanUtils = beanUtils;
        this.passwordEncoder = passwordEncoder;
        this.authorityUtils = authorityUtils;
    }

    public MokaPersnl createPersnl(MokaPersnl mokaPersnl){

        String persnlTypeCode = mokaPersnl.getMokaCommonPersnlType().getCommonPersnlTypeCode().substring(2,3);
        System.out.println("persnlTypeCode : "+persnlTypeCode);

        if(mokaPersnlRepository.persnlTypeCodeCount(persnlTypeCode) == 0){
            String insertNum = persnlTypeCode+"001";
            System.out.println("insertNum : "+insertNum);
            mokaPersnl.setPersnlSerialNum(insertNum);
        }
        else {
            System.out.println("000");

            String findMaxCode = mokaPersnlRepository.persnlTypeCodeMax(persnlTypeCode);

            System.out.println("111");
            System.out.println("findmaxcode.id + "+ findMaxCode);
            System.out.println("findMaxCode.substring(0,1) : "+findMaxCode.substring(0,1));
            String findMaxCodea = findMaxCode.substring(0,1);
            System.out.println("findMaxCode.substring(1,4) : "+findMaxCode.substring(1,4));
            int findMaxCodeb = Integer.parseInt(findMaxCode.substring(1,4)) + 1;
            String findMaxCodec = String.format("%03d",findMaxCodeb);
            System.out.println("findMaxCodec : "+findMaxCodec);

            String findMax = findMaxCodea+findMaxCodec;

            System.out.println("findMax : "+findMax);

            mokaPersnl.setPersnlSerialNum(findMax);

        }

        //////

        System.out.println("persnl_serial_num : "+mokaPersnl.getPersnlSerialNum());
        System.out.println("persnl_id : "+mokaPersnl.getPersnlId());
        System.out.println("persnl_pw : "+mokaPersnl.getPersnlPw());
        System.out.println("persnl_resident_num : "+mokaPersnl.getPersnlResidentNum());
        System.out.println("persnl_phone : "+mokaPersnl.getPersnlPhone());
        System.out.println("persnl_name : "+mokaPersnl.getPersnlName());
        System.out.println("persnl_email : "+mokaPersnl.getPersnlEmail());
        System.out.println("persnl_addr : "+mokaPersnl.getPersnlAddr());
        System.out.println("persnl_rank_type_code : "+mokaPersnl.getMokaCommonRankType());
        System.out.println("persnl_rank_type_code : "+mokaPersnl.getMokaCommonRankType().getCommonRankTypeCode());
        System.out.println("persnl_type_coide : "+mokaPersnl.getMokaCommonPersnlType());
        System.out.println("persnl_type_code : "+mokaPersnl.getMokaCommonPersnlType().getCommonPersnlTypeCode());
        System.out.println("create_persnl : "+mokaPersnl.getCreatePersnl());
        System.out.println("create_dttm : "+mokaPersnl.getCreateDttm());
        System.out.println("create_ip : "+mokaPersnl.getCreateIp());
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        /////

        // PW암호화

        String encryptedPassword = passwordEncoder.encode(mokaPersnl.getPersnlPw());
        mokaPersnl.setPersnlPw(encryptedPassword);

        List<String> roles = authorityUtils.createRoles(mokaPersnl.getMokaCommonPersnlType().getCommonPersnlTypeCode());
        mokaPersnl.setRoles(roles);

     //   MokaPersnl savedMokaPersnl = mokaPersnlRepository.save(mokaPersnl);

        ////

        return mokaPersnlRepository.save(mokaPersnl);
        // 수동 insert 테스트
//        mokaPersnlRepository.insertmanual(mokaPersnl.getPersnlSerialNum(), mokaPersnl.getPersnlId(), mokaPersnl.getPersnlPw(),mokaPersnl.getPersnlResidentNum(),
//                mokaPersnl.getPersnlPhone(),mokaPersnl.getPersnlName(), mokaPersnl.getPersnlEmail(),mokaPersnl.getPersnlAddr(),
//                mokaPersnl.getMokaCommonRankType().getCommonRankTypeCode(), mokaPersnl.getMokaCommonPersnlType().getCommonPersnlTypeCode(), mokaPersnl.getCreatePersnl(),
//                mokaPersnl.getCreateDttm(),mokaPersnl.getCreateIp() );
//
//        return mokaPersnl;
// 수동커리는 성공. 바로 insert 된다.

    }

    public MokaPersnl updateMokaPersnl(MokaPersnl mokaPersnl){
        MokaPersnl findMokaPersnl = findVerifiedMokaPersnl(mokaPersnl.getPersnlSerialNum());

        System.out.println("update date : "+findMokaPersnl.getUpdateDttm());
        System.out.println("create date : "+findMokaPersnl.getCreateDttm());

        MokaPersnl updatingMokaPersnl =
                beanUtils.copyNonNullProperties(mokaPersnl, findMokaPersnl);


        updatingMokaPersnl.setUpdateDttm(LocalDateTime.now());

        return mokaPersnlRepository.save(updatingMokaPersnl);
    }

    @Transactional(readOnly = true)
    public MokaPersnl findMokaPersnl(String persnlSerialNum){
        return findVerifiedMokaPersnl(persnlSerialNum);
    }

    public List<MokaPersnl> findMokaPersnls(){
        return mokaPersnlRepository.findAll();
    }

    public void deleteMokaPersnl(String persnlSerialNum){
        MokaPersnl mokaPersnl = findVerifiedMokaPersnl(persnlSerialNum);

        mokaPersnlRepository.delete(mokaPersnl);
    }


    @Transactional(readOnly = true)
    public MokaPersnl findVerifiedMokaPersnl(String persnlSerialNum) {
        Optional<MokaPersnl> optionalToodoo =
                mokaPersnlRepository.findById(persnlSerialNum);
        MokaPersnl findToodoo =
                optionalToodoo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findToodoo;
    }


}