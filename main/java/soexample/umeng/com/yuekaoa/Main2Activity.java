package soexample.umeng.com.yuekaoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import soexample.umeng.com.yuekaoa.adapter.GoodsInfoAdapter;
import soexample.umeng.com.yuekaoa.bean.GoodsInfoBean;
import soexample.umeng.com.yuekaoa.presenter.MyPresenter;
import soexample.umeng.com.yuekaoa.utils.Contracts;
import soexample.umeng.com.yuekaoa.view.IView;

public class Main2Activity extends AppCompatActivity implements IView {

    @BindView(R.id.recy)
    RecyclerView recy;

    private MyPresenter myPresenter;
    private List<GoodsInfoBean.DataBean> list = new ArrayList<>();
    private GoodsInfoAdapter goodsInfoAdapter;
    private Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        myPresenter = new MyPresenter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recy.setLayoutManager(linearLayoutManager);
        Intent intent = getIntent();
        String pscid = intent.getStringExtra("pscid");
        goodsInfoAdapter = new GoodsInfoAdapter(list, this);
        recy.setAdapter(goodsInfoAdapter);
        map = new HashMap<>();
        map.put("pscid",pscid);
        myPresenter.getData(Contracts.GOODSINFO, GoodsInfoBean.class, map);
    }


    //IView
    @Override
    public void success(Object success) {
        if (success instanceof GoodsInfoBean) {
            GoodsInfoBean goodsInfoBean = (GoodsInfoBean) success;
            list.addAll(goodsInfoBean.getData());
            goodsInfoAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void error(Object error) {

    }
}
