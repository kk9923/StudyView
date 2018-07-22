package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.kx.studyview.Api;
import com.kx.studyview.R;
import com.kx.studyview.adapter.BusLineAdapter;
import com.kx.studyview.bean.BusLineInfo;
import com.kx.studyview.utils.LogUtils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        ButterKnife.bind(this);
        getBusLineInfo();
       // BusLineView busLineView = findViewById(R.id.busLineView);
       // busLineView.setText("高新大道高科园二路");
        busRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mBusLineAdapter = new BusLineAdapter(this);
        busRecyclerView.setAdapter(mBusLineAdapter);

    }

    private void getBusLineInfo() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
//                        if(BuildConfig.DEBUG){
//                            //显示日志
//                            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                        }else {
//                            logInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
//                        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(8, TimeUnit.SECONDS)
                .addInterceptor(logInterceptor)
                .build();
        Retrofit retrofit1 = new Retrofit.Builder()
                //使用自定义的mGsonConverterFactory
                .baseUrl("http://androidbus.wuhancloud.cn:9087/")
                .client(okHttpClient)
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
