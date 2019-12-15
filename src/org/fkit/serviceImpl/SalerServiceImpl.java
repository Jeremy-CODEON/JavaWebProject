package org.fkit.serviceImpl;

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

}
