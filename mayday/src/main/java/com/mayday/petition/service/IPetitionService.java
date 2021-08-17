package com.mayday.petition.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.mayday.exception.BizNotEffectedException;
import com.mayday.exception.BizNotFoundException;
import com.mayday.petition.vo.PetitionSearchVO;
import com.mayday.petition.vo.PetitionVO;

public interface IPetitionService {
	public List<PetitionVO> getPetitionList(PetitionSearchVO searchVO);
	public void excelDown(PetitionSearchVO searchVO,HttpServletResponse resp) throws Exception;
	
	public PetitionVO getPetition(int boNo) throws BizNotFoundException;
	
	public void modifyPetition(PetitionVO petition) throws BizNotFoundException, BizNotEffectedException;
	public void removePetition(PetitionVO petition) throws BizNotFoundException, BizNotEffectedException;
	public void registPetition(PetitionVO petition) throws  BizNotEffectedException;
}
