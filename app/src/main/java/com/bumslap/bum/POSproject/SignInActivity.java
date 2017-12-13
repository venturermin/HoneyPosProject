package com.bumslap.bum.POSproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumslap.bum.POSproject.SignFuntion.FontFuntion;
import com.bumslap.bum.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

public class SignInActivity extends AppCompatActivity {
    private final int RC_SIGN_IN = 1;
    SignInButton signInButton;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private String TAG = "States";
    EditText emailText;
    EditText PasswordText;
    Button SignUpBtn;
    SessionCallback callback;
    LoginButton loginButton_kakao;
    Typeface mTypeface;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        progressBar = (ProgressBar) findViewById(R.id.progressRound);
        progressBar.setVisibility(View.GONE);
        /*progressBar = findViewById(R.id.progressBar1);
        Drawable draw = getDrawable(R.drawable.progressbar_set);

        progressBar.setProgressDrawable(draw);*/



        mAuth = FirebaseAuth.getInstance();

        signInButton = (SignInButton) findViewById(R.id.google_signin);
        emailText = (EditText) findViewById(R.id.EmailText);
        PasswordText = (EditText) findViewById(R.id.PasswordText);
        SignUpBtn = (Button) findViewById(R.id.SignInBtn);

        callback = new SessionCallback();                  // 이 두개의 함수 중요함
        Session.getCurrentSession().addCallback(callback);


        loginButton_kakao = findViewById(R.id.kakao_login);




        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)// gso 잘 이해되지 않는다
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso); //signinclient  잘 이해되지 않는다
    }




    protected void onResume(){
        super.onResume();
        FontFuntion fontFuntion = new FontFuntion();
        mTypeface = Typeface.createFromAsset(getAssets(), "fonts/NanumSquareRoundL.ttf");
        ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
        fontFuntion.setGlobalFont(root,mTypeface);


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signin();

            }
        });
    }//onResume

    private void signin(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        progressBar.setVisibility(View.VISIBLE);//구글과 파이어베이스의 플랫폼이 달라 해당 메서드 이용.
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Authntication is checked",Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });
        progressBar.setVisibility(View.GONE);
    }//firebaseAuthwith Google!




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {//인텐트로 보낸 해당 고객정보를 다시받아온다.
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                // ...
            }
        }
        Log.d("myLog", "onActivityResult11 " + "onActivityResult11");



        if (Session.getCurrentSession().handleActivityResult(requestCode,

                resultCode, data)) {

            Log.d("myLog", "onActivityResult " + "onActivityResult");

            return;

        }





    }//onActivityResult



    protected void CreateUser(final String email, final String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(),"Signup success",Toast.LENGTH_LONG).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());

                        }

                        // ...
                    }
                });
    }//create Users

    private void signInWithEmail(String email,String password) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                           // Toast.makeText(MainActivity.this, "Authentication failed.",
                                   // Toast.LENGTH_SHORT).show();

                        }

                        // ...
                        progressBar.setVisibility(View.GONE);  }
                });
    }// sign in with Email.

    public void onClickedSignIn(View v){

        if(!emailText.getText().toString().equals("") && !PasswordText.getText().toString().equals("")) {
            String email = emailText.getText().toString();
            String password = PasswordText.getText().toString();
            signInWithEmail(email, password);
        }
        else {
            Toast.makeText(getApplicationContext(),"Not Available",Toast.LENGTH_LONG).show();
        }


    }

    public void onClickedSignUp(View v){


        Intent intentSingup = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intentSingup);
    }


    private class SessionCallback implements ISessionCallback {


        @Override

        public void onSessionOpened() {
            progressBar.setVisibility(View.VISIBLE);

            Log.d("myLog", "onSessionOpened " + "onSessionOpened");

            redirectSignupActivity();

        }


        @Override

        public void onSessionOpenFailed(KakaoException exception) {

            Log.d("myLog", "onSessionOpenFailed " + "onSessionOpenFailed");


            if (exception != null) {

                Logger.e(exception);

            }

        }
    }


        @Override

        protected void onDestroy() {

            super.onDestroy();

            Session.getCurrentSession().removeCallback(callback);

        }


        protected void redirectSignupActivity() {

            Log.d("myLog", "redirectSignupActivity " + "redirectSignupActivity");

            requestMe();

            final Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);

            finish();

        }


        private void requestMe() {

            UserManagement.requestMe(new MeResponseCallback() {

                @Override

                public void onFailure(ErrorResult errorResult) {

                    String message = "failed to get user info. msg=" + errorResult;


                }


                @Override

                public void onSessionClosed(ErrorResult errorResult) {

                }


                @Override

                public void onSuccess(UserProfile userProfile) {

                    progressBar.setVisibility(View.GONE);

                   /* Log.d("myLog", "userProfile" + userProfile.getId());

                    Log.d("myLog", "userProfile" + userProfile.getNickname());

                    Log.d("myLog",

                            "userProfile" + userProfile.getThumbnailImagePath());*/


                }


                @Override

                public void onNotSignedUp() {

                }

            });


    }// end of session





}//end of class
