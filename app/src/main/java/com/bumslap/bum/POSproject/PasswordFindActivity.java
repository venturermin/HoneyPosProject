package com.bumslap.bum.POSproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bumslap.bum.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordFindActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText emailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_find);
        mAuth = FirebaseAuth.getInstance();
        emailAddress = findViewById(R.id.email_input);

    }






    public void onClickedSendResetEmail(View v){

        String email = emailAddress.getText().toString();
        if(!email.equals("")) {

            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(PasswordFindActivity.this, "이메일 전송 완료", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(PasswordFindActivity.this, "이메일 전송 실패 다시한번 확인해주세요", Toast.LENGTH_LONG).show();
                            }

                        }
                    });
        }
        else {  Toast.makeText(PasswordFindActivity.this, "이메일 주소를 확인해주세요", Toast.LENGTH_LONG).show();

        }

    }

}

