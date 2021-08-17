package com.mayday.common.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.mayday.common.vo.MemberProfileVO;

@Component
public class MemProfileUtils {
	@Value("#{proj['file.upload.path']}")
	private String uploadPath;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public MemberProfileVO getMemProfileByMultipart(MultipartFile multipart, String memId, String path ) throws IOException{
		if(!multipart.isEmpty()) {
			String fileName = memId+"_profile";
			
			MemberProfileVO vo = new MemberProfileVO();
			vo.setProfileOriginalName(multipart.getOriginalFilename());
			vo.setProfileSize(multipart.getSize());
			vo.setProfileContentType(multipart.getContentType());
			vo.setProfileName(fileName);
			vo.setMemId(memId);
			vo.setProfilePath(path);
			vo.setProfileFancySize(MayDayFileUtils.fancySize(multipart.getSize()));
			String filePath = uploadPath + File.separatorChar + vo.getProfilePath();
			
			logger.debug("filePath={}, fileName={}", filePath, fileName);
			
			FileUtils.copyInputStreamToFile(multipart.getInputStream(), new File(filePath, fileName));
			return vo;
		}else {
			return null;
		}
	}
}
