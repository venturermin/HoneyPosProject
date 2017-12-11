package com.bumslap.bum.POSproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumslap.bum.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class EmailVerifyActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    ImageView checkEmail;

    Button finishBtn;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verify);
    }

    protected void onResume(){

        super.onResume();


        mAuth = FirebaseAuth.getInstance();
        checkEmail = findViewById(R.id.checkEmail);
        finishBtn = findViewById(R.id.finishBtn);
        user =  mAuth.getCurrentUser();
    }

    protected void verifyEmail() {

        user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                // Re-enable button


                if (task.isSuccessful()) {

                    Toast.makeText(EmailVerifyActivity.this,
                            "Verification email sent to " + user.getEmail(),
                            Toast.LENGTH_SHORT).show();
                    checkEmail.setVisibility(View.VISIBLE);
                    finishBtn.setEnabled(true);
                } else {
                    Log.e("state", "sendEmailVerification", task.getException());
                    Toast.makeText(EmailVerifyActivity.this,
                            "Failed to send verification email.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClickedEmail(View v) {
        new Thread(new Runnable() {
            public void run() {

                verifyEmail();
            }
        }).start();



    }



    public void onClickedFinish(View v) {

        user.reload();
        if (user.isEmailVerified()) {
            finish();
            Intent intent = new Intent(EmailVerifyActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(EmailVerifyActivity.this, "이메일 인증을 완료해 주세요!"+user.getEmail(), Toast.LENGTH_LONG).show();

        }
    }
}//end of class