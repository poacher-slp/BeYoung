package per.poacher.beyoungmall.service;

import per.poacher.beyoungmall.pojo.Address;
import per.poacher.beyoungmall.pojo.Orders;

/**
 * @author poacher
 * @create 2022-05-05-16:18
 */
public interface OrdersService {

    Orders create(Integer aid, Integer uid, String username, Integer[] cids);
}
