package per.poacher.beyoungmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import per.poacher.beyoungmall.pojo.Product;
import per.poacher.beyoungmall.service.ProductService;
import per.poacher.beyoungmall.util.Result;
import per.poacher.beyoungmall.util.UploadUtils;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

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
    public Result<List<Product>> getProductList(HttpSession session) {
        Object uid = session.getAttribute("uid");
        List<Product> data = null;
        System.out.println("uid：" + uid);
        if(uid == null) {
            data = productService.findProducts(2);
        }
        else {
            data = productService.findProducts(Integer.valueOf(uid.toString()));
        }
        return new Result<>(OK, data);
    }

    @RequestMapping("search/{search}")
    public Result<List<Product>> getProductsByKeyword(@PathVariable("search") String search, HttpSession session) {
        System.out.println(search);
        Object uid = session.getAttribute("uid");
        if (uid == null) {
            uid = "2";
        }
        System.out.println("uid"+uid);
        List<Product> data = productService.findBykeyword(search,Integer.valueOf(uid.toString()));
        return new Result<>(OK, data);
    }

    @RequestMapping("add")
    public Result addProduct(Product product, @RequestParam("file") MultipartFile file) {
        String productName = product.getItemType();
        String image = UploadUtils.uploadImg(file, productName);
        product.setImage(image);
        productService.insertProduct(product);
        return new Result<>(OK);
    }

    @RequestMapping("{id}/update")
    public Result updateProduct(@PathVariable("id")Integer id, Product product, @RequestParam("file") MultipartFile file) {
        String productName = product.getItemType();
        String image = UploadUtils.uploadImg(file, productName);
        product.setImage(image);
        productService.updateById(id, product);
        return new Result<>(OK);
    }

    @RequestMapping("{id}/delete")
    public Result deleteProduct(@PathVariable("id")Integer id) {
        productService.deleteById(id);
        return new Result<>(OK);
    }
}
