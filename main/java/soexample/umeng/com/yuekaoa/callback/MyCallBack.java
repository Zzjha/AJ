package soexample.umeng.com.yuekaoa.callback;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public interface MyCallBack<T> {
    void setSuccess(T successData);
    void setError(T errorData);
}
