package soexample.umeng.com.yuekaoa.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.yuekaoa.Main2Activity;
import soexample.umeng.com.yuekaoa.R;
import soexample.umeng.com.yuekaoa.adapter.OneAdapter;
import soexample.umeng.com.yuekaoa.adapter.TwoAdapter;
import soexample.umeng.com.yuekaoa.bean.OneBean;
import soexample.umeng.com.yuekaoa.bean.TwoBean;
import soexample.umeng.com.yuekaoa.presenter.MyPresenter;
import soexample.umeng.com.yuekaoa.utils.Contracts;
import soexample.umeng.com.yuekaoa.view.IView;

public class SortFragment extends Fragment implements IView {

    @BindView(R.id.oneRecy)
    RecyclerView oneRecy;
    @BindView(R.id.twoRecy)
    RecyclerView twoRecy;
    Unbinder unbinder;

    private MyPresenter myPresenter;

    private List<OneBean.DataBean> oneList = new ArrayList<>();
    private OneAdapter oneAdapter;
    private Map<String, String> map1;
    private List<TwoBean.DataBean> twoList = new ArrayList<>();
    private TwoAdapter twoAdapter;
    private Map<String, String> map2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        //一级列表展示
        oneAdapter = new OneAdapter(oneList, getActivity());
        oneRecy.setAdapter(oneAdapter);
        map1 = new HashMap<>();
        myPresenter.getData(Contracts.ONE, OneBean.class, map1);
        //默认给二级列表展示 uid=1
        twoAdapter = new TwoAdapter(twoList, getActivity());
        twoRecy.setAdapter(twoAdapter);
        map2 = new HashMap<>();
        map2.put("cid",1+"");
        myPresenter.getData(Contracts.TWO, TwoBean.class, map2);


        //接口回调  点击一级列表条目切换右边
        oneAdapter.setListen(new OneAdapter.CallBack() {
            @Override
            public void setOnItemClick(View view, int position) {
                twoList.clear();
                twoAdapter = new TwoAdapter(twoList, getActivity());
                twoRecy.setAdapter(twoAdapter);
                map2 = new HashMap<>();
                map2.put("cid",position+"");
                myPresenter.getData(Contracts.TWO, TwoBean.class, map2);
            }
        });
        return view;
    }

    private void initView() {
        myPresenter = new MyPresenter(this);
        //左边 一级列表
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        oneRecy.setLayoutManager(linearLayoutManager);
        //右边 二级列表
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),3);
        twoRecy.setLayoutManager(gridLayoutManager);
    }


    //IView
    @Override
    public void success(Object success) {
        if(success instanceof OneBean){
            OneBean oneBean = (OneBean) success;
            oneList.addAll(oneBean.getData());
            oneAdapter.notifyDataSetChanged();
        }else{
            TwoBean twoBean = (TwoBean) success;
            twoList.addAll(twoBean.getData());
            twoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void error(Object error) {
        Log.e("aaa", error.toString());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
