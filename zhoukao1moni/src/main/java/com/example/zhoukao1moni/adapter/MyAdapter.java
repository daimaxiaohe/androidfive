package com.example.zhoukao1moni.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhoukao1moni.R;
import com.example.zhoukao1moni.bean.ProductBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * はすてすゃの
 * 2018-12-29.
 */
public class MyAdapter extends XRecyclerView.Adapter<MyAdapter.XrVH> {
    private Context context;
    private List<ProductBean.DataBean> list;
    public MyAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    //赋值集合的方法
    public void setList(List<ProductBean.DataBean> list){
        if (list!=null){
            this.list=list;
        }
        notifyDataSetChanged();
    }
    //添加数据的方法
    public void addList(List<ProductBean.DataBean> list){
        if (list!=null){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public XrVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
        XrVH xrVH = new XrVH(view);
        return xrVH;
    }

    @Override
    public void onBindViewHolder(@NonNull XrVH xrVH, int i) {
        xrVH.xr_title.setText(list.get(i).getTitle());
        xrVH.xr_price.setText("商品价格:"+list.get(i).getPrice()+"");
        String[] image = list.get(i).getImages().split("\\|");
        if (image!=null&&image.length>0) {
            Glide.with(context).load(image[0]).into(xrVH.xr_img);
        }else{
            Glide.with(context).load(R.mipmap.ic_launcher).into(xrVH.xr_img);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class XrVH extends XRecyclerView.ViewHolder {
        @BindView(R.id.xr_img)
        ImageView xr_img;
        @BindView(R.id.xr_title)
        TextView xr_title;
        @BindView(R.id.xr_price)
        TextView xr_price;
        public XrVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
