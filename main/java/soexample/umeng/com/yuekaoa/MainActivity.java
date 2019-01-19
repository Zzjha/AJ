package soexample.umeng.com.yuekaoa;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import soexample.umeng.com.yuekaoa.fragment.HomePageFragment;
import soexample.umeng.com.yuekaoa.fragment.LookFragment;
import soexample.umeng.com.yuekaoa.fragment.MyFragment;
import soexample.umeng.com.yuekaoa.fragment.ShopCarFragment;
import soexample.umeng.com.yuekaoa.fragment.SortFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.homepage)
    RadioButton homepage;
    @BindView(R.id.sort)
    RadioButton sort;
    @BindView(R.id.look)
    RadioButton look;
    @BindView(R.id.shopcar)
    RadioButton shopcar;
    @BindView(R.id.my)
    RadioButton my;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.frame)
    FrameLayout frame;

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame,new SortFragment()).commitAllowingStateLoss();
    }

    @OnClick({R.id.homepage, R.id.sort, R.id.look, R.id.shopcar, R.id.my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homepage:
                manager.beginTransaction().replace(R.id.frame,new HomePageFragment()).commitAllowingStateLoss();
                break;
            case R.id.sort:
                manager.beginTransaction().replace(R.id.frame,new SortFragment()).commitAllowingStateLoss();
                break;
            case R.id.look:
                manager.beginTransaction().replace(R.id.frame,new LookFragment()).commitAllowingStateLoss();
                break;
            case R.id.shopcar:
                manager.beginTransaction().replace(R.id.frame,new ShopCarFragment()).commitAllowingStateLoss();
                break;
            case R.id.my:
                manager.beginTransaction().replace(R.id.frame,new MyFragment()).commitAllowingStateLoss();
                break;
        }
    }
}
