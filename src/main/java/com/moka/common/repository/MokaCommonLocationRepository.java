package com.moka.common.repository;

import com.moka.common.entity.MokaCommonLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// entity별로 있어야 하나.. 테이블들을 하나로 묶은 common을 만들어야 하나.
// 일단 테이블별로 컨트롤러, 서비스, dto, entity, 리포지토리를 만들고 나서 common interface 추상이 가능한지 봐야할 거 같아..

public interface MokaCommonLocationRepository extends JpaRepository<MokaCommonLocation, String>  {

    @Query(value = "select max(n.common_location_code) as common_location_code from moka_common_location n", nativeQuery = true)
    String findMaxCode();
}
