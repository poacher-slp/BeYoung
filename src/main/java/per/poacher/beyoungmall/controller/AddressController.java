package per.poacher.beyoungmall.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.poacher.beyoungmall.pojo.Address;
import per.poacher.beyoungmall.service.AddressService;
import per.poacher.beyoungmall.util.Result;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author poacher
 * @create 2022-05-04-20:16
 */
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {

    @Autowired
    private AddressService addressService;

    @RequestMapping("addNewAddr")
    public Result addNewAddr(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(uid, username, address);
        return new Result<>(OK);
    }

    @RequestMapping({"","/"})
    public Result<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> data = addressService.getByUid(uid);
        return new Result<>(OK, data);
    }

    @RequestMapping("{aid}/setDefault")
    public Result setDefault(@PathVariable("aid")Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        addressService.setDefault(aid,uid);
        return new Result<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public Result delete(@PathVariable("aid") Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        addressService.delete(aid,uid);
        return new Result<>(OK);
    }

    @RequestMapping("getAddr")
    public Result<Address> getAddr(Integer aid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        Address data = addressService.getByAid(aid, uid);
        return new Result<>(OK, data);
    }

    @RequestMapping("{aid}/update")
    public Result updateByAid(@PathVariable("aid")Integer aid, Address address, HttpSession session) {
        addressService.updateByAid(address, aid);
        return new Result<>(OK);
    }


}
