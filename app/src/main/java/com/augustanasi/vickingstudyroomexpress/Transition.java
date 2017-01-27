package com.augustanasi.vickingstudyroomexpress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by viola on 1/25/2017.
 */

public class Transition extends AppCompatActivity {
    private Button roomListBtn;
    private Button checkInOutBtn;
    private Button updataUseresBtn;
    private FirebaseDatabase database;
    private DatabaseReference roomRef;
    private DatabaseReference userRef;
    private FirebaseAuth mAuth;
    private String curUser;
    private String userLevel;
    private Button logOutBtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transition_screen);

        database=FirebaseDatabase.getInstance();

        /*roomRef = database.getReference("Rooms");

        Log.d("TRANSITION", "In Class");
        roomRef.addValueEventListener(new ValueEventListener(){

            public void onDataChange(DataSnapshot snapshot){
                GenericTypeIndicator<List<Room>> genericTypeIndicator = new GenericTypeIndicator<List<Room>>(){};
                List<Room> roomList = (List<Room>) snapshot.getValue(genericTypeIndicator);
                for(int i = 0;i<roomList.size();i++){
                    Log.d("ROOM "+i,roomList.get(i).toString());
                }
            }

            public void onCancelled(DatabaseError error){

            }
        });*/
        checkInOutBtn = (Button)findViewById(R.id.toCheckInOut);
        updataUseresBtn = (Button) findViewById(R.id.toAdmin);
        roomListBtn = (Button)findViewById(R.id.roomListBtn);
        logOutBtn = (Button)findViewById(R.id.logOutBtn);

        mAuth = FirebaseAuth.getInstance();
        curUser = mAuth.getCurrentUser().getEmail().split("@")[0];
        userRef = database.getReference();
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("LEVEL","---"+dataSnapshot.child("Users").child(curUser).child("Access").getValue());
                userLevel = (String) dataSnapshot.child("Users").child(curUser).child("Access").getValue();

                Log.d("USER LEVEL", "LEVEL "+userLevel);
                if(userLevel.equals("2")||userLevel.equals("1")){
                    checkInOutBtn.setClickable(true);
                    checkInOutBtn.setVisibility(View.VISIBLE);
                    if(userLevel.equals("1")){
                        updataUseresBtn.setClickable(true);
                        updataUseresBtn.setVisibility(View.VISIBLE);

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        roomListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transition.this,RoomListActivity.class);
                startActivity(intent);
            }
        });

        checkInOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transition.this, tempDest.class);
                startActivity(intent);
            }
        });

        updataUseresBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Transition.this, AccessLevelChanger.class);
                startActivity(intent);
            }
        });

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(Transition.this, LogInActivity.class);
                startActivity(intent);

            }
        });


    }

}
