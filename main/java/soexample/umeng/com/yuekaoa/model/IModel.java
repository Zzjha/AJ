package soexample.umeng.com.yuekaoa.model;

import java.util.Map;

import soexample.umeng.com.yuekaoa.callback.MyCallBack;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public interface IModel{
    void getData(String url, Class cla, Map<String,String> map, MyCallBack myCallBack);
}
