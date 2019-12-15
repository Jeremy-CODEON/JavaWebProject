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

	// �Զ�������������б�����������ĸ���
	@Override
	public void updateMusicListPurchasesInOrder(int id) {
		// ��ȡ����
		Order order = orderMapper.getById(id);
		// ͨ��������ȡOrder-Musicӳ���б�
		for (OrderMusic orderMusic : order.getOrderMusicList()) {
			// ͨ��ӳ���б�ȡ��MusicȻ����������������޸�
			musicMapper.updatePurchasesById(orderMusic.getMusic().getId());
		}
	}

	// ��ȡ�����е������б�
	@Override
	public List<OrderMusic> getOrderMusicByOrderId(int id) {
		// ��ȡ����
		Order order = orderMapper.getById(id);
		// ͨ��������ȡOrder-Musicӳ���б�
		return order.getOrderMusicList();
	}

}
