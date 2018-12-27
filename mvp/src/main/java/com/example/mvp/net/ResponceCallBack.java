package com.example.mvp.net;

import com.example.mvp.bean.UserBean;

/**
 * はすてすゃの
 * 2018-12-27.
 */
public interface ResponceCallBack {
    void success(UserBean userBean);
    void fail(String s);
}
