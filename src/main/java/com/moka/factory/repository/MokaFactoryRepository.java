package com.moka.factory.repository;

import com.moka.factory.entity.MokaFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MokaFactoryRepository extends JpaRepository<MokaFactory, String> {

    @Query(value = "select max(n.factory_serial_num) as factory_serial_num from moka_factory n", nativeQuery = true)
    String findMaxCode();

}
