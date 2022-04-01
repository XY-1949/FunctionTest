package net.puge.inside.utils.R;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: LiJW-
 * @date: 2020/4/3 11:54
 * 文件说明：统一返回数据的包装类
 */
@Data
public class R {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<String, Object>();


    //构造方法私有。这类其他人不能new,只有自己可以new
    private R(){};


    //成功静态方法
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static R error(){
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public static R token() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.TOKEN);
        r.setMessage("未登录");
        return r;
    }

    public R success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    //放数据的2个构造方法
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

    public R data(Object object){
        return this;
    }
}
