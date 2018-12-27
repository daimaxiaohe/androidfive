package com.example.day2.presenter;

import com.example.day2.bean.ShowBean;
import com.example.day2.model.ShowModel;
import com.example.day2.net.ResponceCallBack;
import com.example.day2.view.IShowView;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2018-12-27.
 */
public class ShowPresenter {
    private ShowModel showModel;
    private IShowView showView;

    public ShowPresenter(IShowView showView) {
        showModel = new ShowModel();
        this.showView = showView;
    }

    public void show(HashMap<String,String> map){
        showModel.show(map, new ResponceCallBack() {
            @Override
            public void success(ShowBean showBean) {
                if (showBean!=null){
                    showView.success(showBean);
                }
            }

            @Override
            public void fail(String string) {
                    showView.fail(string);
            }
        });
    }

}
