package com.example.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Board;

public interface BoardRepository extends JpaRepository<Board, BigDecimal> { //jparepository<엔티티명, 기본키의 데이터타입>

    //SELECT * FROM BOARD ORDER BY NO DESC;
    List<Board> findAllByOrderByNoDesc();

    //제목찾기
    //select * from board where title like '%' || ? || '%' order by no desc
    List<Board> findByTitleIgnoreCaseContainingOrderByNoDesc(String title);
    //내용찾기
    List<Board> findByContentIgnoreCaseContainingOrderByNoDesc(String content);
    //작성자찾기
    List<Board> findByWriterIgnoreCaseContainingOrderByNoDesc(String writer);


    //페이지 네이션
     //제목찾기
    //select * from board where title like '%' || ? || '%' order by no desc
    List<Board> findByTitleIgnoreCaseContainingOrderByNoDesc(String title, Pageable pageable);
    //내용찾기
    List<Board> findByContentIgnoreCaseContainingOrderByNoDesc(String content, Pageable pageable);
    //작성자찾기
    List<Board> findByWriterIgnoreCaseContainingOrderByNoDesc(String writer, Pageable pageable);
    
    long countByTitleIgnoreCaseContainingOrderByNoDesc(String title);
    long countByContentIgnoreCaseContainingOrderByNoDesc(String content);
    long countByWriterIgnoreCaseContainingOrderByNoDesc(String writer);
}
