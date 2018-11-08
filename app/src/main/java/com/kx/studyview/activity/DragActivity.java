package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kx.studyview.R;
import com.kx.studyview.adapter.ImageListDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DragActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_gallery)
    TextView tvGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
        ButterKnife.bind(this);

        final String[] descList = new String[]{
                getString(R.string.string_mitty_motto1),
                getString(R.string.string_mitty_motto2),
                getString(R.string.string_mitty_motto3),
                getString(R.string.string_mitty_motto4),
                getString(R.string.string_mitty_motto5),
                getString(R.string.TomHardy),
                getString(R.string.ChristianBale),
                getString(R.string.MarkWahlberg),
                getString(R.string.WillSmith),
                getString(R.string.DenzelWashington),
        };

        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.mipmap.pic1);
        imgList.add(R.mipmap.pic2);
        imgList.add(R.mipmap.pic3);
        imgList.add(R.mipmap.pic4);
        imgList.add(R.mipmap.pic5);
        imgList.add(R.mipmap.img1);
        imgList.add(R.mipmap.img2);
        imgList.add(R.mipmap.img3);
        imgList.add(R.mipmap.img4);
        imgList.add(R.mipmap.img5);
        ImageListDetailAdapter adapter = new ImageListDetailAdapter(this,imgList);
        viewPager.setAdapter(adapter);
        tvGallery.setText(descList[0]);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                tvGallery.setText(descList[position]);
            }
        });
        adapter.setOnPhotoClickListener(new ImageListDetailAdapter.OnPhotoClickListener() {
            @Override
            public void onPhotoClick() {
                System.out.println("onPhotoClick");
            }
        });
        tvGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("onClick");
            }
        });
    }
}
