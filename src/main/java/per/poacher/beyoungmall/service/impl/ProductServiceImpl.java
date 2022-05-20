package per.poacher.beyoungmall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.poacher.beyoungmall.mapper.ProductMapper;
import per.poacher.beyoungmall.mapper.UserMapper;
import per.poacher.beyoungmall.pojo.Product;
import per.poacher.beyoungmall.pojo.User;
import per.poacher.beyoungmall.service.ProductService;
import per.poacher.beyoungmall.service.ex.DeleteException;
import per.poacher.beyoungmall.service.ex.InsertException;
import per.poacher.beyoungmall.service.ex.ProductNotFoundException;
import per.poacher.beyoungmall.service.ex.UpdateException;

import java.util.Date;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-9:42
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> hotList = productMapper.findHotList();
        return hotList;
    }

    @Override
    public Product findById(Integer id) {
        Product result = productMapper.findById(id);
        if(result == null) {
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        return result;
    }

    @Override
    public void insertProduct(Product product) {
        product.setCategoryId(1);
        product.setStatus(1);
        product.setPriority(0);
        product.setCreatedTime(new Date());
        product.setModifiedTime(new Date());
        Integer rows = productMapper.insertProduct(product);
        if(rows != 1) {
            throw new InsertException("新增化妆品失败");
        }
    }

    @Override
    public void deleteById(Integer id) {
        Date modifiedTime = new Date();
        Integer rows = productMapper.deleteById(id, modifiedTime);
        if (rows != 1) {
            throw new DeleteException("删除化妆品失败");
        }
    }

    @Override
    public void updateById(Integer id, Product product) {
        product.setId(id);
        product.setModifiedTime(new Date());
        Integer rows = productMapper.updateById(product);
        if(rows != 1) {
            throw new UpdateException("更新化妆品失败");
        }
    }

    @Override
    public List<Product> findBykeyword(String title, Integer uid) {
        User user = userMapper.findByUid(uid);
        Integer role = user.getRole();
        List<Product> list = productMapper.findBykeyword(title, role);
        return list;
    }

    @Override
    public List<Product> findProducts(Integer uid) {
        User user = userMapper.findByUid(uid);
        Integer role = user.getRole();
        List<Product> list = productMapper.findProducts(role);
        return list;
    }
}
