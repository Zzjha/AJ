package soexample.umeng.com.yuekaoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.yuekaoa.bean.AddCarBean;
import soexample.umeng.com.yuekaoa.presenter.MyPresenter;
import soexample.umeng.com.yuekaoa.utils.Contracts;
import soexample.umeng.com.yuekaoa.view.IView;

public class Main3Activity extends AppCompatActivity implements IView {

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.addCar)
    Button addCar;

    private MyPresenter myPresenter;
    private String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        initView();
        Intent intent = getIntent();
        String detailUrl = intent.getStringExtra("detailUrl");
        pid = intent.getStringExtra("pid");
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(detailUrl);
    }

    private void initView() {
        myPresenter = new MyPresenter(this);
    }


    @OnClick(R.id.addCar)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.addCar:
                Map<String,String> map = new HashMap<>();
                map.put("uid",71+"");
                map.put("pid",pid);
                myPresenter.getData(Contracts.ADDCAR,AddCarBean.class,map);
                break;
        }
    }



    //IVIew
    @Override
    public void success(Object success) {
        AddCarBean bean = (AddCarBean) success;
        if(bean.getCode().equals("0")){
            Toast.makeText(this, bean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void error(Object error) {

    }
}
