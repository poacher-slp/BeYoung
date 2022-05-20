package per.poacher.beyoungmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.poacher.beyoungmall.service.CartService;
import per.poacher.beyoungmall.util.Result;
import per.poacher.beyoungmall.vo.CartVo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-10:44
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController{

    @Autowired
    private CartService cartService;

    @RequestMapping("addToCart")
    public Result addToCart(Integer pid, Integer amount, HttpSession session) {
        Integer uid = getUidFromSession(session);
        cartService.addToCart(uid, pid, amount);
        return new Result<>(OK);
    }

    @RequestMapping({"","/"})
    public Result<List<CartVo>> getVoByUid(HttpSession session) {
        List<CartVo> data = cartService.getVoByUid(getUidFromSession(session));
        return new Result<>(OK, data);
    }

    @RequestMapping("{cid}/num/add")
    public Result<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.addNum(cid, getUidFromSession(session));
        return new Result<>(OK, data);
    }

    @RequestMapping("{cid}/num/reduce")
    public Result<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer data = cartService.reduceNum(cid, getUidFromSession(session));
        return new Result<>(OK, data);
    }

    @RequestMapping("{cid}/delete")
    public Result<Integer> delete(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        Integer data = cartService.deleteById(cid, uid);
        return new Result<>(OK, data);
    }

    @RequestMapping("list")
    public Result<List<CartVo>> getVoByCid(Integer[] cids, HttpSession session) {
        List<CartVo> data = cartService.getVoByCid(cids,getUidFromSession(session));
        return new Result<>(OK, data);
    }
}
