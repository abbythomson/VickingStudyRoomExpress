package com.augustanasi.vickingstudyroomexpress;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by viola on 1/27/2017.
 */

public class ChangeRoomState extends Activity {
    private Button checkInOutBtn;
    private EditText roomIdTxt;
    private DatabaseReference ref;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_room_availible);

        checkInOutBtn = (Button)findViewById(R.id.changeStateBtn);
        roomIdTxt = (EditText)findViewById(R.id.roomIdTxTFld);

        checkInOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String roomId = roomIdTxt.getText().toString().trim();
                if(roomId.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangeRoomState.this);
                    builder.setMessage("Please make sure to enter a roomId")
                            .setTitle("Error!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    ref = FirebaseDatabase.getInstance().getReference();
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("Rooms").hasChild(roomId)){
                                boolean curState  = (boolean) dataSnapshot.child("Rooms").child(roomId).child("availible").getValue();

                                ref.child("Rooms").child(roomId).child("availible").setValue(!curState);

                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeRoomState.this);
                                builder.setMessage("Success!")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(ChangeRoomState.this);
                                builder.setMessage("Please make sure to enter a valid RoomId")
                                        .setTitle("Error!")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

}
