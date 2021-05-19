package com.runoob.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView pageNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Configuration configuration = getResources().getConfiguration();
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentTest01Bmi(),"BMI");         //新增分頁
        adapter.addFragment(new FragmentTest02Order(),"Order");
        adapter.addFragment(new FragmentTest03Setting(),"Setting");
        adapter.addFragment(new GoToFile(),"GoGoGo");
        viewPager = findViewById(R.id.viewPagerMain);   //獲取介面元件
        viewPager.setAdapter(adapter);          //設定適配器
        viewPager.setOffscreenPageLimit(3);     //動態載入的頁面限制
        viewPager.setCurrentItem(1);    //可設置起始頁面由哪一頁開始，像現在就是從第二頁開始
        /*pageNum = (TextView)findViewById(R.id.textView2);
        pageNum.setText(viewPager.getCurrentItem() + 1);*/
        /*if (savedInstanceState != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){  //可設定在橫向會出現的資料
            TextView tvSecondRow = findViewById(R.id.textView2);
            tvSecondRow.setText("Hi,我是橫向時才會出現的喔！");
            tvShow.setText(savedInstanceState.getString("SAVE"));

        }else if (savedInstanceState != null && configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            tvShow.setText(savedInstanceState.getString("SAVE"));
        }*/
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {      //若有需要在螢幕翻轉時保存的資料須在此設定
        super.onSaveInstanceState(outState);
        /*String saveWord = tvShow.getText().toString();
        outState.putString("SAVE",saveWord);*/
        //mNumberPicker2.setValue(25);

    }
}