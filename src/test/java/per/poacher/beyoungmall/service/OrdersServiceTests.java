package per.poacher.beyoungmall.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.poacher.beyoungmall.pojo.Orders;

/**
 * @author poacher
 * @create 2022-05-05-16:47
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrdersServiceTests {

    @Autowired
    private OrdersService ordersService;

    @Test
    public void create() {
        Integer[] cids = {3, 4, 5};
        Orders orders = ordersService.create(10, 6, "bob", cids);
        System.out.println(orders);
    }
}
