package com.example.mvppractice.utils;

import com.example.mvppractice.bean.UserBean;

/**
 * はすてすゃの
 * 2018-12-28.
 */
public interface OkHttpCallBack {
    void success(UserBean userBean);
    void fail(String string);
}
