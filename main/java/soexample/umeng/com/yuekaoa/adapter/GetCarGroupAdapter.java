package soexample.umeng.com.yuekaoa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import soexample.umeng.com.yuekaoa.R;
import soexample.umeng.com.yuekaoa.bean.GetCarBean;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public class GetCarGroupAdapter extends RecyclerView.Adapter<GetCarGroupAdapter.ViewHolder> {
    private List<GetCarBean.DataBean> list;
    private Context context;

    public GetCarGroupAdapter(List<GetCarBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.getcargroupitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(list.get(i).getSellerName());

        //child
        List<GetCarBean.DataBean.ListBean> lists = this.list.get(i).getList();
        GetCarChildAdapter getCarChildAdapter = new GetCarChildAdapter(lists,context);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        viewHolder.childrecy.setLayoutManager(manager);
        viewHolder.childrecy.setAdapter(getCarChildAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RecyclerView childrecy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            childrecy = itemView.findViewById(R.id.childrecy);
        }
    }
}
