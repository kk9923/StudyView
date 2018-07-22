package com.kx.studyview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kx.studyview.R;
import com.kx.studyview.bean.BusLineInfo;
import com.kx.studyview.views.BusLineView;

import java.util.ArrayList;

/**
 * Created by admin on 2018/7/22.
 */
public class BusLineAdapter extends RecyclerView.Adapter<BusLineAdapter.BusLineHolder>{
    private ArrayList<BusLineInfo.DataBean.SBean> mBeans  = new ArrayList<>();

    private LayoutInflater mLayoutInflater ;
    private int selectStation   = 1;

    public BusLineAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BusLineHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BusLineHolder  busLineHolder = new BusLineHolder(mLayoutInflater.inflate(R.layout.item_bus_line,parent,false));
        return busLineHolder;
    }

    @Override
    public void onBindViewHolder(BusLineHolder holder, final int position) {
        BusLineInfo.DataBean.SBean sBean = mBeans.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position!= selectStation){
                    selectStation = position ;
                    notifyDataSetChanged();
                }
            }
        });
        System.out.println("position =  "  +  position  );
        if (position <  selectStation){
            holder.busLineView.setText(sBean.getN()).setSelectStation(false).setAfterSelectStation(false).reLoad();
        }else if (selectStation == position){
            holder.busLineView.setText(sBean.getN()).setSelectStation(true).setAfterSelectStation(false).reLoad();
        }else {
            holder.busLineView.setText(sBean.getN()).setSelectStation(false).setAfterSelectStation(true).reLoad();
        }
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public static class BusLineHolder extends RecyclerView.ViewHolder{
    private BusLineView busLineView ;
        public BusLineHolder(View itemView) {
            super(itemView);
            busLineView = itemView.findViewById(R.id.busLineView);
        }
    }

    public void setBusLineDatas(ArrayList<BusLineInfo.DataBean.SBean> beans) {
        if (beans==null || beans.size() == 0){
            return;
        }
        mBeans = beans;
        notifyDataSetChanged();
    }
}
