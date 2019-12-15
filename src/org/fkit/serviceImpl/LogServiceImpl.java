package org.fkit.serviceImpl;

import java.util.List;

import org.fkit.domain.Log;
import org.fkit.mapper.LogMapper;
import org.fkit.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("logService")
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogMapper logMapper;
	
	@Override
	public int addLog(int user_id, String message, String time)
	{
		return logMapper.insertLog(user_id, message, time);
	}
	
	@Override
	public List<Log> getByUserId(int user_id)
	{
		return logMapper.getByUserId(user_id);
	}
	
	@Override
	public List<Log> getAllLog()
	{
		return logMapper.getAllLog();
	}

}
