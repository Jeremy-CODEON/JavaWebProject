package org.fkit.serviceImpl;

import java.util.List;

import org.fkit.domain.Saler;
import org.fkit.mapper.SalerMapper;
import org.fkit.mapper.UserMapper;
import org.fkit.service.SalerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("salerService")
public class SalerServiceImpl implements SalerService {
	
	@Autowired
	private SalerMapper salerMapper;
	
	@Override
	public Saler login(Integer id,String password)
	{
		return salerMapper.findWithIdAndPassword(id, password);
	}

	@Override
	public int addSaler(String password, String sale_category) 
	{
		return salerMapper.insertWithPassword(password, sale_category);
	}

	@Override
	public int updatePassword(String password, Integer id)
	{
		return salerMapper.updatePassword(password, id);
	}

	@Override
	public int removeSaler(Integer id)
	{
		return salerMapper.deleteById(id);
	}

	@Override
	public Saler getById(Integer id) 
	{
		return salerMapper.findWithId(id);
	}

	@Override
	public List<Saler> getAllSaler() 
	{
		return salerMapper.getAllSaler();
	}

}
