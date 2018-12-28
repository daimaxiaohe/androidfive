package com.example.mvppractice.utils;

import com.example.mvppractice.api.UserApi;
import com.example.mvppractice.bean.UserBean;
import com.example.mvppractice.net.ResponceCallBack;
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
 * 2018-12-28.
 */
public class OkHttpUtil {
   private OkHttpClient okHttpClient;
    //单例模式
    private static  OkHttpUtil minstance;
    //私有构造方法
    private OkHttpUtil(){
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    //单例方法
    public static OkHttpUtil getMinstance(){
        if (minstance==null){
            synchronized(OkHttpUtil.class){
                if (minstance==null){
                    minstance = new OkHttpUtil();
                }
            }
        }
        return minstance;
    }
    //get获取请求的方法
    public void doGet(HashMap<String,String> map, String uri, final OkHttpCallBack callBack){

        //请求对象
        Request request = new Request.Builder().url(uri).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    if (callBack!=null){
                        callBack.fail("网络请求失败！");
                    }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (200==response.code()) {
                    getjson(response.body().string(),callBack);
                }
            }
        });
    }
    //post请求
    public void doPost(HashMap<String,String> map, String uri, final OkHttpCallBack callBack){
        //请求体的数据  键值对全是字符类型 所以使用原生表单方式  是request子类  FormBody 类
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> p : map.entrySet()) {
            builder.add(p.getKey(),p.getValue());
        }
        //请求对象
        Request request = new Request.Builder().url(uri).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callBack!=null){
                    callBack.fail("网络请求失败！");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (200==response.code()) {
                    getjson(response.body().string(),callBack);
                }
            }
        });
    }
    //解析数据的方法
    private void getjson(String string,OkHttpCallBack callBack){
        //开始解析
        UserBean userBean = new Gson().fromJson(string, UserBean.class);
        if (userBean!=null){
            callBack.success(userBean);
        }
    }
}
