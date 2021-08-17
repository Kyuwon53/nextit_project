package com.mayday.common.util;

public class MayDayFileUtils {
	public static String fancySize(long size) {
		if(size < 1024) {
			return size +"B";
		}
		if(size >= 1024 && size <(1024*1024)) {
			return size/1024 + "KB";
		}
		if(size <= 1024*1024 && size < 1024*1024*1024) {
			return size/1024/1024 + "MB";
		}
		return "파일 크기 오류";
	}
}
