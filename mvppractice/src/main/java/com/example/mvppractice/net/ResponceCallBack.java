package com.example.mvppractice.net;

import com.example.mvppractice.bean.UserBean;

/**
 * はすてすゃの
 * 2018-12-27.
 */
public interface ResponceCallBack {
    void success(UserBean userBean);
    void fail(String string);
}
