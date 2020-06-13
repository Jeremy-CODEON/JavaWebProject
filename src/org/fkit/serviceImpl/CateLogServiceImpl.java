package org.fkit.serviceImpl;

import java.util.List;

import org.fkit.domain.CateLog;
import org.fkit.mapper.CateLogMapper;
import org.fkit.service.CateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cateLogService")
public class CateLogServiceImpl implements CateLogService {
	
	@Autowired
	private CateLogMapper cateLogMapper;
	
	@Override
	public int addCateLog(int user_id, String category, String message, String time)
	{
		return cateLogMapper.insertLog(user_id, category, message, time);
	}
	
	@Override
	public List<CateLog> getAllLog()
	{
		return cateLogMapper.getAllLog();
	}
	
	@Override
	public List<CateLog> getByCategory(String category)
	{
		return cateLogMapper.getByCategory(category);
	}

}
