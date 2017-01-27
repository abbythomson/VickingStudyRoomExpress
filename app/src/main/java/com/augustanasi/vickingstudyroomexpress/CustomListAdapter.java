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
 * UNUSSED
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
        String text = items.get(position);
        if(text!=null){
            TextView slot = (TextView) mView.findViewById(R.id.room_des);
            if(slot!=null){
                slot.setText(text);
                if(text.contains("NOT")){
                    slot.setTextColor(Color.RED);
                }else{
                    slot.setTextColor(Color.GREEN);
                }

            }
        }
        return mView;
    }
}
