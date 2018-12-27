package com.example.day2.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.day2.R;
import com.example.day2.activity.BaseActivity;
import com.example.day2.adapter.PtAdapter;
import com.example.day2.bean.ShowBean;
import com.example.day2.presenter.ShowPresenter;
import com.example.day2.view.IShowView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.HashMap;

public class MainActivity extends BaseActivity implements IShowView {
    private final int pscid=39;
    private int page;
    private PullToRefreshListView ptlv;
    private ShowPresenter showPresenter;
    private PtAdapter ptAdapter;
    private HashMap<String, String> map;

    /**
     * 加载视图的方法
     * @return
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    /**
     * 操作视图 获取控件的方法
     */
    @Override
    protected void initView() {
        page=1;
        //获取资源ID
        ptlv = findViewById(R.id.ptlv);
        ptlv.setMode(PullToRefreshBase.Mode.BOTH);
        //实例化presenter类
        showPresenter = new ShowPresenter(this);
        //设置适配器
        ptAdapter = new PtAdapter(MainActivity.this);
        ptlv.setAdapter(ptAdapter);
    }

    /**
     * 操作数据的方法
     */
    @Override
    protected void initData() {
        map = new HashMap<>();
        map.put("pscid",pscid+"");
        map.put("page",page+"");
        showPresenter.show(map);
        ptlv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                map.put("pscid",pscid+"");
                map.put("page",page+"");
                showPresenter.show(map);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                map.put("pscid",pscid+"");
                map.put("page",page+"");
                showPresenter.show(map);
            }
        });
    }

    @Override
    public void success(ShowBean showBean) {
        if (showBean!=null){
            if (page==1){
                ptAdapter.setList(showBean.getData());
            }else{
                ptAdapter.addlist(showBean.getData());
            }
            ptlv.onRefreshComplete();
            /*if (showBean.getData().size()<pscid){
                ptlv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
            }*/
            page++;
        }
    }

    @Override
    public void fail(String string) {
        Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
    }
}
