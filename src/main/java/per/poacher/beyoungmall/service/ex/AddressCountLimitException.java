package per.poacher.beyoungmall.service.ex;

/** 收货地址总数超出限制(20)的异常
 * @author poacher
 * @create 2022-05-04-1:55
 */
public class AddressCountLimitException extends ServiceException{

    public AddressCountLimitException() {
        super();
    }

    public AddressCountLimitException(String message) {
        super(message);
    }

    public AddressCountLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressCountLimitException(Throwable cause) {
        super(cause);
    }

    protected AddressCountLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
