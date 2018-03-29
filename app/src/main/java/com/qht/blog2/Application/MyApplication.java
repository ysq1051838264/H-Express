package com.qht.blog2.Application;

import com.baidu.mapapi.SDKInitializer;
import com.qht.blog2.Net.OK_LoggingInterceptor;
import com.qht.blog2.Util.SharePreferenceUtil;
import com.umeng.commonsdk.UMConfigure;
import com.zhy.http.okhttp.OkHttpUtils;

import org.litepal.LitePalApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by QHT on 2017-02-27.
 */

public class MyApplication  extends LitePalApplication {

    protected static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance =this;
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());//初始化百度地图
        SharePreferenceUtil.initSharePreferenceUtil(getApplicationContext());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()//init OkHttp3.0
                .connectTimeout(8000L, TimeUnit.MILLISECONDS)
                .readTimeout(8000L, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new OK_LoggingInterceptor())
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "5aba0372b27b0a063e0000f6");

    }

    public static MyApplication getInstance() {
        return instance;
    }
}
