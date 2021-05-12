package com.runoob.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTest01Bmi#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTest01Bmi extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View view;
    private SeekBar seekbar;
    private SeekBar seekbar2;
    private TextView seektext;
    private TextView seektext2;
    public FragmentTest01Bmi() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTest01Bmi.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTest01Bmi newInstance(String param1, String param2) {
        FragmentTest01Bmi fragment = new FragmentTest01Bmi();
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
        view = inflater.inflate(R.layout.fragment_test01_bmi, container, false);
        seekbar = (SeekBar) view.findViewById(R.id.seekBar);
        seektext = (TextView)view.findViewById(R.id.textView5);
        seekbar.setMax(150);//設定SeekBar最大值
        seekbar.setProgress(80);//設定SeekBar拖移初始值
        seektext.setText("目前拖移植：" +seekbar.getProgress() + "  /  最大值："+seekbar.getMax());   //設定顯示的文字
        seekbar.setOnSeekBarChangeListener(progress);
        seekbar2 = (SeekBar) view.findViewById(R.id.seekBar2);
        seektext2 = (TextView)view.findViewById(R.id.textView6);
        seekbar2.setMax(100);//設定SeekBar最大值
        seekbar2.setProgress(20);//設定SeekBar拖移初始值
        seektext2.setText("目前拖移植：" +seekbar2.getProgress() + "  /  最大值："+seekbar2.getMax());   //設定顯示的文字
        seekbar2.setOnSeekBarChangeListener(progress2);
        return view;
    }
    private SeekBar.OnSeekBarChangeListener progress = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            seektext.setText("目前拖移植：" + progress + "  /  最大值："+seekbar.getMax());
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {     //當開始拖動SeekBar時會觸發
            Toast showMessage = Toast.makeText(getActivity(), "開始拖動SeekBar", Toast.LENGTH_SHORT);
            showMessage.show();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {      //當放開SeekBar時會觸發
            Toast showMessage = Toast.makeText(getActivity(), "放開SeekBar", Toast.LENGTH_SHORT);
            showMessage.show();
        }
    };
    private SeekBar.OnSeekBarChangeListener progress2 = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            seektext2.setText("目前拖移植：" + progress + "  /  最大值："+seekbar2.getMax());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            Toast showMessage2 = Toast.makeText(getActivity(), "開始拖動SeekBar2", Toast.LENGTH_SHORT);
            showMessage2.show();
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Toast showMessage2 = Toast.makeText(getActivity(), "放開SeekBar2", Toast.LENGTH_SHORT);
            showMessage2.show();
        }
    };
}