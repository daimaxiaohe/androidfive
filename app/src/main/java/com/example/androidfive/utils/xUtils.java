package com.example.androidfive.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * はすてすゃの
 * 2018-12-26.
 */
public class xUtils {
    //创建一个单例
    private static xUtils instance;
    private xUtils(){

    }
    //单例方法
    public static xUtils getInstance(){
        if (instance==null){
            synchronized (xUtils.class){
                if (instance==null){
                    instance =new xUtils();
                }
            }
        }
        return instance;
    }
    //接口方法
    public interface NeCallBack{
        void success(Object object);
        void onfail(String string);
    }
    //Asynctask方法中获取网络请求
    @SuppressLint("StaticFieldLeak")
    public void get(final String path, final Class cc, final NeCallBack callBack){
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                //子线程  网络请求方法
                Object object = getjson(path, cc);
                return object;
            }

            @Override
            protected void onPostExecute(Object object) {
                super.onPostExecute(object);
                if (object!=null){
                    callBack.success(object);
                }else{
                    callBack.onfail("没有访问到数据");
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,path);
    }
    //json解析方法
    private Object getjson(String path,Class c){
        String json = gethttp(path);
        Object o = new Gson().fromJson(json, c);
        return o;
    }
    //网络请求的方法
    private String gethttp(String path){
        try {
            URL url = new URL(path);
            //打开连接
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode==HttpURLConnection.HTTP_OK){
                //成功   访问到网络进行字符转化
                String text = gettext(urlConnection.getInputStream());
                return text;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //字符类型转化
    private String gettext(InputStream inputStream) throws IOException {
        //字符密文类型
        StringBuilder stringBuilder = new StringBuilder();
        //字符缓冲流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        for (String tem=bufferedReader.readLine();tem!=null;tem=bufferedReader.readLine()){
            stringBuilder.append(tem);
        }
        return stringBuilder.toString();
    }
}
