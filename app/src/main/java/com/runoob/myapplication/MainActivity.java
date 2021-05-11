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
        adapter.addFragment(new FragmentTest01Bmi(),"BMI");
        adapter.addFragment(new FragmentTest02Order(),"Order");
        adapter.addFragment(new FragmentTest03Setting(),"Setting");
        viewPager = findViewById(R.id.viewPagerMain);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

    }
}