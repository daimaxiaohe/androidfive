package com.example.zhoukao1moni.utils;

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
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * はすてすゃの
 * 2018-12-29.
 */
public class OkHttpUtil {
    OkHttpClient bulider;
    //单例模式
    private static OkHttpUtil minstance;
    //私有的构造方法
    private OkHttpUtil(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        //okhttp管理者 以及拦截器
        bulider = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(interceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }
    //单例方法
    public static OkHttpUtil getMinstance(){
        if (minstance==null){
            synchronized(OkHttpUtil.class){
                if (minstance==null){
                    minstance=new OkHttpUtil();
                }
            }
        }
        return minstance;
    }
    //网络请求的方法  post请求
    public void dopost(String string, final Class cc, HashMap<String,String> map, final OkHttpCallBack callBack){
        //请求的数据
        FormBody.Builder form = new FormBody.Builder();
        for (Map.Entry<String, String> p : map.entrySet()) {
            form.add(p.getKey(),p.getValue());
        }
        //请求的对象
        Request request = new Request.Builder().url(string).post(form.build()).build();
        bulider.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callBack!=null){
                    callBack.fail("网络请求错误");
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callBack!=null){
                    getjson(response.body().string(),cc,callBack);
                }
            }
        });
    }

    private void getjson(String string, Class cc, OkHttpCallBack callBack) {
        //开始解析
        Object o = new Gson().fromJson(string, cc);
        if (callBack!=null){
            if (o==null){
                callBack.fail("没有数据了");
            }else{
                callBack.success(o);
            }
        }
    }
    //关闭网络请求
    public void closeutil(){
        if (bulider!=null){
            bulider.dispatcher().cancelAll();
        }
    }
}
