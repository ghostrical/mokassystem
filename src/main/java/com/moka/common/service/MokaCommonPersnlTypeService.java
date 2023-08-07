package com.moka.common.service;

import com.moka.common.entity.MokaCommonPersnlType;
import com.moka.common.repository.*;
import com.moka.exception.BusinessLogicException;
import com.moka.exception.ExceptionCode;
import com.moka.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MokaCommonPersnlTypeService {

    private final MokaCommonPersnlTypeRepository mokaCommonPersnlTypeRepository;

    private final CustomBeanUtils<MokaCommonPersnlType> beanUtils;

    public MokaCommonPersnlTypeService(MokaCommonPersnlTypeRepository mokaCommonPersnlTypeRepository,
                                       CustomBeanUtils<MokaCommonPersnlType> beanUtils) {
        this.mokaCommonPersnlTypeRepository = mokaCommonPersnlTypeRepository;
        this.beanUtils = beanUtils;
    }

    public MokaCommonPersnlType createMokaCommonPersnlType(MokaCommonPersnlType mokaCommonPersnlType){

        return mokaCommonPersnlTypeRepository.save(mokaCommonPersnlType);
    }

    public MokaCommonPersnlType updateMokaCommonPersnlType(MokaCommonPersnlType mokaCommonPersnlType){
        MokaCommonPersnlType findMokaCommonPersnlType = findVerifiedMokaCommonPersnlType(mokaCommonPersnlType.getCommonPersnlTypeCode());

        MokaCommonPersnlType updatingMokaCommonPersnlType =
                beanUtils.copyNonNullProperties(mokaCommonPersnlType, findMokaCommonPersnlType);

        return mokaCommonPersnlTypeRepository.save(updatingMokaCommonPersnlType);
    }

    @Transactional(readOnly = true)
    public MokaCommonPersnlType findMokaCommonPersnlType(String commonPersnlTypeCode){
        return findVerifiedMokaCommonPersnlType(commonPersnlTypeCode);
    }

    public List<MokaCommonPersnlType> findMokaCommonPersnlTypes(){
        return mokaCommonPersnlTypeRepository.findAll();
    }

    public void deleteMokaCommonPersnlType(String commonPersnlTypeCode){
        MokaCommonPersnlType mokaCommonPersnlType = findVerifiedMokaCommonPersnlType(commonPersnlTypeCode);

        mokaCommonPersnlTypeRepository.delete(mokaCommonPersnlType);
    }


    @Transactional(readOnly = true)
    public MokaCommonPersnlType findVerifiedMokaCommonPersnlType(String commonPersnlTypeCode) {
        Optional<MokaCommonPersnlType> optionalToodoo =
                mokaCommonPersnlTypeRepository.findById(commonPersnlTypeCode);
        MokaCommonPersnlType findToodoo =
                optionalToodoo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findToodoo;
    }


}
