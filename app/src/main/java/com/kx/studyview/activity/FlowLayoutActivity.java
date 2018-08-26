package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.kx.studyview.R;
import com.kx.studyview.adapter.FlowAdapter;
import com.kx.studyview.utils.ToastUtils;
import com.kx.studyview.views.AdapterFlowLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlowLayoutActivity extends AppCompatActivity {

    @BindView(R.id.adapterFlowLayout)
    AdapterFlowLayout adapterFlowLayout;
    private String [] texts = {"Welcome","IT工程师","我真是可以的","你觉得呢","不要只知道挣钱","努力ing","I thick i can"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        ButterKnife.bind(this);
        adapterFlowLayout.setFlowAdapter(new FlowAdapter() {
            @Override
            public int getChildCount() {
                return texts.length;
            }

            @Override
            public View getView(int position) {
                TextView textView =  LayoutInflater.from(FlowLayoutActivity.this).inflate(R.layout.item_flowlayout_textview,null,false).findViewById(R.id.textView);
                textView.setText(texts[position]);
                removeChildParent(textView);
                return textView;
            }
        });
        adapterFlowLayout.setOnItemClickListener((position, view) ->
                ToastUtils.showToast(texts[position]));
    }

    public void removeChildParent(View view){
        if (view !=null){
            ViewParent parent = view.getParent();
            if(parent instanceof ViewGroup){
                ViewGroup group=(ViewGroup) parent;
                group.removeView(view);
            }
        }
    }
}
