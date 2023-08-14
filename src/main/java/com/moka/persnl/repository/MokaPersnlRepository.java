package com.moka.persnl.repository;

import com.moka.persnl.entity.MokaPersnl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface MokaPersnlRepository extends JpaRepository<MokaPersnl, String> {

    @Query(value = "select count(*) from moka_persnl where persnl_serial_num like :persnlTypeCode%", nativeQuery = true)
    Integer persnlTypeCodeCount(@Param("persnlTypeCode") String persnlTypeCode);

    @Query(value = "select max(persnl_serial_num) from moka_persnl where persnl_serial_num like :persnlTypeCode%", nativeQuery = true)
    String persnlTypeCodeMax(@Param("persnlTypeCode") String persnlTypeCode);

//    @Modifying
//    @Query(value = "insert into moka_persnl(persnl_serial_num,\n" +
//            "persnl_id,\n" +
//            "persnl_pw,\n" +
//            "persnl_resident_num,\n" +
//            "persnl_phone,\n" +
//            "persnl_name,\n" +
//            "persnl_email,\n" +
//            "persnl_addr,\n" +
//            "persnl_rank_type_code,\n" +
//            "persnl_type_code,\n" +
//            "create_persnl,\n" +
//            "create_dttm,\n" +
//            "create_ip )\n" +
//            "values ( :persnlSerialNum , :persnlId , :persnlPw , :persnlResidentNum , \n" +
//            ":persnlPhone , :persnlName , :persnlEmail , :persnlAddr , \n" +
//            ":persnlRankTypeCode , :persnlTypeCode , :createPersnl , :createDttm , :createIp ) ", nativeQuery = true)
//    void insertmanual(@Param("persnlSerialNum") String persnlSerialNum, @Param("persnlId") String persnlId, @Param("persnlPw") String persnlPw,
//                      @Param("persnlResidentNum") String persnlResidentNum, @Param("persnlPhone") String persnlPhone, @Param("persnlName") String persnlName,
//                      @Param("persnlEmail") String persnlEmail, @Param("persnlAddr") String persnlAddr, @Param("persnlRankTypeCode") String persnlRankTypeCode,
//                      @Param("persnlTypeCode") String persnlTypeCode, @Param("createPersnl") String createPersnl, @Param("createDttm") LocalDateTime createDttm,
//                      @Param("createIp") String createIp);
// 수동쿼리는 성공.

}
