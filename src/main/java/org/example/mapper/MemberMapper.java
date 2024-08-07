package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dto.member.MemberDto;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    List<MemberDto> findAll();
}
