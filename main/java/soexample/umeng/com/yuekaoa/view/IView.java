package soexample.umeng.com.yuekaoa.view;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public interface IView<T> {
    void success(T success);
    void error(T error);
}
