package soexample.umeng.com.yuekaoa.presenter;

import android.util.Log;

import java.util.Map;

import soexample.umeng.com.yuekaoa.callback.MyCallBack;
import soexample.umeng.com.yuekaoa.model.MyModel;
import soexample.umeng.com.yuekaoa.view.IView;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public class MyPresenter implements IPresenter {
    private MyModel myModel;
    private IView iView;

    public MyPresenter(IView iView) {
        this.iView = iView;
        myModel = new MyModel();
    }

    @Override
    public void getData(String url, Class cla, Map<String, String> map) {
        myModel.getData(url, cla, map, new MyCallBack() {
            @Override
            public void setSuccess(Object successData) {
                iView.success(successData);
                Log.e("psuc",successData.toString());
            }

            @Override
            public void setError(Object errorData) {
                iView.error(errorData);
                Log.e("perr",errorData.toString());
            }
        });
    }
}
