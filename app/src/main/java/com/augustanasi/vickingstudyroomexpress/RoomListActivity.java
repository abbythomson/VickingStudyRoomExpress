package com.augustanasi.vickingstudyroomexpress;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

        roomIds.addAll(Arrays.asList("PDK","Room 232","Room 233","Room 310","Room 311","Room 312","Room 313","Room 314","Room 322","Room 323","Room 324"));

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mUserId = mFirebaseUser.getUid();

        final ListView listView = (ListView) findViewById(R.id.roomList);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1){
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position,convertView,parent);
                final TextView text = (TextView) view.findViewById(android.R.id.text1);


                mDatabase.child("Rooms").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
                mDatabase.child("Rooms").child(roomIds.get(position)).child("availible").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean isAvailible = dataSnapshot.getValue().equals("true");
                        if(isAvailible){
                            text.setTextColor(Color.GREEN);
                        }
                        else{
                            text.setTextColor(Color.RED);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                return view;
            }
        };
        listView.setAdapter(adapter);

       mDatabase.child("Rooms").addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               adapter.add((String)dataSnapshot.child("description").getValue());
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    adapter.add((String)dataSnapshot.child("description").getValue());
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView< ?> parent, View view, int position, long id) {
                mDatabase.child("Rooms").child(roomIds.get(position))
                        .equalTo((String) listView.getItemAtPosition(position))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChildren()) {
                                    DataSnapshot firstChild = dataSnapshot.getChildren().iterator().next();
                                    boolean current = (boolean) firstChild.child("availible").getValue();
                                    firstChild.getRef().child("availible").setValue(!current);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });

        //setRoomList();
        Room pdk = new Room(240,4,false,true,"PDK");
        //Log.d("TEST", pdk.toString());
        //Log.d("First",roomList.get(0).toString());
        //Log.d("CHECK INITIAL",mDatabase.child("Rooms").child(roomIds.get(0)).child("availible").getKey());
        mDatabase.child("Rooms").child(roomIds.get(0)).child("availible").setValue(false);
        //Log.d("THEN INITIAL",mDatabase.child("Rooms").child(roomIds.get(0)).child("availible").getKey());
        //Log.d("First",roomList.get(0).toString());




    }
    private void setRoomList(){

        Room room232 = new Room(232,4,true,true,"Room232");
        Room room233 = new Room(233,4,true,true,"Room233");
        Room pdk = new Room(240,4,false,true,"PDK");
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
