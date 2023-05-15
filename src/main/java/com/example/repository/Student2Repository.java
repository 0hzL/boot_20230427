package com.example.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student2Projection;
import com.example.entity.library.Student2;

@Repository
public interface Student2Repository extends JpaRepository<Student2, String>{
    //select * from 테이블명 where 컬럼=?
    //findBy + 컬럼

    //select count(*) from student2 where email=?
    long countByEmail(String email);

    //select name, phone from student2 where email=?
    Student2Projection findByEmail(String email);

}   
