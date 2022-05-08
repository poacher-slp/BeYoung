package per.poacher.beyoungmall.util;

import java.io.Serializable;

/**
 * @author poacher
 * @create 2022-05-04-2:30
 */
public class Result<E> implements Serializable {

    private Integer state;  //状态码
    private String message; //描述信息
    private E data;     //数据

    public Result() {
    }

    public Result(Integer state) {
        this.state = state;
    }

    public Result(Throwable e) {
        this.message = e.getMessage();
    }

    public Result(Integer state, E data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
