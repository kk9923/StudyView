package com.kx.studyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kx.studyview.activity.AnimationButtonActivity;
import com.kx.studyview.activity.BusActivity;
import com.kx.studyview.activity.ColorfulTextViewActivity;
import com.kx.studyview.activity.LayoutContainerActivity;
import com.kx.studyview.activity.PayInPutViewActivity;
import com.kx.studyview.activity.PrinterTextViewActivity;
import com.kx.studyview.activity.RenderScriptActivity;
import com.kx.studyview.activity.SuperButtonActivity;
import com.kx.studyview.activity.TabLayoutActivity;
import com.kx.studyview.activity.WaveViewActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.colorfulTextView).setOnClickListener(mClickListener);
        findViewById(R.id.waveView).setOnClickListener(mClickListener);
        findViewById(R.id.superButton).setOnClickListener(mClickListener);
        findViewById(R.id.tabLayout).setOnClickListener(mClickListener);
        findViewById(R.id.printerTextView).setOnClickListener(mClickListener);
        findViewById(R.id.payInPutView).setOnClickListener(mClickListener);
        findViewById(R.id.animationButton).setOnClickListener(mClickListener);
        findViewById(R.id.renderScript).setOnClickListener(mClickListener);
        findViewById(R.id.layoutContainer).setOnClickListener(mClickListener);
        findViewById(R.id.tv_bus).setOnClickListener(mClickListener);

    }
    private View.OnClickListener mClickListener = new View.OnClickListener() {

        private Api mMApi;

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.colorfulTextView:
                     startActivity(ColorfulTextViewActivity.class);
                break;
                case R.id.waveView:
                     startActivity(WaveViewActivity.class);
                break;
                case R.id.superButton:
                     startActivity(SuperButtonActivity.class);
                break;
                case R.id.tabLayout:
                    startActivity(TabLayoutActivity.class);
                    break;
                    case R.id.printerTextView:
                    startActivity(PrinterTextViewActivity.class);
                    break;
                    case R.id.payInPutView:
                    startActivity(PayInPutViewActivity.class);
                    break;
                    case R.id.animationButton:
                    startActivity(AnimationButtonActivity.class);
                    break;
                    case R.id.renderScript:
                   startActivity(RenderScriptActivity.class);
                    break;
                    case R.id.layoutContainer:
                    startActivity(LayoutContainerActivity.class);
//                        Retrofit retrofit2 = new Retrofit.Builder()
//                                //使用自定义的mGsonConverterFactory
//                                .baseUrl("http://androidbus.wuhancloud.cn:9087/")
//                               .addConverterFactory(GsonConverterFactory.create())
//                               // .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                                .build();
//                        mMApi = retrofit2.create(Api.class);
//                        mMApi.getBus().enqueue(new Callback<BusLineInfo>() {
//                            @Override
//                            public void onResponse(Call<BusLineInfo> call, Response<BusLineInfo> response) {
//                                boolean successful = response.isSuccessful();
//                                LogUtils.e("successful =   "   +successful);
//                              //  LogUtils.e(busInfo.toString());
//                                BusLineInfo body = response.body();
//                                List<BusLineInfo.DataBean.SBean> s = body.getData().getS();
//                                for (BusLineInfo.DataBean.SBean businfo : s) {
//                                  //  System.out.println(businfo.getN());
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<BusLineInfo> call, Throwable e) {
//                             //   LogUtils.e(e.toString());
//                            }
//                        });

                    break;
                case R.id.tv_bus :
                    startActivity(BusActivity.class);
                    break;
            }
        }
    };


    public void startActivity(Class activityClass){
        Intent intent = new Intent(MainActivity.this,activityClass);
        startActivity(intent);
    }
}
