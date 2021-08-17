package com.mayday.news.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

public interface INewsService {
public List<NewsVO> getNewsList(NewsSearchVO searchVO);

public void excelDown(NewsSearchVO searchVO,HttpServletResponse resp) throws Exception;
public void createNews(NewsVO news);
public void removeNews(NewsVO news);
}
