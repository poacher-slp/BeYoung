package per.poacher.beyoungmall.service.ex;

/** 用户数据不存在异常
 * @author poacher
 * @create 2022-05-04-1:55
 */
public class UserNotFoundtException extends ServiceException{

    public UserNotFoundtException() {
        super();
    }

    public UserNotFoundtException(String message) {
        super(message);
    }

    public UserNotFoundtException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundtException(Throwable cause) {
        super(cause);
    }

    protected UserNotFoundtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
