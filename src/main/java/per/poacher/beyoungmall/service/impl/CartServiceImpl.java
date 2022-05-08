package per.poacher.beyoungmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.poacher.beyoungmall.mapper.CartMapper;
import per.poacher.beyoungmall.mapper.ProductMapper;
import per.poacher.beyoungmall.pojo.Cart;
import per.poacher.beyoungmall.pojo.Product;
import per.poacher.beyoungmall.service.CartService;
import per.poacher.beyoungmall.service.ex.AccessDeniedException;
import per.poacher.beyoungmall.service.ex.CartNotFoundException;
import per.poacher.beyoungmall.service.ex.InsertException;
import per.poacher.beyoungmall.service.ex.UpdateException;
import per.poacher.beyoungmall.vo.CartVo;

import java.rmi.AccessException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-10:43
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount) {
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        Date date = new Date();
        if(result == null) {
            Cart cart = new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);
            Product product = productMapper.findById(pid);
            cart.setPrice(product.getPrice());
            cart.setCreateTime(date);
            cart.setModifiedTime(date);
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("插入数据时产生未知的异常");
            }
        }
        else {
            Integer num = result.getNum() + amount;
            Integer rows = cartMapper.updateNumByCid(result.getCid(), num, date);
            if(rows != 1) {
                throw new UpdateException("更新数据时产生未知的异常");
            }
        }
    }

    @Override
    public List<CartVo> getVoByUid(Integer uid) {
        return cartMapper.findByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null) {
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, new Date());
        if(rows != 1) {
            throw new UpdateException("更新数据时产生的异常");
        }
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid) {
        Cart result = cartMapper.findByCid(cid);
        if(result == null) {
            throw new CartNotFoundException("数据不存在");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num = result.getNum() - 1;
        Integer rows = cartMapper.updateNumByCid(cid, num, new Date());
        if(rows != 1) {
            throw new UpdateException("更新数据时产生的异常");
        }
        return num;
    }

    @Override
    public List<CartVo> getVoByCid(Integer[] cids, Integer uid) {
        List<CartVo> list = cartMapper.findVoByCid(cids);
        Iterator<CartVo> iterator = list.iterator();
        while (iterator.hasNext()) {
            CartVo next = iterator.next();
            if(!next.getUid().equals(uid)) {
                list.remove(next);
            }
        }
        return list;
    }
}
