package com.augustanasi.vickingstudyroomexpress;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by viola on 1/22/2017.
 */

public class RoomListActivity extends Activity {
    private List<String> roomIds = new ArrayList<String>();
    private List<Room> roomList = new ArrayList<>();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);

        roomIds.addAll(Arrays.asList("PDK","Room232","Room233","Room310","Room311","Room312","Room313","Room314","Room322","Room323","Room  324"));

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mUserId = mFirebaseUser.getUid();

        final ListView listView = (ListView) findViewById(R.id.roomList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.activity_list_item,android.R.id.text1);
        //CustomListAdapter adapter = new CustomListAdapter(this, android.R.layout.activity_list_item,);
        listView.setAdapter(adapter);

        mDatabase.child("Rooms").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Room temp = dataSnapshot.getValue(Room.class);
                String text = temp.toString();

                adapter.add(temp.toString());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Room temp =  dataSnapshot.getValue(Room.class);
                adapter.add(temp.toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /**
     * Used to initail set values in cloud database
     */
    private void setRoomList(){

        Room room232 = new Room(232,4,true,true,"Room232");
        Room room233 = new Room(233,4,true,true,"Room233");
        Room pdk = new Room(240,4,false,true,"PBK");
        Room room310 = new Room(310,1,false,true,"Room310");
        Room room311 = new Room(311,1,false,true,"Room311");
        Room room312 = new Room(312,1,false,true,"Room312");
        Room room313 = new Room(313,1,false,true,"Room313");
        Room room314 = new Room(314,1,false,true,"Room314");
        Room room322 = new Room(322,1,false,true,"Room322");
        Room room323 = new Room(323,1,false,true,"Room323");
        Room room324 = new Room(324,1,false,true,"Room324");

        roomList = new ArrayList<Room>();
        roomList.add(room232);
        roomList.add(room233);
        roomList.add(pdk);
        roomList.add(room310);
        roomList.add(room311);
        roomList.add(room312);
        roomList.add(room313);
        roomList.add(room314);
        roomList.add(room322);
        roomList.add(room323);
        roomList.add(room324);

        for(int i=0; i<roomList.size();i++){
            Room newRoom = roomList.get(i);
            mDatabase.child("Rooms").child(newRoom.getRoomId()).setValue(newRoom);
        }

}
}
