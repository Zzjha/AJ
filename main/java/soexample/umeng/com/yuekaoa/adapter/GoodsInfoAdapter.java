package soexample.umeng.com.yuekaoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.yuekaoa.Main2Activity;
import soexample.umeng.com.yuekaoa.Main3Activity;
import soexample.umeng.com.yuekaoa.R;
import soexample.umeng.com.yuekaoa.bean.GoodsInfoBean;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public class GoodsInfoAdapter extends RecyclerView.Adapter<GoodsInfoAdapter.ViewHolder> {
    private List<GoodsInfoBean.DataBean> list;
    private Context context;

    public GoodsInfoAdapter(List<GoodsInfoBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.goodsinfoitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String images = list.get(i).getImages();
        String replace = images.replace("https", "http");
        String[] split = replace.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.img);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.price.setText("￥"+list.get(i).getPrice());

        //点击到详情
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Main3Activity.class);
                intent.putExtra("detailUrl",list.get(0).getDetailUrl());
                intent.putExtra("pid",list.get(0).getPid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView price;
        private TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            price = itemView.findViewById(R.id.price);
            title = itemView.findViewById(R.id.title);
        }
    }
}
