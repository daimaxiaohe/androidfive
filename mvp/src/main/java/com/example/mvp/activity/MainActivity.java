package com.example.mvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvp.R;
import com.example.mvp.bean.UserBean;
import com.example.mvp.presenters.IloginPresenter;
import com.example.mvp.view.IloginView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements IloginView {

    private EditText mobil,pass;
    private Button login,reg;
    private IloginPresenter iloginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        final HashMap<String,String> map = new HashMap<>();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                map.put("mobile",mobil.getText().toString());
                map.put("password",pass.getText().toString());
                iloginPresenter.login(map);
            }
        });
    }

    private void initView() {
        //获取资源ID
        mobil = findViewById(R.id.mobile);
        pass = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        reg = findViewById(R.id.reg);
        iloginPresenter = new IloginPresenter(this);
    }



    @Override
    public void fail(String string) {
        Toast.makeText(MainActivity.this,string,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserBean userBean) {
        Toast.makeText(MainActivity.this,userBean.msg+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mobileError(String string) {
        Toast.makeText(MainActivity.this,string+"",Toast.LENGTH_SHORT).show();
    }
}
