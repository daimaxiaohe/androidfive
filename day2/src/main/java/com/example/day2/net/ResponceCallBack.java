package com.example.day2.net;

import com.example.day2.bean.ShowBean;

/**
 * はすてすゃの
 * 2018-12-27.
 */
public interface ResponceCallBack {
    void success(ShowBean showBean);
    void fail(String string);
}
