package org.fkit.service;

import java.util.List;

import org.fkit.domain.Saler;

public interface SalerService {
	
	Saler login(Integer id,String password);
	
	int addSaler(String password, String sale_category);
	
	int updatePassword(String password, Integer id);
	
	int removeSaler(Integer id);
	
	Saler getById(Integer id);
	
	List<Saler> getAllSaler();

}
