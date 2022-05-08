package per.poacher.beyoungmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.poacher.beyoungmall.mapper.AddressMapper;
import per.poacher.beyoungmall.mapper.OrdersMapper;
import per.poacher.beyoungmall.pojo.Address;
import per.poacher.beyoungmall.pojo.OrderItem;
import per.poacher.beyoungmall.pojo.Orders;
import per.poacher.beyoungmall.service.AddressService;
import per.poacher.beyoungmall.service.CartService;
import per.poacher.beyoungmall.service.OrdersService;
import per.poacher.beyoungmall.service.ex.InsertException;
import per.poacher.beyoungmall.vo.CartVo;

import java.util.Date;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-16:18
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;

    @Override
    public Orders create(Integer aid, Integer uid, String username, Integer[] cids) {
        List<CartVo> cartVoList = cartService.getVoByCid(cids, uid);
        Integer totalPrice = 0;

        for (CartVo cartVo : cartVoList) {
            totalPrice += cartVo.getRealPrice() * cartVo.getNum();
        }
        Address address = addressService.getByAid(aid, uid);
        Orders orders = new Orders();

        orders.setUserId(uid);
        orders.setReveiverName(address.getReceiverName());
        orders.setReceiverMobile(address.getReceiverMobile());
        String provinceCode = address.getProvinceCode();
        String cityCode = address.getCityCode();
        String areaCode = address.getAreaCode();
        String addr = address.getAddr();
        orders.setReveiverAddress(provinceCode + cityCode + areaCode + addr);

        orders.setStatus(0);
        orders.setTotalAmount(totalPrice);
        orders.setCreateTime(new Date());
        orders.setUpdateTime(new Date());

        Integer rows = ordersMapper.insertOrder(orders);
        if (rows != 1) {
            throw new InsertException("插入数据异常");
        }

        //创建订单项
        for (CartVo cartVo : cartVoList) {
            OrderItem item = new OrderItem();
            item.setOrderId(orders.getOrderId());
            item.setProductId(cartVo.getPid());
            item.setProductName(cartVo.getTitle());
            item.setProductImg(cartVo.getImage());
            item.setBuyCounts(cartVo.getNum());
            item.setProductPrice(cartVo.getRealPrice());
            item.setBuyTime(new Date());
            Integer insert = ordersMapper.insertOrderItem(item);
            if(insert != 1) {
                throw new InsertException("插入数据异常");
            }
        }
        return orders;
    }
}
