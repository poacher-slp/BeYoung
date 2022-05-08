package per.poacher.beyoungmall.mapper;

import per.poacher.beyoungmall.pojo.OrderItem;
import per.poacher.beyoungmall.pojo.Orders;

/**
 * @author poacher
 * @create 2022-05-05-15:47
 */
public interface OrdersMapper {

    /**
     * 插入订单数据
     * @param order
     * @return
     */
    Integer insertOrder(Orders order);

    /**
     * 插入订单项的数据
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem);
}
