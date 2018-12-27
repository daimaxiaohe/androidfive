package com.example.mvppractice.contract;

import com.example.mvppractice.bean.UserBean;
import com.example.mvppractice.net.ResponceCallBack;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2018-12-27.
 */
public interface ILogincontract {

    public abstract class ILogpresenter{
        public abstract void login(HashMap<String,String> map);
        public abstract void reg(HashMap<String,String> map);
    }

    public interface ILoginModel{
        void setokhttp(HashMap<String,String> map, String string, ResponceCallBack callBack);
    }
    public interface IloginView{
        public void success(UserBean userBean);
        public void fail(String string);
        public void mobileerror(String error);
    }
}
