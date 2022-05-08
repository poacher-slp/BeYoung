package per.poacher.beyoungmall.service;

import per.poacher.beyoungmall.pojo.Product;

import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-9:42
 */
public interface ProductService {

    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id
     * @return
     */
    Product findById(Integer id);

    /**
     * 新增化妆品
     * @param product
     * @return
     */
    void insertProduct(Product product);

    /**
     * 下架化妆品
     * @param id
     * @return
     */
    void deleteById(Integer id);

    /**
     * 更新化妆品
     * @param id
     * @return
     */
    void updateById(Integer id, Product product);

    /**
     * 模糊查询化妆品
     * @param title
     * @return
     */
    List<Product> findBykeyword(String title);

    List<Product> findProducts();
}
