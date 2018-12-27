package com.example.mvppractice.presenter;

import com.example.mvppractice.api.UserApi;
import com.example.mvppractice.bean.UserBean;
import com.example.mvppractice.contract.ILogincontract;
import com.example.mvppractice.model.LoginModel;
import com.example.mvppractice.net.ResponceCallBack;
import com.example.mvppractice.utils.ValidatorUtil;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2018-12-27.
 */
public class LogPresenter extends ILogincontract.ILogpresenter {
    private LoginModel loginModel;
    private ILogincontract.IloginView iloginView;
    public LogPresenter(ILogincontract.IloginView iloginView) {
        loginModel = new LoginModel();
        this.iloginView = iloginView;
    }

    //登录的方法
    @Override
    public void login(HashMap<String, String> map) {
        String mobile= map.get("mobile");
        if (!ValidatorUtil.isMobile(mobile)){
            iloginView.mobileerror("手机号不合法");
            return;
        }
        loginModel.setokhttp(map, UserApi.USER_LOGIN, new ResponceCallBack() {
            @Override
            public void success(UserBean userBean) {
                if (userBean!=null){
                    iloginView.success(userBean);
                }
            }

            @Override
            public void fail(String string) {
                iloginView.fail(string);
            }
        });
    }


    //注册的方法
    @Override
    public void reg(HashMap<String,String> map) {
        String mobile= map.get("mobile");
        if (!ValidatorUtil.isMobile(mobile)){
            iloginView.mobileerror("手机号不合法");
            return;
        }
        loginModel.setokhttp(map, UserApi.USER_REG, new ResponceCallBack() {
            @Override
            public void success(UserBean userBean) {
                if (userBean!=null){
                    iloginView.success(userBean);
                }
            }
            @Override
            public void fail(String string) {
                iloginView.fail(string);
            }
        });
    }
}
