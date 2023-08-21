package com.moka.factory.service;


import com.moka.exception.BusinessLogicException;
import com.moka.exception.ExceptionCode;
import com.moka.factory.dto.MokaFactoryResponseDto;
import com.moka.factory.entity.MokaFactory;
import com.moka.factory.repository.MokaFactoryRepository;
import com.moka.utils.CustomBeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MokaFactoryService {

    private final MokaFactoryRepository mokaFactoryRepository;

    private final CustomBeanUtils<MokaFactory> beanUtils;

    public MokaFactoryService(MokaFactoryRepository mokaFactoryRepository,
                              CustomBeanUtils<MokaFactory> beanUtils) {
        this.mokaFactoryRepository = mokaFactoryRepository;
        this.beanUtils = beanUtils;
    }

    public MokaFactory createFactory(MokaFactory mokaFactory){

        if(mokaFactoryRepository.count() == 0){
            System.out.println("2222");
            mokaFactory.setFactorySerialNum("FAC001");
            System.out.println("3333");

        }
        else {
            System.out.println("000");

            String findMaxCode = mokaFactoryRepository.findMaxCode();

            System.out.println("111");
            System.out.println("findmaxcode.id + "+ findMaxCode);
            System.out.println("findMaxCode.substring(0,3) : "+findMaxCode.substring(0,3));
            String findMaxCodea = findMaxCode.substring(0,3);
            System.out.println("findMaxCode.substring(3,6) : "+findMaxCode.substring(3,6));
            int findMaxCodeb = Integer.parseInt(findMaxCode.substring(3,6)) + 1;
            String findMaxCodec = String.format("%03d",findMaxCodeb);
            System.out.println("findMaxCodec : "+findMaxCodec);

            String findMax = findMaxCodea+findMaxCodec;

            System.out.println("findMax : "+findMax);

            mokaFactory.setFactorySerialNum(findMax);

        }

        return mokaFactoryRepository.save(mokaFactory);
    }

    public MokaFactory updateFactory(MokaFactory mokaFactory){
        MokaFactory findmokaFactory = findVerifiedFactory(mokaFactory.getFactorySerialNum());

        MokaFactory updatingFactory =
                beanUtils.copyNonNullProperties(mokaFactory, findmokaFactory);

        return mokaFactoryRepository.save(updatingFactory);
    }

    @Transactional(readOnly = true)
    public MokaFactory findFactory(String factorySerialNum){
        return findVerifiedFactory(factorySerialNum);
    }

    public List<MokaFactory> findFactories(){
        return mokaFactoryRepository.findAll();
    }

    public void deleteFactory(String factorySerialNum){
        MokaFactory mokaFactory = findVerifiedFactory(factorySerialNum);

        mokaFactoryRepository.delete(mokaFactory);
    }


    @Transactional(readOnly = true)
    public MokaFactory findVerifiedFactory(String factorySerialNum) {
        Optional<MokaFactory> optionalToodoo =
                mokaFactoryRepository.findById(factorySerialNum);
        MokaFactory findToodoo =
                optionalToodoo.orElseThrow(() ->
                        new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findToodoo;
    }


}
