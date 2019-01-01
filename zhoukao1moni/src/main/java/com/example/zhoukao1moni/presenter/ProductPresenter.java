package com.example.zhoukao1moni.presenter;

import com.example.zhoukao1moni.contract.IProductContract;
import com.example.zhoukao1moni.model.ProductModel;
import com.example.zhoukao1moni.net.ResponceCallBack;
import com.example.zhoukao1moni.utils.OkHttpUtil;

import java.util.HashMap;

/**
 * はすてすゃの
 * 2018-12-29.
 */
public class ProductPresenter extends IProductContract.IProductPresenter {
    private IProductContract.IProductView productView;
    private ProductModel productModel;
    public ProductPresenter(IProductContract.IProductView productView) {
        productModel = new ProductModel();
        this.productView = productView;
    }

    //展示的方法
    @Override
    public void show(String uri, Class cc, HashMap<String,String> map){
        if (map!=null){
            productModel.showview(uri, cc, map, new ResponceCallBack() {
                @Override
                public void success(Object o) {
                    if (productView!=null){
                        productView.success(o);
                    }
                }

                @Override
                public void fail(String string) {
                    if (productView!=null){
                        productView.fail(string);
                    }
                }
            });
        }
    }
    //防止内存泄漏
    public void setcancel(){
        if (productView!=null){
            productView=null;
            OkHttpUtil.getMinstance().closeutil();
        }
    }
}
