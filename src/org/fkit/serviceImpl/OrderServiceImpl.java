package org.fkit.serviceImpl;

import java.util.List;

import org.fkit.domain.Order;
import org.fkit.domain.OrderMusic;
import org.fkit.mapper.MusicMapper;
import org.fkit.mapper.OrderMapper;
import org.fkit.mapper.OrderMusicMapper;
import org.fkit.mapper.UserMapper;
import org.fkit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private MusicMapper musicMapper;

	@Override
	public int createOrder(int user_id) {
		return orderMapper.insertWithUserId(user_id);
	}

	@Override
	public void addMusicCost(double cost, int id) {
		orderMapper.updateWithCost(cost, id);
	}

	@Override
	public Order getOrderCreated() {
		return orderMapper.getOrderWithStateZero();
	}

	@Override
	public Order getById(int id) {
		return orderMapper.getById(id);
	}

	@Override
	public List<Order> getByUserId(int user_id) {
		return orderMapper.getByUserId(user_id);
	}

	@Override
	public void updateState(int state, int id) {
		orderMapper.updateStateWithId(state, id);
	}

	// 对订单里面的音乐列表进行销售量的更新
	@Override
	public void updateMusicListPurchasesInOrder(int id) {
		// 获取订单
		Order order = orderMapper.getById(id);
		// 通过订单获取Order-Music映射列表
		for (OrderMusic orderMusic : order.getOrderMusicList()) {
			// 通过映射列表取出Music然后对它进行销售量修改
			musicMapper.updatePurchasesById(orderMusic.getMusic().getId());
		}
	}

	// 获取订单中的音乐列表
	@Override
	public List<OrderMusic> getOrderMusicByOrderId(int id) {
		// 获取订单
		Order order = orderMapper.getById(id);
		// 通过订单获取Order-Music映射列表
		return order.getOrderMusicList();
	}

}
