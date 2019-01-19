package soexample.umeng.com.yuekaoa.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.yuekaoa.R;
import soexample.umeng.com.yuekaoa.adapter.GetCarGroupAdapter;
import soexample.umeng.com.yuekaoa.bean.GetCarBean;
import soexample.umeng.com.yuekaoa.presenter.MyPresenter;
import soexample.umeng.com.yuekaoa.utils.Contracts;
import soexample.umeng.com.yuekaoa.view.IView;

public class LookFragment extends Fragment implements IView {

    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.checkAll)
    CheckBox checkAll;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.ll)
    LinearLayout ll;
    Unbinder unbinder;

    private MyPresenter myPresenter;
    private List<GetCarBean.DataBean> list = new ArrayList<>();
    private GetCarGroupAdapter getCarGroupAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_look, container, false);
        unbinder = ButterKnife.bind(this, view);
        myPresenter = new MyPresenter(this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(manager);
        getCarGroupAdapter = new GetCarGroupAdapter(list,getActivity());
        recy.setAdapter(getCarGroupAdapter);
        Map<String,String> map = new HashMap<>();
        map.put("uid",71+"");
        myPresenter.getData(Contracts.GETCAR, GetCarBean.class, map);
        return view;
    }


    //IView
    @Override
    public void success(Object success) {
        GetCarBean bean = (GetCarBean) success;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getList()==null){
                list.remove(list.get(i));
            }
        }
        list.addAll(bean.getData());
        getCarGroupAdapter.notifyDataSetChanged();
    }
    @Override
    public void error(Object error) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
