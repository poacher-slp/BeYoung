package per.poacher.beyoungmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.poacher.beyoungmall.pojo.Product;
import per.poacher.beyoungmall.service.ProductService;
import per.poacher.beyoungmall.util.Result;

import java.util.List;

/**
 * @author poacher
 * @create 2022-05-05-9:50
 */
@RestController
@RequestMapping("products")
public class ProductController extends BaseController{

    @Autowired
    private ProductService productService;

    @RequestMapping("hotlist")
    public Result<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return new Result<List<Product>>(OK, data);
    }

    @RequestMapping("{id}/details")
    public Result<Product> getById(@PathVariable("id")Integer id) {
        Product data = productService.findById(id);
        return new Result<Product>(OK, data);
    }

    @RequestMapping({"","/"})
    public Result<List<Product>> getProductList() {
        List<Product> data = productService.findProducts();
        return new Result<>(OK, data);
    }

    @RequestMapping("add")
    public Result addProduct(Product product) {
        productService.insertProduct(product);
        return new Result<>(OK);
    }

    @RequestMapping("{id}/update")
    public Result updateProduct(@PathVariable("id")Integer id,Product product) {
        productService.updateById(id, product);
        return new Result<>(OK);
    }

    @RequestMapping("{id}/delete")
    public Result deleteProduct(@PathVariable("id")Integer id) {
        productService.deleteById(id);
        return new Result<>(OK);
    }
}
