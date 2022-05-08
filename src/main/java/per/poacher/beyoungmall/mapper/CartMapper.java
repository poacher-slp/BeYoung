package per.poacher.beyoungmall.mapper;

import org.apache.ibatis.annotations.Param;
import per.poacher.beyoungmall.pojo.Cart;
import per.poacher.beyoungmall.vo.CartVo;

import java.util.Date;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-10:42
 */
public interface CartMapper {

    Integer insert(Cart cart);

    Integer updateNumByCid(@Param("cid") Integer cid, @Param("num")Integer num,
                           @Param("modifiedTime")Date modifiedTime);

    Cart findByUidAndPid(@Param("uid")Integer uid, @Param("pid")Integer pid);

    List<CartVo> findByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVo> findVoByCid(Integer[] cid);
}
