package cn.edu.cque.mall.service;

import cn.edu.cque.mall.entity.Cart;
import cn.edu.cque.mall.entity.CartItem;

/**
 * @ClassName CartService
 * @Description TODO
 * @Author YoungWinter
 * @Date 2020/9/24 15:31
 * @Version 1.0
 **/
public class CartService {

    public Cart addItem(Cart cart, CartItem cartItem) {
        cart.addCartItem(cartItem);
        return cart;
    }
}
