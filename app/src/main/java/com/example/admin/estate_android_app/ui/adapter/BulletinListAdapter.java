package com.example.admin.estate_android_app.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.admin.estate_android_app.R;
import com.example.admin.estate_android_app.model.BulletinBoard;
import com.example.admin.estate_android_app.ui.activity.BulletinDetailsActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by think on 2015/11/26.
 * 公告栏
 */
public class BulletinListAdapter extends RecyclerView.Adapter<BulletinListAdapter.ViewHolder> {
    Context mContext;
    List<BulletinBoard> bulletinBoardList = new ArrayList<>();

    public BulletinListAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final BulletinBoard bulletinBoard = bulletinBoardList.get(position);
        holder.itemNumTitle.setText(mContext.getString(R.string.num_text));
        holder.itemDescTitle.setText(mContext.getString(R.string.theme_text));

        holder.itemNum.setText(bulletinBoard.bulletinboardid);
        holder.itemDesc.setText(bulletinBoard.subject);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BulletinDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bulletinBoard", bulletinBoard);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bulletinBoardList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout relativeLayout;
        /**
         * CardView*
         */
        public CardView cardView;
        /**
         * 编号名称*
         */
        public TextView itemNumTitle;
        /**
         * 描述名称*
         */
        public TextView itemDescTitle;
        /**
         * 编号*
         */
        public TextView itemNum;
        /**
         * 描述*
         */
        public TextView itemDesc;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.card_container);

            itemNumTitle = (TextView) view.findViewById(R.id.item_num_title);
            itemDescTitle = (TextView) view.findViewById(R.id.item_desc_title);


            itemNum = (TextView) view.findViewById(R.id.item_num_text);
            itemDesc = (TextView) view.findViewById(R.id.item_desc_text);
        }
    }

    public void update(ArrayList<BulletinBoard> data, boolean merge) {
        if (merge && bulletinBoardList.size() > 0) {
            for (int i = 0; i < bulletinBoardList.size(); i++) {
                BulletinBoard bulletinBoard = bulletinBoardList.get(i);
                boolean exist = false;
                for (int j = 0; j < data.size(); j++) {
                    if (data.get(j) == bulletinBoard) {
                        exist = true;
                        break;
                    }
                }
                if (exist) continue;
                data.add(bulletinBoard);
            }
        }
        bulletinBoardList = data;
        notifyDataSetChanged();
    }

    //
    public void adddate(ArrayList<BulletinBoard> data) {
        if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (!bulletinBoardList.contains(data.get(i))) {
                    bulletinBoardList.add(data.get(i));
                }
            }
        }
        notifyDataSetChanged();
    }


    public void removeAllData() {
        if (bulletinBoardList.size() > 0) {
            bulletinBoardList.removeAll(bulletinBoardList);
        }
    }
}
