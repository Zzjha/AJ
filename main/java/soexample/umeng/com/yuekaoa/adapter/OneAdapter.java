package soexample.umeng.com.yuekaoa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.yuekaoa.R;
import soexample.umeng.com.yuekaoa.bean.OneBean;
import soexample.umeng.com.yuekaoa.bean.TwoBean;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public class OneAdapter extends RecyclerView.Adapter<OneAdapter.ViewHolder> {
    private List<OneBean.DataBean> list;
    private Context context;

    public OneAdapter(List<OneBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.oneitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.oneTv.setText(list.get(i).getName());

        //点击左边条目切换右边
        viewHolder.oneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack!=null){
                    callBack.setOnItemClick(v,list.get(i).getCid());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView oneTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            oneTv = itemView.findViewById(R.id.oneTv);
        }
    }


    //接口回调
    public interface CallBack{
        void setOnItemClick(View view,int position);
    }
    private CallBack callBack;
    public void setListen(CallBack callBack){
        this.callBack = callBack;
    }
}
