package com.cdc.cardview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by cdc on 2017/2/6.
 */

public class MainRecyclerViewAdapter extends  RecyclerView.Adapter<MainRecyclerViewAdapter.NormalTextViewHolder>{
    private static List<Map<String, Object>> lists;
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    public MainRecyclerViewAdapter(List<Map<String, Object>> lists, Context context){
        this.lists=lists;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);


    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }


    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
            Map<String, Object> maps= lists.get(position);
            for (Map.Entry entry : maps.entrySet()) {
                Object key = entry.getKey( );
                if(key.toString().equals("title")){
                    String  str= (String) entry.getValue();
                    holder.mTextView.setText(str);
                }
            }
    }


    @Override
    public int getItemCount() {
        return lists.size();
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            mTextView= (TextView) itemView.findViewById(R.id.text_view);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.i("1234", "onClick--> position = " + getPosition());
                    Map<String, Object> map =lists
                            .get(getPosition());
                    Intent intent = (Intent) map.get("intent");
                    mTextView.getContext().startActivity(intent);
                }
            });
        }


    }
}
