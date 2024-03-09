package edu.nju.doudou.doutaocart.service;

import edu.nju.doudou.doutaocart.vo.CartItemVo;
import edu.nju.doudou.doutaocart.vo.CartVo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface CartService {
    /**
     * 添加商品到购物车
     * @param skuId
     * @param num
     */
    CartItemVo addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    /**
     * 获取购物车某个购物项
     * @param skuId
     * @return
     */

    /**
     * 获取购物车
     * @return
     */
    CartVo getCart() throws ExecutionException, InterruptedException;

    void clearCartInfo(String cartKey);

    List<CartItemVo> getUserCartItems();

    /**
     * 获取购物车某个购物项
     * @param skuId
     * @return
     */
    CartItemVo getCartItem(Long skuId);

    void deleteIdCartInfo(Integer skuId);

    void changeItemCount(Long skuId, Integer num);

    void checkItem(Long skuId, Integer checked);
}
