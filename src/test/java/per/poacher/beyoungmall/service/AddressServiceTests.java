package per.poacher.beyoungmall.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import per.poacher.beyoungmall.pojo.Address;

/**
 * @author poacher
 * @create 2022-05-04-20:06
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {

    @Autowired
    private AddressService addressService;

    @Test
    public void addNewAddress() {
        Address address = new Address();
        address.setReceiverMobile("123456");
        address.setReceiverName("sam");
        addressService.addNewAddress(5, "zhaosi4", address);
    }

    @Test
    public void setDefault() {
        addressService.setDefault(7,5);
    }

    @Test
    public void delete() {
        addressService.delete(7,5);
    }
}
