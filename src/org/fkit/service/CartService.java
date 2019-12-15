package org.fkit.service;

import java.util.List;
import org.fkit.domain.Cart;

public interface CartService {

	int addToCart(int user_id, int music_id);

	List<Cart> getCartInfoByUser(int user_id);
	
	int removeFromCartByUserIdAndMusicId(int user_id, int music_id);

	int removeFromCartById(int id);

}
