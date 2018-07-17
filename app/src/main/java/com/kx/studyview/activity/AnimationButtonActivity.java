package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kx.studyview.R;
import com.kx.studyview.views.AnimationButton;

public class AnimationButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_button);
        final AnimationButton  animationButton = findViewById(R.id.animationButton);
        animationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationButton.startAnimation();
            }
        });
    }
}
