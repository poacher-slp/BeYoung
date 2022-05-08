package per.poacher.beyoungmall.service.ex;

/** 在更新数据时产生的未知异常
 * @author poacher
 * @create 2022-05-04-13:53
 */
public class UpdateException  extends  ServiceException{
    public UpdateException() {
        super();
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(Throwable cause) {
        super(cause);
    }

    protected UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
