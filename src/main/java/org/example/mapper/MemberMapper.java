package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dto.member.MemberDto;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    void save(MemberDto newMember);

    List<MemberDto> findAll();

    @Select("SELECT * FROM members")
    List<MemberDto> findAll2();
}
