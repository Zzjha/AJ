package soexample.umeng.com.yuekaoa.model;

import android.util.Log;

import com.google.gson.Gson;

import java.util.Map;

import soexample.umeng.com.yuekaoa.callback.MyCallBack;
import soexample.umeng.com.yuekaoa.utils.RetrofitUtils;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public class MyModel implements IModel {
    @Override
    public void getData(String url, final Class cla, Map<String, String> map, final MyCallBack myCallBack) {
        RetrofitUtils.getInstance().get(url, map, new RetrofitUtils.CallBacks() {
            @Override
            public void onSuccess(String jsonStr) {
                Gson gson = new Gson();
                Object o = gson.fromJson(jsonStr, cla);
                myCallBack.setSuccess(o);
                Log.e("msuc",o.toString());
            }

            @Override
            public void onError(String error) {
                Log.e("merr",error);
            }
        });
    }
}
