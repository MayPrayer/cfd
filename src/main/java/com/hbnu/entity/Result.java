package com.hbnu.entity;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Result <br/>
 * Description: 统一json数据格式
 * date: 2020/2/25 16:41<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
public class Result {
    private String code;    //状态码
    private String message; //接口处理信息
    private int count;     //数据总数
    private Object data = new Object(); //容纳数据（json对象）


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        Result result = new Result();
        result.setCode("0");
        result.setMessage("操作成功");
        return result;
    }

    public static Result failed(String message) {
        Result result = new Result();
        result.setCode("404");
        result.setMessage(message);
        return result;
    }


    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }


}
