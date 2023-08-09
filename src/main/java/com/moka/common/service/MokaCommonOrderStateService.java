package com.moka.common.service;

import com.moka.common.entity.MokaCommonOrderState;
import com.moka.common.repository.MokaCommonOrderStateRepository;
import com.moka.exception.BusinessLogicException;
import com.moka.exception.ExceptionCode;
import com.moka.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MokaCommonOrderStateService {

    private final MokaCommonOrderStateRepository mokaCommonOrderStateRepository;

    private final CustomBeanUtils<MokaCommonOrderState> beanUtils;

    public MokaCommonOrderStateService(MokaCommonOrderStateRepository mokaCommonOrderStateRepository,
                                       CustomBeanUtils<MokaCommonOrderState> beanUtils) {
        this.mokaCommonOrderStateRepository = mokaCommonOrderStateRepository;
        this.beanUtils = beanUtils;
    }

    public MokaCommonOrderState createMokaCommonOrderState(MokaCommonOrderState mokaCommonOrderState){

        return mokaCommonOrderStateRepository.save(mokaCommonOrderState);
    }

    public MokaCommonOrderState updateMokaCommonOrderState(MokaCommonOrderState mokaCommonOrderState){
        MokaCommonOrderState findMokaCommonOrderState = findVerifiedMokaCommonOrderState(mokaCommonOrderState.getCommonOrderStateCode());

        MokaCommonOrderState updatingMokaCommonOrderState =
                beanUtils.copyNonNullProperties(mokaCommonOrderState, findMokaCommonOrderState);

        return mokaCommonOrderStateRepository.save(updatingMokaCommonOrderState);
    }

    @Transactional(readOnly = true)
    public MokaCommonOrderState findMokaCommonOrderState(String commonOrderStateCode){
        return findVerifiedMokaCommonOrderState(commonOrderStateCode);
    }

    public List<MokaCommonOrderState> findMokaCommonOrderStates(){
        return mokaCommonOrderStateRepository.findAll();
    }

    public void deleteMokaCommonOrderState(String commonOrderStateCode){
        MokaCommonOrderState mokaCommonOrderState = findVerifiedMokaCommonOrderState(commonOrderStateCode);

        mokaCommonOrderStateRepository.delete(mokaCommonOrderState);
    }


    @Transactional(readOnly = true)
    public MokaCommonOrderState findVerifiedMokaCommonOrderState(String commonOrderStateCode) {
        Optional<MokaCommonOrderState> optionalToodoo =
                mokaCommonOrderStateRepository.findById(commonOrderStateCode);
        MokaCommonOrderState findToodoo =
                optionalToodoo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findToodoo;
    }


}
