package per.poacher.beyoungmall.mapper;

import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.poacher.beyoungmall.pojo.OrderItem;
import per.poacher.beyoungmall.pojo.Orders;

/**
 * @author poacher
 * @create 2022-05-05-16:00
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrdersMapperTests {

    @Autowired
    private OrdersMapper ordersMapper;

    @Test
    public void insertOrder() {
        Orders orders = new Orders();
        orders.setUserId(6);
        orders.setReveiverName("tom");
        orders.setReceiverMobile("123456");
        ordersMapper.insertOrder(orders);
    }

    @Test
    public void insertOrderItem() {
        OrderItem item = new OrderItem();
        item.setOrderId(1);
        item.setProductId(3);
        item.setProductName("欧莱雅-爽肤水");
        ordersMapper.insertOrderItem(item);
    }
}
