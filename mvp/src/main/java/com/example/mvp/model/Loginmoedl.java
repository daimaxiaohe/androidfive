package com.example.mvp.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.mvp.api.Userapi;
import com.example.mvp.bean.UserBean;
import com.example.mvp.net.ResponceCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * はすてすゃの
 * 2018-12-26.
 */
public class Loginmoedl implements Iloginmodel{
    Handler handler = new Handler();
    //业务逻辑方法
    @Override
    public void login(HashMap<String, String> param, final ResponceCallBack callBack) {
        //okhttp 网络框架管理者
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
        //post 请求体 构建数据的过程
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> p : param.entrySet()) {
            builder.add(p.getKey(),p.getValue());
        }

        //创建请求对象
        final Request request = new Request.Builder().url(Userapi.USER_LOGIN).post(builder.build()).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            String result="";
            //失败
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.fail("网络连接超时");
                    }
                });
            }
            //成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result = response.body().string();
                //成功  开始解析
                int code = response.code();
                if (200==code){
                    if (!TextUtils.isEmpty(result)){
                        setjson(result,callBack);
                    }
                }
            }
        });
    }
    //解析的方法
    private void setjson(String result, final ResponceCallBack callBack){
        final UserBean userBean = new Gson().fromJson(result, UserBean.class);
        if (callBack!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.success(userBean);
                }
            });
        }
    }
}
