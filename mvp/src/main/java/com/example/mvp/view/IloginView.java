package com.example.mvp.view;

import com.example.mvp.bean.UserBean;

/**
 * はすてすゃの
 * 2018-12-26.
 */
public interface IloginView {
    void fail(String string);
    void success(UserBean userBean);
    void mobileError(String string);
}
