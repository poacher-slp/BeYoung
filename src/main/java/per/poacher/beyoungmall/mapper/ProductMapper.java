package per.poacher.beyoungmall.mapper;

import org.apache.ibatis.annotations.Param;
import per.poacher.beyoungmall.pojo.Product;

import java.util.Date;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-9:39
 */
public interface ProductMapper {
    /**
     * 根据销售额(优先级)获取热销商品
     * @return
     */
    List<Product> findHotList();

    /**
     * 通过商品id查找化妆品
     * @param id
     * @return
     */
    Product findById(Integer id);

    /**
     * 添加新化妆品
     * @param product
     * @return
     */
    Integer insertProduct(Product product);

    /**
     * 根据id下架化妆品
     * @param id
     * @return
     */
    Integer deleteById(@Param("id") Integer id, @Param("modifiedTime") Date modifiedTime);

    /**
     * 修改商品信息
     * @param product
     * @return
     */
    Integer updateById(Product product);

    /**
     * 通过title模糊查询商品列表
     * @return
     */
    List<Product> findBykeyword(String title);

    /**
     * 获取化妆品列表
     * @return
     */
    List<Product> findProducts();
}
