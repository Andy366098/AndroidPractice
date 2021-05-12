package com.runoob.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Toast_AlertDialog extends AppCompatActivity {
    private TextView password;
    private Button toast1;
    private Button alert;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast_alert_dialog);    //設置此Class對應的布局文件並加載
        toast1 = (Button)findViewById(R.id.toast1);     //獲取介面元件與變數名稱綁定
        toast1.setOnClickListener(listener);            //設置按鈕監聽器
        alert = (Button)findViewById(R.id.alertDialog1);
        alert.setOnClickListener(listener);
        password = (TextView)findViewById(R.id.password);

    }
    private Button.OnClickListener listener = new Button.OnClickListener(){ //按鈕監聽器

        @Override
        public void onClick(View v) {
            switch (v.getId()){     //用switch來判定按下不同按鈕的功能
                case R.id.toast1:   //若按下的是toast1的按鈕
                    str = password.getText().toString();    //獲取所輸入的Text字串
                    if(str.equals("123456")){       //判定密碼是否等於123456
                        Toast showMessage = Toast.makeText(Toast_AlertDialog.this,"密碼正確！",Toast.LENGTH_LONG);    //創建一個快顯訊息並設定他的文字及顯示時間
                        //showMessage.setGravity(Gravity.TOP,0,200); //快顯訊息所顯示的位置 後兩個數為XY偏移量，目前不知為何無作用
                        showMessage.show(); //顯示快顯訊息
                    }else {
                        Toast showMessage = Toast.makeText(Toast_AlertDialog.this,"密碼錯誤，請重新輸入！",Toast.LENGTH_LONG);
                        showMessage.show();
                        password.setText("");
                    }
                    break;
                case R.id.alertDialog1:
                    new AlertDialog.Builder(Toast_AlertDialog.this)
                            .setTitle("確認視窗")         //設置標題
                            .setIcon(R.drawable.list)   //設置一個小icon
                            .setMessage("確定要結束應用程式嗎？")  //設置顯示的訊息
                            .setPositiveButton("確定", new DialogInterface.OnClickListener() {    //設定一個確認的按鈕
                                @Override
                                public void onClick(DialogInterface dialog, int which) {        //必要方法，設置其按下按鈕會觸發的動作
                                    finish();                                                       //結束程式
                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {    //設定一個取消的按鈕
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setNeutralButton("忽略", new DialogInterface.OnClickListener() {     //設定一個忽略的按鈕
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                    break;

            }


        }
    };
}