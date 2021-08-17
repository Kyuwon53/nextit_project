package com.mayday.common.service;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.common.vo.MemberProfileVO;
@Mapper
public interface IMemberProfileDao {
	public MemberProfileVO getMemProfile(String memId);
	public int insertMemProfile(MemberProfileVO memberProfileVO);
	public int deleteMemProfile(String memId);
	public int updateMemProfile(MemberProfileVO memberProfileVO);
}
