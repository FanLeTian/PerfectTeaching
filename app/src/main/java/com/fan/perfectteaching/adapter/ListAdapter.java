package com.fan.perfectteaching.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fan.perfectteaching.R;
import com.fan.perfectteaching.beans.HomeBean;
import com.fan.perfectteaching.widegts.OnItemClickListener;

/**
 * Created by acer on 2017/5/2.
 */

public class ListAdapter extends BaseRecyclerAdapter<HomeBean.DataBean> {

    private OnItemClickListener<HomeBean.DataBean> onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener<HomeBean.DataBean> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public ListAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder) holder).itemContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position, getItem(position), v);
            }
        });
        HomeBean.DataBean objbean = getItem(position);
        ((ViewHolder) holder).labname.setText(objbean.getName());
        ((ViewHolder) holder).labContent.setText(objbean.getContent());
        ((ViewHolder) holder).labtype.setText(objbean.getType());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemContent;
        ImageView imageView;
        TextView labname;
        TextView labContent;
        TextView labtype;


        private ViewHolder(View itemView) {
            super(itemView);
            itemContent = itemView.findViewById(R.id.itemContent);
            labname = itemView.findViewById(R.id.kc_title);
            labContent = itemView.findViewById(R.id.kc_content);
            labtype = itemView.findViewById(R.id.type);
        }
    }
}
