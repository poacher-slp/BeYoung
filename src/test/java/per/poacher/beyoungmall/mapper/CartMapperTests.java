package per.poacher.beyoungmall.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.poacher.beyoungmall.pojo.Cart;
import per.poacher.beyoungmall.vo.CartVo;

import java.util.Date;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-11:38
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CartMapperTests {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(5);
        cart.setPid(2);
        cart.setNum(2);
        cart.setPrice(1000);
        cartMapper.insert(cart);
    }

    @Test
    public void updateNumByCid() {
        cartMapper.updateNumByCid(1, 4, new Date());
    }

    @Test
    public void findByUidAndPid() {
        Cart cart = cartMapper.findByUidAndPid(5, 2);
        System.out.println(cart);
    }

    @Test
    public void findByUid() {
        List<CartVo> list = cartMapper.findByUid(5);
        System.out.println(list);
    }

    @Test
    public void findByCid() {
        Cart cart = cartMapper.findByCid(4);
        System.out.println(cart);
    }

    @Test
    public void findVoByCid() {
        Integer[] cids = {1, 2, 3, 10, 11, 12};
        List<CartVo> voByCid = cartMapper.findVoByCid(cids);
        System.out.println(voByCid);
    }
}
