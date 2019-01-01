package com.example.zhoukao1moni.model;

import android.os.Handler;

import com.example.zhoukao1moni.contract.IProductContract;
import com.example.zhoukao1moni.net.ResponceCallBack;
import com.example.zhoukao1moni.utils.OkHttpCallBack;
import com.example.zhoukao1moni.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2018-12-29.
 */
public class ProductModel implements IProductContract.IProductModel {
    //handler发送数据
    Handler handler = new Handler();
    //获取展示数据的方法
    @Override
    public void showview(String uri, Class cc, HashMap<String,String> map, final ResponceCallBack callBack) {
        //开始调用查询的方法
        OkHttpUtil.getMinstance().dopost(uri, cc, map, new OkHttpCallBack() {
            @Override
            public void success(final Object o) {
                if (callBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(o);
                        }
                    });
                }
            }

            @Override
            public void fail(final String string) {
                if (callBack!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.fail(string);
                        }
                    });
                }
            }
        });
    }

}
