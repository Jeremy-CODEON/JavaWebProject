package org.fkit.service;

import java.util.List;

import org.fkit.domain.Order;
import org.fkit.domain.OrderMusic;

public interface OrderService {
	int createOrder(int user_id);
	
	void addMusicCost(double cost,int id);
	
	Order getOrderCreated();
	
	Order getById(int id);
	
	List<Order> getByUserId(int user_id);
	
	void updateState(int state,int id);
	
	void updateMusicListPurchasesInOrder(int id);
	
	List<OrderMusic> getOrderMusicByOrderId(int id);

}
