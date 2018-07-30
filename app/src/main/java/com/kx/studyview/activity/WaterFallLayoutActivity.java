package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.kx.studyview.R;
import com.kx.studyview.utils.ToastUtils;
import com.kx.studyview.views.WaterFallLayout;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WaterFallLayoutActivity extends AppCompatActivity {
    private static int IMG_COUNT = 7;
    @BindView(R.id.add_btn)
    Button addBtn;
    @BindView(R.id.waterfallLayout)
    WaterFallLayout waterfallLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_fall_layout);
        ButterKnife.bind(this);
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView(waterfallLayout);
            }
        });
    }
    public void addView(WaterFallLayout waterfallLayout) {
        Random random = new Random();
        Integer num = Math.abs(random.nextInt());
        WaterFallLayout.LayoutParams layoutParams = new WaterFallLayout.LayoutParams(WaterFallLayout.LayoutParams.WRAP_CONTENT,
                WaterFallLayout.LayoutParams.WRAP_CONTENT);
        ImageView imageView = new ImageView(this);
        if (num % IMG_COUNT == 0) {
            imageView.setImageResource(R.drawable.pic_1);
        } else if (num % IMG_COUNT == 1) {
            imageView.setImageResource(R.drawable.pic_2);
        } else if (num % IMG_COUNT == 2) {
            imageView.setImageResource(R.drawable.pic_3);
        } else if (num % IMG_COUNT == 3) {
            imageView.setImageResource(R.drawable.pic_4);
        } else if (num % IMG_COUNT == 4) {
            imageView.setImageResource(R.drawable.pic_5);
        }else if (num % IMG_COUNT == 5) {
            imageView.setImageResource(R.drawable.pic_6);
        }else if (num % IMG_COUNT == 6) {
            imageView.setImageResource(R.drawable.pic_7);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        waterfallLayout.addView(imageView, layoutParams);

        waterfallLayout.setOnItemClickListener(new WaterFallLayout.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int index) {
                ToastUtils.showToast("item=" + index);
            }
        });
    }
}
