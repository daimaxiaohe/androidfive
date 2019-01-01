package com.example.zhoukao1moni.contract;

import com.example.zhoukao1moni.net.ResponceCallBack;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2018-12-29.
 */
public interface IProductContract {
    //一个p层的抽象类
    public abstract class IProductPresenter{
        public abstract void show(String uri, Class cc, HashMap<String,String> map);
    }
    //一个view层的接口
    public interface IProductView{
        void success(Object o);
        void fail(String string);
    }
    //一个model层的接口
    public interface IProductModel{
        void showview(String string, Class cc, HashMap<String,String> map, ResponceCallBack callBack);
    }
}
