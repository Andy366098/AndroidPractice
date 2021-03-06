package com.runoob.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private EditText edtID,edtPW,edtContent;
    private Button btnAppend,btnClear,btnEnd;
    private static final String FILENAME = "login.txt";

    public FragmentFile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFile newInstance(String param1, String param2) {
        FragmentFile fragment = new FragmentFile();
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
        view = inflater.inflate(R.layout.fragment_file, container, false);
        DisplayFile(FILENAME);      //???????????????DisplayFile??????????????????????????????
        edtID = (EditText) view.findViewById(R.id.edtID);
        edtPW = (EditText) view.findViewById(R.id.edtPW);
        edtContent = (EditText) view.findViewById(R.id.edtContent);
        btnAppend = (Button)view.findViewById(R.id.btnAppend);
        btnClear = (Button)view.findViewById(R.id.btnClear);
        btnEnd = (Button)view.findViewById(R.id.btnEnd);

        btnAppend.setOnClickListener(listener);
        btnClear.setOnClickListener(listener);
        btnEnd.setOnClickListener(listener);

        return view;
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case(R.id.btnAppend):
                    if(edtID.getText().toString().equals("") || edtPW.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"???????????????????????????",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    FileOutputStream fout = null;            //?????????????????????
                    BufferedOutputStream buffout = null;
                    try {
                        fout = getActivity().openFileOutput(FILENAME, Context.MODE_APPEND); //MODE_APPEND??????????????????????????????
                        buffout = new BufferedOutputStream(fout);
                        //?????????????????????
                        buffout.write(edtID.getText().toString().getBytes());       //????????????????????????????????????byte
                        buffout.write("\n".getBytes());                             //??????
                        buffout.write(edtPW.getText().toString().getBytes());
                        buffout.write("\n".getBytes());
                        buffout.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    DisplayFile(FILENAME);
                    edtID.setText("");
                    edtPW.setText("");
                    break;
                case(R.id.btnClear):    //????????????
                    try {
                        //??????????????????????????????
                        fout = getActivity().openFileOutput(FILENAME,Context.MODE_PRIVATE);
                        fout.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    DisplayFile(FILENAME);
                    break;
                case (R.id.btnEnd):
                    getActivity().finish();
                    break;
            }

        }
    };
    private void DisplayFile(String fname){
        FileInputStream fin = null;         //?????????????????????
        BufferedInputStream buffin = null;
        try{
            fin = getActivity().openFileInput(fname);
            buffin = new BufferedInputStream(fin);
            byte[] buffbyte = new byte[20];     //??????????????????20????????????
            edtContent.setText("");
            //?????????????????????????????????
            do{                                     //????????????????????????????????????????????????????????????20???????????????????????????
                int flag = buffin.read(buffbyte);   //????????????????????????????????????????????????-1??????????????????
                if(flag == -1) break;
                else
                    edtContent.append(new String(buffbyte),0,flag);     //????????????
            }while (true);
            buffin.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}