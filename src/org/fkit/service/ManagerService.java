package org.fkit.service;

import java.util.List;

import org.fkit.domain.Manager;

public interface ManagerService {
	
	Manager login(Integer id, String password);
	
	List<Double> getBrowseStatistic();
	
	List<Double> getActiveStatistic();

}
