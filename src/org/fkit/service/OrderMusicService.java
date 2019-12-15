package org.fkit.service;

import java.util.List;

import org.fkit.domain.OrderMusic;

public interface OrderMusicService {
	
	List<OrderMusic> getByOrderId(int order_id);
	
	int addMusicIntoOrder(int order_id, int music_id);

}
