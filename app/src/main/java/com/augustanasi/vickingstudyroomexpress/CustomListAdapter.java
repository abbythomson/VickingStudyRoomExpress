package com.augustanasi.vickingstudyroomexpress;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by viola on 1/27/2017.
 */

public class CustomListAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private int id;
    private List<String> items;

    public CustomListAdapter(Context context, int textViewResourcedId, List<String> list){
        super(context,textViewResourcedId,list);
        mContext = context;
        id = textViewResourcedId;
        items = list;
    }
    public View getView(int position, View v, ViewGroup parent){
        View mView = v;

        if(v == null){
            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(R.layout.list_item, null);

        }
        TextView text = (TextView)v.findViewById(R.id.room_des);


        if(items.get(position)!=null){
            Log.d("TEXT", items.get(position));
            text.setText(items.get(position));
            if(items.get(position).toString().contains("NOT")){
                text.setTextColor(Color.RED);
            }

        }
        return mView;
    }
}
