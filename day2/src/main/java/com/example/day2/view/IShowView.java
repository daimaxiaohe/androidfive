package com.example.day2.view;

import com.example.day2.bean.ShowBean;

/**
 * はすてすゃの
 * 2018-12-27.
 */
public interface IShowView {
    void success(ShowBean showBean);
    void fail(String string);
}
