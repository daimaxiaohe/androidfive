package com.example.day2.model;

import android.os.Handler;

import com.example.day2.api.ShowApi;
import com.example.day2.bean.ShowBean;
import com.example.day2.net.ResponceCallBack;
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
 * 2018-12-27.
 */
public class ShowModel implements IHsow{
    Handler handler = new Handler();
    //网络请求数据的方法
    @Override
    public void show(HashMap<String,String> map, final ResponceCallBack callBack) {
        //okhttp 网络框架管理类
        OkHttpClient build = new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
        //请求体 创建
        FormBody.Builder form = new FormBody.Builder();
        for(Map.Entry<String,String> p :map.entrySet()){
            form.add(p.getKey(),p.getValue());
        }
        //请求
        Request request = new Request.Builder().url(ShowApi.SHOWAPI).post(form.build()).build();
        //Call类
        Call call = build.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.fail("网络请求失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取字符串数据
                String text = response.body().string();
                //判断是否是访问成功
                if (200==response.code()){
                    getjson(text,callBack);
                }
            }
        });
    }
    //网络解析数据的方法
    private void getjson(String text, final ResponceCallBack callBack){
        //开始解析
        final ShowBean showBean = new Gson().fromJson(text, ShowBean.class);
        if (callBack!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callBack.success(showBean);
                }
            });
        }
    }
}
