package com.moka.common.service;

import com.moka.common.entity.MokaCommonOrderHistory;
import com.moka.common.repository.MokaCommonOrderHistoryRepository;
import com.moka.exception.BusinessLogicException;
import com.moka.exception.ExceptionCode;
import com.moka.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MokaCommonOrderHistoryService {

    private final MokaCommonOrderHistoryRepository mokaCommonOrderHistoryRepository;

    private final CustomBeanUtils<MokaCommonOrderHistory> beanUtils;

    public MokaCommonOrderHistoryService(MokaCommonOrderHistoryRepository mokaCommonOrderHistoryRepository,
                                         CustomBeanUtils<MokaCommonOrderHistory> beanUtils) {
        this.mokaCommonOrderHistoryRepository = mokaCommonOrderHistoryRepository;
        this.beanUtils = beanUtils;
    }

    public MokaCommonOrderHistory createMokaCommonOrderHistory(MokaCommonOrderHistory mokaCommonOrderHistory){

        return mokaCommonOrderHistoryRepository.save(mokaCommonOrderHistory);
    }

    public MokaCommonOrderHistory updateMokaCommonOrderHistory(MokaCommonOrderHistory mokaCommonOrderHistory){
        MokaCommonOrderHistory findMokaCommonOrderHistory = findVerifiedMokaCommonOrderHistory(mokaCommonOrderHistory.getCommonOrderHistoryCode());

        MokaCommonOrderHistory updatingMokaCommonOrderHistory =
                beanUtils.copyNonNullProperties(mokaCommonOrderHistory, findMokaCommonOrderHistory);

        return mokaCommonOrderHistoryRepository.save(updatingMokaCommonOrderHistory);
    }

    @Transactional(readOnly = true)
    public MokaCommonOrderHistory findMokaCommonOrderHistory(long commonOrderHistoryCode){
        return findVerifiedMokaCommonOrderHistory(commonOrderHistoryCode);
    }

    public List<MokaCommonOrderHistory> findMokaCommonOrderHistorys(){
        return mokaCommonOrderHistoryRepository.findAll();
    }

    public void deleteMokaCommonOrderHistory(long commonOrderHistoryCode){
        MokaCommonOrderHistory mokaCommonOrderHistory = findVerifiedMokaCommonOrderHistory(commonOrderHistoryCode);

        mokaCommonOrderHistoryRepository.delete(mokaCommonOrderHistory);
    }


    @Transactional(readOnly = true)
    public MokaCommonOrderHistory findVerifiedMokaCommonOrderHistory(long commonOrderHistoryCode) {
        Optional<MokaCommonOrderHistory> optionalToodoo =
                mokaCommonOrderHistoryRepository.findById(commonOrderHistoryCode);
        MokaCommonOrderHistory findToodoo =
                optionalToodoo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findToodoo;
    }


}
