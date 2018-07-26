package com.kx.studyview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.kx.studyview.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewGroupListActivity extends AppCompatActivity {

    @BindView(R.id.layoutContainer)
    TextView layoutContainer;
    @BindView(R.id.myLinearLayout)
    TextView myLinearLayout;
    @BindView(R.id.flowLayout)
    TextView flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group_list);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.layoutContainer, R.id.myLinearLayout,R.id.flowLayout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layoutContainer:
                startActivity(LayoutContainerActivity.class);
                break;
            case R.id.myLinearLayout:
                startActivity(MyLinearLayoutActivity.class);
                break;
                case R.id.flowLayout:
                startActivity(FlowLayoutActivity.class);
                break;
        }
    }

    public void startActivity(Class activityClass) {
        Intent intent = new Intent(ViewGroupListActivity.this, activityClass);
        startActivity(intent);
    }
}
