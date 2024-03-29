package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "MEMBER1") // 생성하고자 하는 테이블 또는 생성되어 있는 테이블 매칭
public class Member1 {

    @Id // import javax.persistence.Id; : 기본키
    @Column(name = "ID", length = 50) 
    private String id; // @Column을 생략하면 변수명이 컬럼명이 됨

    private String pw;

    private String name;

    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp //추가시에만 날짜 정보 저장
    private Date regdate;

    
    @ToString.Exclude
    @OneToOne(mappedBy = "member1" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MemberInfo1 memberInfo1;


    // 양방향이라서 어렵다나 .. .?
    //EAGER -> member1 조회시 address1을 조인하여 보여줌(화면창을 띄울때 데이터를 다가져와서 띄움 상대적으로 느림)
    //LAZY -> member1 조회시 address1을 조인하지 않고 address1을 필요할때 조인함(빠르게 화면창 띄우고 데이터 가져옴)
    //cascade => member1의 회원을 지우면 자동으로 address1의 관련 주소도 삭제함(외래키 신경 x , 외래키걸려있는 거 까지 다 삭제)
    // @ToString.Exclude //stackoverflow
    // @OneToMany(mappedBy = "member1" , fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) //변수가 들어와야함
    // @OrderBy(value = "no desc")
    // List<Address1> list = new  ArrayList<>();
    
}