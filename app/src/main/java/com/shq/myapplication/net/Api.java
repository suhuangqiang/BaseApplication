package com.shq.myapplication.net;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.shq.myapplication.App;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/8/4.
 */

public class Api {
    protected Retrofit mRetrofit;
    public ApiService service;
    //http://192.168.1.25:8082/
    //http://hs.hsshy.cn:8082/
    //http://192.168.1.35:8082/
    //http://192.168.1.5:8082/
    public static final String BASE_URL = "http://www.ydk-tech.com:8082/";
    private static final int DEFAULT_TIME = 10;//默认超时时间
    private final long RETRY_TIMES = 1;//重订阅次数
    Interceptor mInterceptor = (chain) -> chain.proceed(chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .build());
    public Api(){
        //创建okHttpClient
        if (null == mRetrofit){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            File cacheFile = new File(App.getAppContext().getCacheDir(),"cache");
            Cache cache = new Cache(cacheFile,1024 * 1024 * 100);//100Mb

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .readTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                    .connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS)
                    .addInterceptor(mInterceptor)
                    .addInterceptor(interceptor)
                    .addInterceptor(new HttpCacheInterceptor())
                    .cache(cache)
                    .build();
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
            mRetrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
            service = mRetrofit.create(ApiService.class);
        }
    }
    private static class SingletonHolder{
        private static final Api INSTANCE = new Api();
    }
    public static Api getInstance(){
        return SingletonHolder.INSTANCE;
    }
    class HttpCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtil.isNetConnected(App.getAppContext())){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Toast.makeText(App.getAppContext(),"请检查您的网络", Toast.LENGTH_SHORT).show();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetConnected(App.getAppContext())){
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control",cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
                        .removeHeader("Pragma")
                        .build();
            }
        }
    }
}
