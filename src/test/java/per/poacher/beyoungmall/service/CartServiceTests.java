package per.poacher.beyoungmall.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author poacher
 * @create 2022-05-05-12:03
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartServiceTests {

    @Autowired
    private CartService cartService;

    @Test
    public void addToCart() {
        cartService.addToCart(5, 6, 10);
    }
}
