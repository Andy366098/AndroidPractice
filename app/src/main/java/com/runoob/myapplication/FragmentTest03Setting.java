package com.runoob.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTest03Setting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTest03Setting extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private TextView textView;
    private Button button;
    public FragmentTest03Setting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTest03Setting.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTest03Setting newInstance(String param1, String param2) {
        FragmentTest03Setting fragment = new FragmentTest03Setting();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_test03_setting, container, false);
        textView = (TextView) view.findViewById(R.id.superLink);      //獲取介面元件與變數名稱綁定
        textView.setAutoLinkMask(Linkify.EMAIL_ADDRESSES|Linkify.WEB_URLS);     //文字辨識，不要將電話跟Email誤認為網址
        textView.setMovementMethod(LinkMovementMethod.getInstance());   //設定可使連接到超連結的網頁
        button = (Button)view.findViewById(R.id.jtToast);             //獲取介面元件與變數名稱綁定
        button.setOnClickListener(listener);        //設置按鈕監聽器



        return view;
    }
    private Button.OnClickListener listener = new Button.OnClickListener(){     //按鈕監聽器
        @Override
        public void onClick(View view){
            Intent intent = new Intent();   //設置一個意圖
            intent.setClass(getActivity(),Toast_AlertDialog.class);  //我意圖從這個MainActivity跳到TryImage的Class
            startActivity(intent);      //開始動作
        }
    };
}