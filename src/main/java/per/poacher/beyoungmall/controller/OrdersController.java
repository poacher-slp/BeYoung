package per.poacher.beyoungmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.poacher.beyoungmall.pojo.Orders;
import per.poacher.beyoungmall.service.OrdersService;
import per.poacher.beyoungmall.util.Result;

import javax.servlet.http.HttpSession;

/**
 * @author poacher
 * @create 2022-05-05-16:53
 */
@RestController
@RequestMapping("orders")
public class OrdersController extends BaseController{

    @Autowired
    private OrdersService ordersService;

    @RequestMapping("create")
    public Result<Orders> create(Integer aid, Integer[] cids, HttpSession session) {
        Orders data = ordersService.create(aid, getUidFromSession(session),
                getUsernameFromSession(session), cids);
        return new Result<>(OK, data);
    }
}
