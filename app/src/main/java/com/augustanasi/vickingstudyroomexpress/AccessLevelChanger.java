package com.augustanasi.vickingstudyroomexpress;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by abby on 1/27/2017.
 * This class changes a person authoization levle
 */

public class AccessLevelChanger extends Activity{
    private EditText userNameTxt;
    private EditText newLevelTxt;
    private Button changeBtn;
    private FirebaseAuth fAuth;
    private DatabaseReference ref;
    private String username;
    private String stringLevel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updata_user_auth);

        userNameTxt = (EditText)findViewById(R.id.userNameTXT);
        newLevelTxt = (EditText)findViewById(R.id.rankTXT);
        changeBtn = (Button)findViewById(R.id.levelChangeBtn);

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = userNameTxt.getText().toString().trim();
                stringLevel = newLevelTxt.getText().toString().trim();

                //checks that user inputed values
                if(username.isEmpty()||stringLevel.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AccessLevelChanger.this);
                    builder.setMessage("Please make sure you enter a username and access level")
                            .setTitle("Error!")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                //checks that user sent in valid Auth level
                else if(!stringLevel.equals("1")&&!stringLevel.equals("2")&&!stringLevel.equals("3")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(AccessLevelChanger.this);
                    builder.setMessage("Please make sure you enter a valid access level")
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
                            if(dataSnapshot.child("Users").hasChild(username)){
                                //sets new auth level
                                ref.child("Users").child(username).child("Access").setValue(stringLevel);
                                AlertDialog.Builder builder = new AlertDialog.Builder(AccessLevelChanger.this);
                                builder.setMessage("Success!")
                                        .setPositiveButton(android.R.string.ok, null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }
                            else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(AccessLevelChanger.this);
                                builder.setMessage("Invalid User! Please make sure to enter an existing user!")
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
