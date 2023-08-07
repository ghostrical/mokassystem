package com.moka.common.service;

import com.moka.common.entity.*;
import com.moka.common.repository.*;
import com.moka.exception.BusinessLogicException;
import com.moka.exception.ExceptionCode;
import com.moka.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MokaCommonRankTypeService {

    private final MokaCommonRankTypeRepository mokaCommonRankTypeRepository;

    private final CustomBeanUtils<MokaCommonRankType> beanUtils;

    public MokaCommonRankTypeService(MokaCommonRankTypeRepository mokaCommonRankTypeRepository,
                                     CustomBeanUtils<MokaCommonRankType> beanUtils) {
        this.mokaCommonRankTypeRepository = mokaCommonRankTypeRepository;
        this.beanUtils = beanUtils;
    }

    public MokaCommonRankType createMokaCommonRankType(MokaCommonRankType mokaCommonRankType){

        return mokaCommonRankTypeRepository.save(mokaCommonRankType);
    }

    public MokaCommonRankType updateMokaCommonRankType(MokaCommonRankType mokaCommonRankType){
        MokaCommonRankType findMokaCommonRankType = findVerifiedMokaCommonRankType(mokaCommonRankType.getCommonRankTypeCode());

        MokaCommonRankType updatingMokaCommonRankType =
                beanUtils.copyNonNullProperties(mokaCommonRankType, findMokaCommonRankType);

        return mokaCommonRankTypeRepository.save(updatingMokaCommonRankType);
    }

    @Transactional(readOnly = true)
    public MokaCommonRankType findMokaCommonRankType(String commonRankTypeCode){
        return findVerifiedMokaCommonRankType(commonRankTypeCode);
    }

    public List<MokaCommonRankType> findMokaCommonRankTypes(){
        return mokaCommonRankTypeRepository.findAll();
    }

    public void deleteMokaCommonRankType(String commonRankTypeCode){
        MokaCommonRankType mokaCommonRankType = findVerifiedMokaCommonRankType(commonRankTypeCode);

        mokaCommonRankTypeRepository.delete(mokaCommonRankType);
    }


    @Transactional(readOnly = true)
    public MokaCommonRankType findVerifiedMokaCommonRankType(String commonRankTypeCode) {
        Optional<MokaCommonRankType> optionalToodoo =
                mokaCommonRankTypeRepository.findById(commonRankTypeCode);
        MokaCommonRankType findToodoo =
                optionalToodoo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findToodoo;
    }


}
