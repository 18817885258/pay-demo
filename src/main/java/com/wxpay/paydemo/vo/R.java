package com.wxpay.paydemo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 田振兴
 * @create 2022-10-29 10:03
 */
@Data
@Accessors(chain = true)
public class R {
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<String,Object>();

    public static R ok(){
        R r = new R();
        r.setCode(0);
        r.setMessage("成功");
        return r;
    }

    public static R error(){
        R r = new R();
        r.setCode(-1);
        r.setMessage("失败");
        return r;
    }
    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }
}
