package com.example.day3.utils;

import com.example.day3.model.api.UserApi;

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
 * 2018-12-28.
 */
public class OkHttpUtil {
    //单例模式
    private static  OkHttpUtil instance;
    private final OkHttpClient okHttpClient;

    private OkHttpUtil(){
        //OkHTTP管理
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    public static OkHttpUtil getInstance(){
        if (instance==null){
            synchronized (OkHttpUtil.class){
                if (instance==null){
                    instance = new OkHttpUtil();
                }
            }
        }
        return instance;
    }
    public void get(HashMap<String,String> map){

        //请求对象
        Request request = new Request.Builder().url(UserApi.USER_LOGIN).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
    public void post(HashMap<String,String> map){
        //请求体数据
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> p : map.entrySet()) {
            builder.add(p.getKey(),p.getValue());
        }
        //请求对象
        Request request = new Request.Builder().url(UserApi.USER_LOGIN).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
}
