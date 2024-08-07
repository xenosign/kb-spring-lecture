package org.example.dto.member;

import lombok.RequiredArgsConstructor;
import org.example.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
//@RequiredArgsConstructor
public class MemberRepository {
    private final MemberMapper memberMapper;

    @Autowired
    public MemberRepository(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public MemberDto save(final MemberDto memberDto) {
        return null;
    }

    public List<MemberDto> findAll() {
        return memberMapper.findAll();
    }

    public List<MemberDto> findAll2() {
        return memberMapper.findAll2();
    }
}
