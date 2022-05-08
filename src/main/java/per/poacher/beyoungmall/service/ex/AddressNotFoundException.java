package per.poacher.beyoungmall.service.ex;

/** 收货地址数据不存在的异常
 * @author poacher
 * @create 2022-05-04-1:55
 */
public class AddressNotFoundException extends ServiceException{

    public AddressNotFoundException() {
        super();
    }

    public AddressNotFoundException(String message) {
        super(message);
    }

    public AddressNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AddressNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
