package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.Cart;
import cn.edu.cque.mall.entity.CartItem;
import org.springframework.stereotype.Service;

/**
 * @ClassName CartService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/24 15:31
 * @Version 1.0
 **/
@Service
public class CartService {

    public Cart addItem(Cart cart, CartItem cartItem) {
        cart.addCartItem(cartItem);
        return cart;
    }
}
