package com.example.mvp.model;

import com.example.mvp.net.ResponceCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * はすてすゃの
 * 2018-12-26.
 */
public interface Iloginmodel {
    //接口中 写业务逻辑方法
    void login(HashMap<String,String> param, ResponceCallBack callBack);
}
