package com.example.entity;

//원본 entity는 있고 거기서 필요한 것만 받아옴
//일부컬럼만 받아옴
//롬복 못씀
//출력 원하면 반복문 볼려야함 go(member1controller)
public interface Member1Projection {
    //get + 변수()
    String getId();

    String getName();

    int getAge();
}
