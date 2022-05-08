package per.poacher.beyoungmall.service;

import per.poacher.beyoungmall.vo.CartVo;

import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-10:43
 */
public interface CartService {

    /**
     * 将商品添加到购物车中
     * @param uid 用户id
     * @param pid 商品id
     * @param amount 新增的数量
     */
    void addToCart(Integer uid, Integer pid, Integer amount);

    List<CartVo> getVoByUid(Integer uid);

    /**
     * 更新用户购物车数据的数量
     * @param cid
     * @param uid
     * @return 增加成功后新的数量
     */
    Integer addNum(Integer cid, Integer uid);

    Integer reduceNum(Integer cid, Integer uid);

    List<CartVo> getVoByCid(Integer[] cids, Integer uid);
}
