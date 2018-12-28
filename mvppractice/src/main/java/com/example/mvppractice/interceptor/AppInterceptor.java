package com.example.mvppractice.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * はすてすゃの
 * 2018-12-28.
 */
public class AppInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //得到开发者创建对象
        Request request = chain.request();
        //得到相应对象
        Response response = chain.proceed(request);
        return response;
    }
}
