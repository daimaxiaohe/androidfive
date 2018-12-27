package com.example.mvp.presenters;

import com.example.mvp.bean.UserBean;
import com.example.mvp.model.Iloginmodel;
import com.example.mvp.model.Loginmoedl;
import com.example.mvp.net.ResponceCallBack;
import com.example.mvp.utils.ValidatorUtil;
import com.example.mvp.view.IloginView;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2018-12-26.
 */
public class IloginPresenter {
    private Loginmoedl loginmoedl;
    private IloginView iloginView;
    //构造方法中  先将view层与model层的借口拿到
    public IloginPresenter(IloginView iloginView){
        loginmoedl = new Loginmoedl();
        this.iloginView=iloginView;
    }
    public void login(HashMap<String,String> param){
        //判断手机号是否合法
        if (!ValidatorUtil.isMobile(param.get("mobile"))){
            if (iloginView!=null){
                iloginView.mobileError("请输入合法手机号");
            }
            return;//返回
        }
        if (loginmoedl!=null){
            loginmoedl.login(param, new ResponceCallBack() {
                @Override
                public void success(UserBean userBean) {
                    if (iloginView!=null){
                        iloginView.success(userBean);
                    }
                }

                @Override
                public void fail(String s) {
                    if (iloginView!=null){
                        iloginView.fail(s);
                    }
                }
            });
        }
    }

}
