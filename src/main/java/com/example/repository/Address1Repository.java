package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Address1;

// 저장소 생성 : JpaRepository에는 기본적인 crud 구현되어 있음
// JpaRepository<엔티티, 기본키의 데이터 타입>
@Repository
public interface Address1Repository extends JpaRepository<Address1, Long> {

    //SELECT ... WHERE address=?
    List<Address1> findByAddress(String address);

    //SELECT ... WHERE postcode=?
    List<Address1> findByPostcode(String postcode);

    //SELECT ... WHERE address=? AND postcode=?
    List<Address1> findByAddressAndPostcode(String address, String postcode);

    //SELECT ... WHERE member1.id=? ORDER BY no DESC  member1은 객체이기 때문에 _를 이용해서 id값
    List<Address1> findByMember1_idOrderByNoDesc(String id);

    //SELECT count(*) FROM address1 WHERE member1.id=?
    long countByMember1_id(String id);

    //WHERE member1.id=? ORDER BY no DESC + 페이지네이션 기능 포함
    List<Address1> findByMember1_idOrderByNoDesc(String id, Pageable pageable); //domain pageable 
}