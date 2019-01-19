package soexample.umeng.com.yuekaoa.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author:author${朱佳华}
 * data:2019/1/16
 */
public class RetrofitUtils {
    private MyApiService myApiService;
    private OkHttpClient okHttpClient;

        public RetrofitUtils() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Contracts.BASEURL)
                .client(okHttpClient)
                .build();

        myApiService = retrofit.create(MyApiService.class);
    }

    public static RetrofitUtils getInstance() {
        return RestrofitHolder.retrofitUtils;
    }

    private static class RestrofitHolder {
        private static final RetrofitUtils retrofitUtils = new RetrofitUtils();
    }

    //GET方式
    public void get(String url, Map<String, String> map, final CallBacks callBacks) {

        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }
            @Override
            public void onError(Throwable e) {
                if (callBacks != null) {
                    callBacks.onError(e.getMessage());
                }
            }
            @Override
            public void onNext(ResponseBody responseBody) {
                if (callBacks != null) {
                    try {
                        callBacks.onSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        myApiService.get(url, map)
                .subscribeOn(Schedulers.io())   //io子线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //接口回调
    public interface CallBacks {
        void onSuccess(String jsonStr);
        void onError(String error);
    }
}
