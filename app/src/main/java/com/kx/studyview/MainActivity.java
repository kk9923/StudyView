package com.kx.studyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kx.studyview.activity.AnimationButtonActivity;
import com.kx.studyview.activity.BusActivity;
import com.kx.studyview.activity.ColorfulTextViewActivity;
import com.kx.studyview.activity.GradientActivity;
import com.kx.studyview.activity.LayoutContainerActivity;
import com.kx.studyview.activity.PaintViewActivity;
import com.kx.studyview.activity.PayInPutViewActivity;
import com.kx.studyview.activity.PrinterTextViewActivity;
import com.kx.studyview.activity.RegionActivity;
import com.kx.studyview.activity.RenderScriptActivity;
import com.kx.studyview.activity.SuperButtonActivity;
import com.kx.studyview.activity.TabLayoutActivity;
import com.kx.studyview.activity.WaveViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.colorfulTextView)
    TextView colorfulTextView;
    @BindView(R.id.waveView)
    TextView waveView;
    @BindView(R.id.superButton)
    TextView superButton;
    @BindView(R.id.tabLayout)
    TextView tabLayout;
    @BindView(R.id.printerTextView)
    TextView printerTextView;
    @BindView(R.id.payInPutView)
    TextView payInPutView;
    @BindView(R.id.animationButton)
    TextView animationButton;
    @BindView(R.id.renderScript)
    TextView renderScript;
    @BindView(R.id.layoutContainer)
    TextView layoutContainer;
    @BindView(R.id.tv_bus)
    TextView tvBus;
    @BindView(R.id.region)
    TextView region;
    @BindView(R.id.paintView)
    TextView paintView;
    @BindView(R.id.gradient)
    TextView gradient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.colorfulTextView, R.id.waveView, R.id.superButton, R.id.tabLayout, R.id.printerTextView,
            R.id.payInPutView, R.id.animationButton, R.id.renderScript, R.id.layoutContainer, R.id.tv_bus, R.id.region,
            R.id.paintView, R.id.gradient})
    public void onClick(View view) {
        switch (view.getId()) {
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
            case R.id.tv_bus:
                startActivity(BusActivity.class);
                break;
            case R.id.region:
                startActivity(RegionActivity.class);
                break;
            case R.id.paintView:
                startActivity(PaintViewActivity.class);
                break;
            case R.id.gradient:
                startActivity(GradientActivity.class);
                break;
        }
    }

    public void startActivity(Class activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }
}
