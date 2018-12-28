package com.example.day3.presenter;

import com.example.day3.model.Logmodel;
import com.example.day3.view.ILoginView;

/**
 * はすてすゃの
 * 2018-12-28.
 */
public class ILoginPresenter {
    private Logmodel logmodel;
    private ILoginView iLoginView;
    public ILoginPresenter(ILoginView iLoginView) {
        logmodel = new Logmodel();
        this.iLoginView = iLoginView;
    }

}
