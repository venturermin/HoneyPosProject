package com.bumslap.bum.menuedit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumslap.bum.DB.DBHelper;
import com.bumslap.bum.DB.DBProvider;
import com.bumslap.bum.DB.Menu;
import com.bumslap.bum.R;
import com.bumslap.bum.order.OrderActivity;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MenuUpdateActivity extends AppCompatActivity {
    Button UpdateBTN;
    EditText UpdateMenuName, UpdateMenuPrice, UpdateMenuCost;
    ImageView UpdateMenuImage;
    FloatingActionButton UpdateMenuImageBTN;
    int IMAGE_CAPTURE = 1;
    Context context = this;

    public static DBHelper dbforAnalysis;
    public static DBProvider db;

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_update);
        setTitle("메뉴 등록");

        init();
        // DBProvider에 DBHelper 인스턴스 포함되어 있음
        db = new DBProvider(this);
        db.open();

        db.queryData("CREATE TABLE IF NOT EXISTS MENU_TABLE (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR, PRICE VARCHAR, COST VARCHAR, IMAGE BLOG)");

        UpdateMenuImageBTN.setOnClickListener(changeimage);
        UpdateBTN.setOnClickListener(UpdateMenu);
    }

    private void init() {
        UpdateBTN = (Button) findViewById(R.id.UpdateBtn);

        UpdateMenuImage = (ImageView) findViewById(R.id.UpdateMenuImage);
        UpdateMenuName = (EditText) findViewById(R.id.UpdateMenuName);
        UpdateMenuPrice = (EditText) findViewById(R.id.UpdateMenuPrice);
        UpdateMenuCost = (EditText) findViewById(R.id.UpdateMenuCost);

        UpdateMenuImageBTN = (FloatingActionButton) findViewById(R.id.UpdateMenuImageBTN);


    }
    Button.OnClickListener UpdateMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                db.insertData(
                        UpdateMenuName.getText().toString().trim(),
                        UpdateMenuPrice.getText().toString().trim(),
                        UpdateMenuCost.getText().toString().trim(),
                        imgaeViewToByte(UpdateMenuImage)
                );

                Toast.makeText(getApplicationContext(), "메뉴 등록 완료", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), MenuSettingActivity.class);
                startActivity(intent);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    Button.OnClickListener changeimage = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final CharSequence[] items = {"Camera", "Gallery"};
            android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(
                    context);

            // 제목셋팅
            alertDialogBuilder.setTitle("실행 시킬 앱");
            alertDialogBuilder.setItems(items,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {

                            // 프로그램을 종료한다
                            Toast.makeText(getApplicationContext(), items[id] + " 요!? ", Toast.LENGTH_SHORT).show();
                            switch (id) {
                                case 0:
                                    if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
                                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(intent, IMAGE_CAPTURE);
                                    }
                                    break;
                                case 1:
                                    int LOAD_IMAGE = 101;
                                    Intent intent = new Intent();
                                    intent.setType("image/*");   //가져오려하는 종류
                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                    startActivityForResult(intent, LOAD_IMAGE);
                                    break;

                            }
                            dialog.dismiss();
                        }
                    });

            // 다이얼로그 생성
            android.app.AlertDialog alertDialog = alertDialogBuilder.create();

            // 다이얼로그 보여주기
            alertDialog.show();

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        UpdateMenuImage = (ImageView) findViewById(R.id.UpdateMenuImage);
        if (requestCode == IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                Bitmap bitmap = (Bitmap) extras.get("data");
                UpdateMenuImage.setImageBitmap(bitmap);
            } else {
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
            }

        } else if (data != null) {
            Uri selectedImage = data.getData();
            InputStream inputStream = null;

            try {
                inputStream = this.getContentResolver().openInputStream(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            UpdateMenuImage.setImageBitmap(bitmap);
        }
    }




        private byte[] imgaeViewToByte(ImageView image) {
            Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            return byteArray;

        }

    }

