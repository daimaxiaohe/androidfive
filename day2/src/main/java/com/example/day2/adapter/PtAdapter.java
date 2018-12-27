package com.example.day2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.day2.R;
import com.example.day2.bean.ShowBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * はすてすゃの
 * 2018-12-27.
 */
public class PtAdapter extends BaseAdapter {
    private Context context;
    private List<ShowBean.DataBean> list;
    public PtAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }
    //赋值数据
    public void setList(List<ShowBean.DataBean> list){
        if (list!=null){
            this.list = list;
        }
        notifyDataSetChanged();
    }
    //添加集合
    public void addlist(List<ShowBean.DataBean> list){
        if (list!=null){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
    private final int TITLE_ONE=0;
    private final int TITLE_TWO=1;
    @Override
    public int getItemViewType(int position) {
        if (position%2!=0){
            return TITLE_ONE;
        }
        return TITLE_TWO;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position)==TITLE_ONE){
            ViewHolder holder;
            if (convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.show_one,parent,false);
                holder = new ViewHolder(convertView);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.settext(position);
        }else{
            ViewHolder holder;
            if (convertView==null){
                convertView = LayoutInflater.from(context).inflate(R.layout.show_two,parent,false);
                holder = new ViewHolder(convertView);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.settext(position);
        }
        return convertView;
    }
    //复用类
    class ViewHolder{
        TextView title;
        TextView price;
        ImageView image;

        public ViewHolder(View convertView) {
            title = convertView.findViewById(R.id.title);
            price = convertView.findViewById(R.id.price);
            image = convertView.findViewById(R.id.image);
            convertView.setTag(this);
        }

        public void settext(int position) {
            title.setText(list.get(position).getTitle());
            price.setText(list.get(position).getPrice()+"");
            if (position%2==0){
                ImageLoader.getInstance().displayImage(list.get(position).getImages(),image);
            }
        }
    }
}
