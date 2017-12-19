package com.bumslap.bum.settings;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumslap.bum.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserSettingActivity extends AppCompatActivity {
    Typeface mTypeface;
    FirebaseAuth mAuth;
    TextView user_name;
    FirebaseAuth.AuthStateListener listener;
    TextView uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_setting);
        setTitle("설정");


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        mAuth = FirebaseAuth.getInstance();


        user_name = findViewById(R.id.text_username);
        uid = findViewById(R.id.textView12);
        String name = user.getDisplayName();
        String user_id = user.getUid();
        user_name.setText(name);
        uid.setText(user_id);



        /*listener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    user_name = findViewById(R.id.text_username);
                    uid = findViewById(R.id.textView12);
                     String name = user.getDisplayName();
                    String user_id = user.getUid();
                     user_name.setText(name);
                    uid.setText(user_id);
                    Log.d("state", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("state", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };*/








    }




}
