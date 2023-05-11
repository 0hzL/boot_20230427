package com.example.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Board1View;

public interface Board1ViewRepository extends JpaRepository<Board1View, Long> { //jparepository<엔티티명, 기본키의 데이터타입>
    
    //전체 num = 0 
    List<Board1View> findAllByOrderByNoDesc();
    
    //글번호와 제목이 정확하게 일치할 경우 1
    List<Board1View> findByNoAndTitleOrderByNoDesc(Long no, String title);
 
    //글번호나 제목 둘 중 하나 이상 일치 2
    List<Board1View> findByNoOrTitleOrderByNoDesc(Long no, String title);

    //글번호를 , 로 구분해서 다 조회 3
    List<Board1View> findByNoInOrderByNoDesc(List<Long> no);

     //제목을 , 로 구분해서 다 조회 4
    List<Board1View> findByTitleInOrderByNoDesc(List<String> title);
}
