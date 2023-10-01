package com.moka.persnl.service;

import com.moka.common.entity.MokaCommonRankType;
import com.moka.common.repository.MokaCommonRankTypeRepository;
import com.moka.exception.BusinessLogicException;
import com.moka.exception.ExceptionCode;
import com.moka.persnl.entity.MokaPersnl;
import com.moka.persnl.repository.MokaPersnlRepository;
import com.moka.utils.CustomBeanUtils;
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

    public MokaPersnlService(MokaPersnlRepository mokaPersnlRepository,
                                     CustomBeanUtils<MokaPersnl> beanUtils) {
        this.mokaPersnlRepository = mokaPersnlRepository;
        this.beanUtils = beanUtils;
    }

    public MokaPersnl createPersnl(MokaPersnl mokaPersnl){

        String persnlTypeCode = mokaPersnl.getMokaCommonPersnlType().getCommonPersnlTypeCode().substring(2,3);

        if(mokaPersnlRepository.persnlTypeCodeCount(persnlTypeCode) == 0){
            String insertNum = persnlTypeCode+"001";
            mokaPersnl.setPersnlSerialNum(insertNum);
        }
        else {

            String findMaxCode = mokaPersnlRepository.persnlTypeCodeMax(persnlTypeCode);

            String findMaxCodea = findMaxCode.substring(0,1);
            int findMaxCodeb = Integer.parseInt(findMaxCode.substring(1,4)) + 1;
            String findMaxCodec = String.format("%03d",findMaxCodeb);

            String findMax = findMaxCodea+findMaxCodec;

            mokaPersnl.setPersnlSerialNum(findMax);

        }

        return mokaPersnlRepository.save(mokaPersnl);

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