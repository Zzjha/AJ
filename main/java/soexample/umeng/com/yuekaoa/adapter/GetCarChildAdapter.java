package soexample.umeng.com.yuekaoa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.yuekaoa.R;
import soexample.umeng.com.yuekaoa.bean.GetCarBean;
import soexample.umeng.com.yuekaoa.weight.JiaJianView;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public class GetCarChildAdapter extends RecyclerView.Adapter<GetCarChildAdapter.ViewHolder> {
    private List<GetCarBean.DataBean.ListBean> list;
    private Context context;

    public GetCarChildAdapter(List<GetCarBean.DataBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.getcarchilditem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String images = list.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(viewHolder.img);
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.price.setText("￥"+list.get(i).getPrice());
        //给 num 赋值
        viewHolder.jiajianview.setOnChange(new JiaJianView.OnCountChange() {
            @Override
            public void setCount(int count) {
                callBack.setNum(i,count);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView price;
        private JiaJianView jiajianview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            jiajianview = itemView.findViewById(R.id.jiajianview);
        }
    }

    //给商品数量进行赋值
    public void setGoodsNumber(int position,int num){
        list.get(position).setNum(num);
    }


    //接口回调
    public interface CallBack{
        void setNum(int position,int num);  //num数量设置点击事件的方法
    }
    private CallBack callBack;
    public void setOnLinstener(CallBack callBack){
        this.callBack = callBack;
    }
}
