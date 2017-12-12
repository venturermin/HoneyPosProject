package com.bumslap.bum.POSproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumslap.bum.DB.User;
import com.bumslap.bum.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {


    EditText password;
    EditText passwordConfirm;
    TextView passwordMatching;
    ImageView check;
    SignInActivity signInActivity;
    EditText editText_name;
    EditText editText_store_name;
    EditText editText_email;
    EditText editText_password;
    RadioGroup radioGroup_sex;
    EditText editText_phonenumber;
    FirebaseAuth mAuth;
    private DatabaseReference mDatabaseRef;

    RadioGroup radioGroup;

    Spinner yearSpinner;
    Spinner monthSpinner;
    Spinner daySpinner;

    Button nextBtn;
    ImageView checkEmailAddress;
    private Typeface mTypeface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mTypeface = Typeface.createFromAsset(getAssets(), "fonts/NanumSquareRoundL.ttf");
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        setGlobalFont(root);

        mAuth = FirebaseAuth.getInstance();

//set the default according to value





        password = (EditText) findViewById(R.id.password);

        passwordConfirm = (EditText) findViewById(R.id.passwordConfirm);

        check = findViewById(R.id.check);
        check.setVisibility(View.INVISIBLE);


        passwordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String PasswordCheck = password.getText().toString();
                String confirmCheck = passwordConfirm.getText().toString();

                if(PasswordCheck.equals(confirmCheck)){
                    check.setVisibility(View.VISIBLE);
                    check.setImageResource(R.drawable.check_green);
                }
                else {
                    check.setVisibility(View.VISIBLE);
                    check.setImageResource(R.drawable.check_red);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }// end of onCreate



    void setGlobalFont(ViewGroup root) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView)
                ((TextView)child).setTypeface(mTypeface);
            else if (child instanceof ViewGroup)
                setGlobalFont((ViewGroup)child);
        }
    }



    protected void onResume(){

        super.onResume();









        nextBtn = findViewById(R.id.nextBtn);
       // nextBtn.setEnabled(false);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        checkEmailAddress = (ImageView) findViewById(R.id.checkEmail);

        //spinner
        yearSpinner = (Spinner)findViewById(R.id.year);
        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_year, R.layout.simple_spinner_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);

        monthSpinner = (Spinner)findViewById(R.id.month);
        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_month,R.layout.simple_spinner_item);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        daySpinner = (Spinner)findViewById(R.id.day);
        ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this,
                R.array.date_day, R.layout.simple_spinner_item);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daySpinner.setAdapter(dayAdapter);
        //spinner


        editText_name = findViewById(R.id.name);


        editText_store_name = findViewById(R.id.nameOfStore);
        editText_email = findViewById(R.id.email);

        editText_phonenumber = findViewById(R.id.phoneNumber);

        editText_password = findViewById(R.id.password);
        radioGroup = (RadioGroup)findViewById(R.id.radio);





    }

    protected void CreateUser(final String email, final String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"회원가입 완료",Toast.LENGTH_LONG).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"회원가입 실패하였습니다.",Toast.LENGTH_LONG).show();
                            Log.d("state", "createUserWithEmail:failure", task.getException());

                        }

                        // ...
                    }
                });
    }//create Users




    public void onClickedSend(View v){

       /* final String email = editText_email.getText().toString();

*/

        User user = new User();

        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();

        int id = radioGroup.getCheckedRadioButtonId();
        //getCheckedRadioButtonId() 의 리턴값은 선택된 RadioButton 의 id 값.
        RadioButton radioButton = (RadioButton) findViewById(id);


        user.setUser_Name(editText_name.getText().toString());
        user.setUser_StoreName(editText_store_name.getText().toString());
        user.setUser_Email(editText_email.getText().toString());
        user.setUser_Password(password);
        user.setUser_Gender(radioButton.getText().toString());
        user.setUser_PhoneNumber(editText_phonenumber.getText().toString());
        user.setUser_Birthday(yearSpinner.getSelectedItem().toString()+monthSpinner.getSelectedItem().toString()+daySpinner.getSelectedItem().toString());

        Toast.makeText(SignUpActivity.this,editText_name.getText().toString()+editText_store_name.getText().toString()+editText_email.getText().toString()+
                password+radioButton.getText().toString()+editText_phonenumber.getText().toString()+yearSpinner.getSelectedItem().toString()+monthSpinner.getSelectedItem().toString()+daySpinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

        mDatabaseRef.child("signUpInfos").push().setValue(user);

        //클래스로 싸서 처리한다.

        CreateUser(email,password);
        Intent intent = new Intent(SignUpActivity.this, EmailVerifyActivity.class);
        startActivity(intent);





    }
    /*class Gmail {

        public void sendMail(String mailAddress) {
            Properties properties = new Properties();
            properties.put("mail.smtp.user", "bumsgoh@gmail.com"); //구글 계정
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.debug", "true");
            properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.socketFactory.fallback", "false");

            try {
                Authenticator auth = new senderAccount();
                Session session = Session.getInstance(properties, auth);
                session.setDebug(true); // 메일을 전송할 때 상세한 상황을 콘솔에 출력한다.
                MimeMessage msg = new MimeMessage(session);

                msg.setSubject("HoneyPOS 인증메일 입니다");
                Address fromAddr = new InternetAddress("bumsgoh@gmail.com"); // 보내는사람 EMAIL
                msg.setFrom(fromAddr);
                Address toAddr = new InternetAddress(mailAddress);    //받는사람 EMAIL
                msg.addRecipient(Message.RecipientType.TO, toAddr);
                msg.setContent("안녕하세요 허니포스 인증메일입니다.", "text/plain;charset=KSC5601"); //메일 전송될 내용
                Transport.send(msg);


            } catch (Exception e) {
                e.printStackTrace();
            }
        } //end of sendmail


        class senderAccount extends javax.mail.Authenticator {

            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("bumsgoh@gmail.com", "rhtkdqja@2"); // @gmail.com 제외한 계정 ID, PASS

            }
        }//end of senderAccount


    }// end of Gmail*/


}



/*FFF1F1F1<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
<solid android:color="#FFFFFF"/>
<stroke
        android:bottom = "1dp"
                android:color="#FFDFDFDF"
                />

</shape>*/