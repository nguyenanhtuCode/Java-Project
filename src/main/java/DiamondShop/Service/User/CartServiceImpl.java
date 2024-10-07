package DiamondShop.Service.User;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DiamondShop.Dao.CartDao;
import DiamondShop.Dto.CartDto;

@Service
public class CartServiceImpl implements ICartService {
	@Autowired
	private CartDao cartDao = new CartDao();

	@Override
	public HashMap<Integer, CartDto> addCart(int id, HashMap<Integer, CartDto> cart) {
		return cartDao.addCart(id, cart);
	}

	@Override
	public HashMap<Integer, CartDto> editCart(int id, int quantity, HashMap<Integer, CartDto> cart) {
		return cartDao.editCart(id, quantity, cart);
	}

	@Override
	public HashMap<Integer, CartDto> deleteCart(int id, HashMap<Integer, CartDto> cart) {
		return cartDao.deleteCart(id, cart);
	}

	@Override
	public int totalQuantity(HashMap<Integer, CartDto> cart) {
		return cartDao.totalQuantity(cart);
	}

	@Override
	public double totalPrice(HashMap<Integer, CartDto> cart) {
		return cartDao.totalPrice(cart);
	}
}