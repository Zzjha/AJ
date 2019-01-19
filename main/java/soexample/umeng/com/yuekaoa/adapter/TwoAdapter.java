package soexample.umeng.com.yuekaoa.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.yuekaoa.Main2Activity;
import soexample.umeng.com.yuekaoa.R;
import soexample.umeng.com.yuekaoa.bean.OneBean;
import soexample.umeng.com.yuekaoa.bean.TwoBean;
import soexample.umeng.com.yuekaoa.fragment.ShopCarFragment;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.ViewHolder> {
    private List<TwoBean.DataBean> list;
    private Context context;

    public TwoAdapter(List<TwoBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.twoitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(context).load(list.get(i).getList().get(0).getIcon()).into(viewHolder.twoImg);
        viewHolder.twoTv.setText(list.get(i).getList().get(0).getName());


        //点击跳转到列表
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Main2Activity.class);
                intent.putExtra("pscid",list.get(0).getList().get(i).getPscid()+"");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView twoImg;
        private TextView twoTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            twoImg = itemView.findViewById(R.id.twoImg);
            twoTv = itemView.findViewById(R.id.twoTv);
        }
    }
}
