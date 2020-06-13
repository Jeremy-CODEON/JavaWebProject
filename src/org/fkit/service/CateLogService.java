package org.fkit.service;

import java.util.List;

import org.fkit.domain.CateLog;

public interface CateLogService {
	
	int addCateLog(int user_id, String category, String message, String time);
	
	List<CateLog> getAllLog();
	
	List<CateLog> getByCategory(String category);

}
