package com.mayday.news.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface INewsDao {
public int getNewsCount(NewsSearchVO searchVO);
public List<NewsVO> getNewsList(NewsSearchVO searchVO);

public int insertNews(NewsVO news);
public int deleteNews(NewsVO news);
}
