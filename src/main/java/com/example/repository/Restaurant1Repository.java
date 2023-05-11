package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Restaurant1;

@Repository
public interface Restaurant1Repository extends JpaRepository<Restaurant1, String> {
    
    //페이지네이션
    //연락처 검색
    //SELECT * FROM RESTAURANT1 WHERE PHONE LIKE '%' || ? '%' ORDER BY NO DESC;
    List<Restaurant1> findByPhoneContainingOrderByNoDesc(String phone, Pageable pageable);
    //SELECT count(*) FROM RESTAURANT1 WHERE PHONE LIKE '%' || ? '%';
    long countByPhoneContaining(String phone);

    //이름 검색
    //SELECT * FROM RESTAURANT1 WHERE NAME LIKE '%' || ? '%' ORDER BY NO DESC;
    List<Restaurant1> findByNameContainingOrderByNoDesc(String name, Pageable pageable);
    //SELECT count(*) FROM RESTAURANT1 WHERE NAME LIKE '%' || ? '%';
    long countByNameContaining(String name);

    //종류 검색
    //SELECT * FROM RESTAURANT1 WHERE TYPE LIKE '%' || ? '%' ORDER BY NO DESC;
    List<Restaurant1> findByTypeContainingOrderByNoDesc(String type, Pageable pageable);
    //SELECT count(*) FROM RESTAURANT1 WHERE TYPE LIKE '%' || ? '%';
    long countByTypeContaining(String type);

    //주소 검색
     //SELECT * FROM RESTAURANT1 WHERE ADDRESS LIKE '%' || ? '%' ORDER BY NO DESC;
    List<Restaurant1> findByAddressContainingOrderByNoDesc(String address, Pageable pageable);
    //SELECT count(*) FROM RESTAURANT1 WHERE ADDRESS LIKE '%' || ? '%';
    long countByAddressContaining(String address);

}
