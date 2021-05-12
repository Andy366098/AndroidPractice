package com.runoob.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentTest01Bmi(),"BMI");         //新增分頁
        adapter.addFragment(new FragmentTest02Order(),"Order");
        adapter.addFragment(new FragmentTest03Setting(),"Setting");
        viewPager = findViewById(R.id.viewPagerMain);   //獲取介面元件
        viewPager.setAdapter(adapter);          //設定適配器
        viewPager.setOffscreenPageLimit(3);     //動態載入的頁面限制
        viewPager.setCurrentItem(1);    //可設置起始頁面由哪一頁開始，像現在就是從第二頁開始

    }
}