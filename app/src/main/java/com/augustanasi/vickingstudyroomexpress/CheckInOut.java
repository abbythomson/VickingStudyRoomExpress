package com.augustanasi.vickingstudyroomexpress;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viola on 1/25/2017.
 */

public class CheckInOut extends AppCompatActivity {
    private List<String> roomIds = new ArrayList<String>();
    private List<Room> roomList = new ArrayList<>();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;


}
