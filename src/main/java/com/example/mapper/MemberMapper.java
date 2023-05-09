package com.example.mapper;

import com.example.dto.Member;

@Mapper
public interface MemberMapper {
    
    public int insertMemberOne(Member member);

    public Member selectMemberOne(Member member);

    //아이디만 전달
    public Member selectMemberOne1(String userid);

    //회원정보 수정
    public int updateMemberOne(Member member);

    //비번변경
    public int updateMemberPassword(Member member);

    //회원탈퇴
    public int deleteMemberOne(Member member);

}
