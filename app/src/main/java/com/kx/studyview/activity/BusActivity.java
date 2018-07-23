package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.TextView;

import com.kx.studyview.Api;
import com.kx.studyview.R;
import com.kx.studyview.adapter.BusLineAdapter;
import com.kx.studyview.bean.BusLineInfo;
import com.kx.studyview.utils.BaseConstants;
import com.kx.studyview.utils.LogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BusActivity extends AppCompatActivity {

    @BindView(R.id.tv_start_station)
    TextView tvStartStation;
    @BindView(R.id.tv_end_station)
    TextView tvEndStation;
    @BindView(R.id.tv_ticket_price)
    TextView tvTicketPrice;
    @BindView(R.id.tv_run_time)
    TextView tvRunTime;
    @BindView(R.id.busRecyclerView)
    RecyclerView busRecyclerView;
    private Api mMApi;
    private BusLineAdapter mBusLineAdapter;
    private static final int TIMEOUT_VALUE = 30*1000;

    //okhttp的缓存大小，现为50M
    public static final long CATCH_SIZE = 50*1024*1024;

    private HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BODY;
    private Retrofit retrofit;
    private Map<Class,Object> apiMap;
    private String cookies;
    private String userAgent;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        ButterKnife.bind(this);
        getBusLineInfo();
        busRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mBusLineAdapter = new BusLineAdapter(this);
        busRecyclerView.setAdapter(mBusLineAdapter);
        userAgent = System.getProperty("http.agent");

    }

    private void getBusLineInfo() {
        OkHttpClient.Builder newBuilder = new OkHttpClient().newBuilder();
        Interceptor requestInterceptor = chain -> {

            Request.Builder builder = chain.request().newBuilder();
            if (!TextUtils.isEmpty(userAgent)) {
               // builder.addHeader("User-Agent", userAgent);
            }
            if (!TextUtils.isEmpty(cookies)) {
              //  builder.addHeader("Cookie", cookies);
            }
            return chain.proceed(builder.build());
        };
      //  newBuilder.addNetworkInterceptor(requestInterceptor);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(logLevel);
        newBuilder.addNetworkInterceptor(loggingInterceptor);
        //设置缓存路径跟大小
        newBuilder.cache(new Cache(new File(BaseConstants.getNetCacheDir()), CATCH_SIZE));
        newBuilder.connectTimeout(TIMEOUT_VALUE, TimeUnit.MILLISECONDS);
        newBuilder.readTimeout(TIMEOUT_VALUE, TimeUnit.MILLISECONDS);
        newBuilder.writeTimeout(TIMEOUT_VALUE, TimeUnit.MILLISECONDS);


        Retrofit retrofit1 = new Retrofit.Builder()
                //使用自定义的mGsonConverterFactory
                .baseUrl("http://androidbus.wuhancloud.cn:9087/")
                .client(newBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        mMApi = retrofit1.create(Api.class);
        mMApi.getBusLineInfo().enqueue(new Callback<BusLineInfo>() {
            @Override
            public void onResponse(Call<BusLineInfo> call, Response<BusLineInfo> response) {
                if (response.isSuccessful()) {
                    BusLineInfo body = response.body();
                    if (body != null) {
                        BusLineInfo.DataBean data = body.getData();
                        if (data != null) {
                            BusLineInfo.DataBean.LBean l = data.getL();
                            String startStation = l.getS();
                            String endStation = l.getE();
                            String t1 = l.getT1();
                            String t2 = l.getT2();
                            String price = l.getP();
                            tvStartStation.setText(startStation);
                            tvEndStation.setText(endStation);
                            tvRunTime.setText(String.format("运行时间 %s-%S", t1, t2));
                            tvTicketPrice.setText(String.format("%s元", price));
                            ArrayList<BusLineInfo.DataBean.SBean> s = data.getS();
                            mBusLineAdapter.setBusLineDatas(s);

                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BusLineInfo> call, Throwable e) {
                LogUtils.e(e.toString());
            }
        });
    }
}
