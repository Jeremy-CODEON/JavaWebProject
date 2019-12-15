package org.fkit.serviceImpl;

import java.util.List;

import org.fkit.domain.Cart;
import org.fkit.mapper.CartMapper;
import org.fkit.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("cartService")
public class CartServiceImpl implements CartService {
	@Autowired
	private CartMapper cartMapper;

	@Override
	public int addToCart(int user_id, int music_id) {
		List<Cart> cartList=cartMapper.getByUserIdAndMusicId(user_id, music_id);
		if(cartList.isEmpty())
		{
			return cartMapper.insertWithUserIdAndMusicId(user_id, music_id);
		}
		else
		{
			//限制了同一音乐同一用户只能添加一次
			return 0;
		}
	}

	@Override
	public List<Cart> getCartInfoByUser(int user_id) {
		return cartMapper.getByUserId(user_id);
	}
	
	@Override
	public int removeFromCartByUserIdAndMusicId(int user_id, int music_id) {
		return cartMapper.deleteByUserIdAndMusicId(user_id, music_id);
	}

	@Override
	public int removeFromCartById(int id) {
		return cartMapper.deleteById(id);
	}

}
