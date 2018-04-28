package com.fan.perfectteaching.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fan.perfectteaching.R;
import com.fan.perfectteaching.beans.OrderBean;
import com.fan.perfectteaching.widegts.recycler.OnItemClickListener;

public class OderAdapter extends BaseRecyclerAdapter<OrderBean.DataBean> {

    private OnItemClickListener<OrderBean.DataBean> onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener<OrderBean.DataBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OderAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oder_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((ViewHolder) holder).itemContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position, getItem(position), v);
            }
        });
        OrderBean.DataBean objbean = getItem(position);
        ((ViewHolder) holder).oderName.setText(objbean.getName());
        ((ViewHolder) holder).oderContent.setText(objbean.getContent());
        ((ViewHolder) holder).orderType.setText(objbean.getType());
        if (objbean.getStatus() == 0) {
            ((ViewHolder) holder).status.setText("待完成");
        } else if (objbean.getStatus() == 1) {
            ((ViewHolder) holder).status.setText("已完成");
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemContent;
        TextView oderName;
        TextView oderContent;
        TextView orderType;
        TextView status;


        private ViewHolder(View itemView) {
            super(itemView);
            itemContent = itemView.findViewById(R.id.oder_itemcontent);
            oderName = itemView.findViewById(R.id.oder_name);
            oderContent =  itemView.findViewById(R.id.order_content);
            orderType =  itemView.findViewById(R.id.order_lab_type);
            status =  itemView.findViewById(R.id.orderstatus);
        }
    }
}


