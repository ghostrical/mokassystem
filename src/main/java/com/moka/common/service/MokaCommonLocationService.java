package com.moka.common.service;

import com.moka.common.entity.MokaCommonLocation;
import com.moka.common.repository.MokaCommonLocationRepository;
import com.moka.exception.BusinessLogicException;
import com.moka.exception.ExceptionCode;
import com.moka.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MokaCommonLocationService {

    private final MokaCommonLocationRepository mokaCommonLocationRepository;

    private final CustomBeanUtils<MokaCommonLocation> beanUtils;

    public MokaCommonLocationService(MokaCommonLocationRepository mokaCommonLocationRepository,
                                     CustomBeanUtils<MokaCommonLocation> beanUtils) {
        this.mokaCommonLocationRepository = mokaCommonLocationRepository;
        this.beanUtils = beanUtils;
    }

    public MokaCommonLocation createMokaCommonLocation(MokaCommonLocation mokaCommonLocation){

        if(mokaCommonLocationRepository.count() == 0){
            mokaCommonLocation.setCommonLocationCode("L001");

        }
        else {

            String findMaxCode = mokaCommonLocationRepository.findMaxCode();

            System.out.println("findmaxcode.id + "+ findMaxCode);
            System.out.println("findMaxCode.substring(0,1) : "+findMaxCode.substring(0,1));
            String findMaxCodea = findMaxCode.substring(0,1);
            System.out.println("findMaxCode.substring(1,4) : "+findMaxCode.substring(1,4));
            int findMaxCodeb = Integer.parseInt(findMaxCode.substring(1,4)) + 1;
            String findMaxCodec = String.format("%03d",findMaxCodeb);
            System.out.println("findMaxCodec : "+findMaxCodec);

            String findMax = findMaxCodea+findMaxCodec;

            System.out.println("findMax : "+findMax);

            mokaCommonLocation.setCommonLocationCode(findMax);

        }

        return mokaCommonLocationRepository.save(mokaCommonLocation);
    }

    public MokaCommonLocation updateMokaCommonLocation(MokaCommonLocation mokaCommonLocation){
        MokaCommonLocation findMokaCommonLocation = findVerifiedMokaCommonLocation(mokaCommonLocation.getCommonLocationCode());

        MokaCommonLocation updatingMokaCommonLocation =
                beanUtils.copyNonNullProperties(mokaCommonLocation, findMokaCommonLocation);

        return mokaCommonLocationRepository.save(updatingMokaCommonLocation);
    }

    @Transactional(readOnly = true)
    public MokaCommonLocation findMokaCommonLocation(String commonLocationCode){
        return findVerifiedMokaCommonLocation(commonLocationCode);
    }

    public List<MokaCommonLocation> findMokaCommonLocations(){
        return mokaCommonLocationRepository.findAll();
    }

    public void deleteMokaCommonLocation(String commonLocationCode){
        MokaCommonLocation mokaCommonLocation = findVerifiedMokaCommonLocation(commonLocationCode);

        mokaCommonLocationRepository.delete(mokaCommonLocation);
    }


    @Transactional(readOnly = true)
    public MokaCommonLocation findVerifiedMokaCommonLocation(String commonLocationCode) {
        Optional<MokaCommonLocation> optionalToodoo =
                mokaCommonLocationRepository.findById(commonLocationCode);
        MokaCommonLocation findToodoo =
                optionalToodoo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findToodoo;
    }


}
