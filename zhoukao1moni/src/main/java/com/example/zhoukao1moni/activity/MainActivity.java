package com.example.zhoukao1moni.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhoukao1moni.R;
import com.example.zhoukao1moni.adapter.MyAdapter;
import com.example.zhoukao1moni.api.ProductApi;
import com.example.zhoukao1moni.bean.ProductBean;
import com.example.zhoukao1moni.contract.IProductContract;
import com.example.zhoukao1moni.presenter.ProductPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IProductContract.IProductView {
    @BindView(R.id.xrlv)
    XRecyclerView xrlv;
    @BindView(R.id.ed_search)
    EditText ed_search;
    private MyAdapter myAdapter;
    private ProductPresenter pro;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    /**
     *  操作视图的方法
     */
    private void initView() {
        page=1;
        //创建适配器实例
        myAdapter = new MyAdapter(MainActivity.this);
        //设置布局管理器
        xrlv.setLayoutManager(new LinearLayoutManager(this));
        //支持刷新
        xrlv.setPullRefreshEnabled(true);
        //设置适配器
        xrlv.setAdapter(myAdapter);
        pro = new ProductPresenter(this);
    }

    /**
     * 操作数据的方法
     */
    private void initData() {
        //Xrecyclearview刷新的方法
        xrlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
               getquery();
            }

            @Override
            public void onLoadMore() {
                getquery();
            }
        });
    }
    @OnClick(R.id.butt_search)
    public void search(){
        page=1;
        if (ed_search!=null&&!ed_search.getText().toString().equals("")){
            getquery();
        }else{
            Toast.makeText(MainActivity.this,"请输入商品名称",Toast.LENGTH_SHORT).show();
        }
    }
    //访问数据的方法
    private void getquery(){
        //创建一个集合
        if (pro!=null){
            HashMap<String, String> map = new HashMap<>();
            map.put("keywords",ed_search.getText().toString());
            map.put("page",page+"");
            pro.show(ProductApi.PRODUCT_API,ProductBean.class, map);
        }
    }
    //成功的方法
    @Override
    public void success(Object o) {
        ProductBean bean = (ProductBean) o;
        if (bean!=null){
            if (page==1){
                myAdapter.setList(bean.getData());
            }else{
                myAdapter.addList(bean.getData());
            }
            xrlv.loadMoreComplete();
            xrlv.refreshComplete();
            if (bean.getData().size()<10){
                xrlv.setLoadingMoreEnabled(false);
            }
            page++;
        }
    }
    //失败的方法
    @Override
    public void fail(String string) {
        Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
    }
    //销毁页面的方法

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pro.setcancel();
    }
}
