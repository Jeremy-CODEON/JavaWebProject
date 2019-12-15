package org.fkit.serviceImpl;

import java.util.List;

import org.fkit.domain.Order;
import org.fkit.domain.OrderMusic;
import org.fkit.mapper.OrderMapper;
import org.fkit.mapper.OrderMusicMapper;
import org.fkit.mapper.UserMapper;
import org.fkit.service.OrderMusicService;
import org.fkit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("orderMusicService")
public class OrderMusicServiceImpl implements OrderMusicService{
	
	@Autowired
	private OrderMusicMapper orderMusicMapper;
	
	@Override
	public List<OrderMusic> getByOrderId(int order_id)
	{
		return orderMusicMapper.getByOrderId(order_id);
	}
	
	@Override 
	public int addMusicIntoOrder(int order_id, int music_id)
	{
		return orderMusicMapper.insert(order_id, music_id);
	}

}
