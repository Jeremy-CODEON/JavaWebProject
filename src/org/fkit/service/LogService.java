package org.fkit.service;

import java.util.List;

import org.fkit.domain.Log;

public interface LogService {
	
	int addLog(int user_id,String message,String time);
	
	List<Log> getByUserId(int user_id);
	
	List<Log> getAllLog();

}
