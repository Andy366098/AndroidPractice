package com.runoob.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTest02Order#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTest02Order extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;                      //宣告一個View變數
    private NumberPicker mNumberPicker;     //宣告一個NumberPicker變數
    //private NumberPicker mNumberPicker2;
    public FragmentTest02Order() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTest02Order.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTest02Order newInstance(String param1, String param2) {
        FragmentTest02Order fragment = new FragmentTest02Order();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    /*@Override             //原本的onCreateView
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            return inflater.inflate(R.layout.fragment_test02_order, container, false);
        }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  //將onCreatView改寫成這樣才能正確獲取介面元件
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_test02_order, container, false);
        mNumberPicker = (NumberPicker)view.findViewById(R.id.numberPicker);     //在Fragment裡要獲取元件要這樣寫
        mNumberPicker.setMinValue(0); //設定最小值
        mNumberPicker.setMaxValue(200); //設定最大值
        mNumberPicker.setValue(25);//設定現值
        int nowValue = mNumberPicker.getValue(); //取得現值
        mNumberPicker.setOnValueChangedListener(numPickerOnValueChange); //設定數字變化監聽事件
        /*mNumberPicker2 = (NumberPicker)view.findViewById(R.id.numberPicker2);     //第二個數字滾輪
        mNumberPicker2.setMinValue(0);
        mNumberPicker2.setMaxValue(200);
        mNumberPicker2.setValue(25);
        int nowValue2 = mNumberPicker.getValue();
        mNumberPicker2.setOnValueChangedListener(numPickerOnValueChange);*/
        return view;
    }
    private NumberPicker.OnValueChangeListener numPickerOnValueChange = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {        //這裡面可規劃當數值變化要執行什麼動作
            switch (picker.getId()){
                case R.id.numberPicker:
                    break;
                /*case R.id.numberPicker2:
                    break;*/
            }

        }
    };
}