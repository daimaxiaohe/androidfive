package com.example.androidfive.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidfive.R;
import com.example.androidfive.base.BaseActivity;
import com.example.androidfive.utils.xUtils;

public class MainActivity extends BaseActivity {

    private EditText mobile;
    private EditText password;
    private Button login;
    private Button zhu;

    /**
     * 加载视图方法
     * @return
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    /**
     * 操作视图 获取控价的方法
     */
    @Override
    protected void initView() {
        //获取资源ID
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        zhu = findViewById(R.id.zhu);
    }

    /**
     * 操作数据的方法
     */
    @Override
    protected void initData() {
        //登录按钮点击事件  验证
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //注册按钮的点击事件
        zhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    //网络请求数据的方法
    private String get(String path,Class cc){
        xUtils.getInstance().get(path, cc, new xUtils.NeCallBack() {
            @Override
            public void success(Object object) {
                
            }

            @Override
            public void onfail(String string) {
                Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}
