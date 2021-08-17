package com.mayday.petition.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mayday.petition.vo.PetitionSearchVO;
import com.mayday.petition.vo.PetitionVO;
@Mapper
public interface IPetitionDao {
	public List<PetitionVO> getPetitionList(PetitionSearchVO searchVO);
	
	public int getCountPetition(PetitionSearchVO searchVO);
	public PetitionVO getPetition(int boNo);
	
	public int updatePetition(PetitionVO petition);
	public int deletePetition(PetitionVO petition);
	public int insertPetition(PetitionVO petition);
}
