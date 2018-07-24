package com.kx.studyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kx.studyview.activity.AnimationButtonActivity;
import com.kx.studyview.activity.BusActivity;
import com.kx.studyview.activity.ColorfulTextViewActivity;
import com.kx.studyview.activity.LayoutContainerActivity;
import com.kx.studyview.activity.PaintViewActivity;
import com.kx.studyview.activity.PayInPutViewActivity;
import com.kx.studyview.activity.PrinterTextViewActivity;
import com.kx.studyview.activity.RegionActivity;
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
        findViewById(R.id.region).setOnClickListener(mClickListener);
        findViewById(R.id.paintView).setOnClickListener(mClickListener);

    }
    private View.OnClickListener mClickListener = new View.OnClickListener() {
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
                    break;
                case R.id.tv_bus :
                    startActivity(BusActivity.class);
                    break;
                    case R.id.region :
                    startActivity(RegionActivity.class);
                    break;
                    case R.id.paintView :
                    startActivity(PaintViewActivity.class);
                    break;
            }
        }
    };


    public void startActivity(Class activityClass){
        Intent intent = new Intent(MainActivity.this,activityClass);
        startActivity(intent);
    }
}
