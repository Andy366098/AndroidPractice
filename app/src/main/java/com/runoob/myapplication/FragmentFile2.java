package com.runoob.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentFile2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFile2 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View view;
    private String[] login;
    private File filename;
    private TextView txtID,txtPW;
    private EditText edtID,edtPW;
    private Button btnOK,btnReset,btnRegister;

    public FragmentFile2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentFile2.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentFile2 newInstance(String param1, String param2) {
        FragmentFile2 fragment = new FragmentFile2();
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
        view = inflater.inflate(R.layout.fragment_file2, container, false);
        requestStoragePermission();
        edtID = (EditText) view.findViewById(R.id.editTextTextPersonName);
        edtPW = (EditText) view.findViewById(R.id.editTextTextPassword);
        btnOK = (Button)view.findViewById(R.id.btnOK);
        btnReset = (Button)view.findViewById(R.id.btnReset);
        btnRegister = (Button)view.findViewById(R.id.btnRegister);

        btnOK.setOnClickListener(listener);
        btnReset.setOnClickListener(listener);
        btnRegister.setOnClickListener(listener);
        return view;
    }
    //????????????
    private void requestStoragePermission(){
        if(Build.VERSION.SDK_INT >= 23){        //Android6.0??????
            //???????????????????????????
            int hasPermission = getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if(hasPermission != PackageManager.PERMISSION_GRANTED){     //?????????????????????
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
                return;
            }
        }
        FirstWrite();
    }
    //requestPermissions ???????????????
    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,@NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){   //????????????
                readFile();
            }else {
                Toast.makeText(getActivity(),"??????????????????",Toast.LENGTH_SHORT).show();
                getActivity().finish(); //??????????????????
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }

    }
    private void FirstWrite(){  //?????????????????????????????????????????????????????????
        File path = getContext().getExternalFilesDir("").getAbsoluteFile(); //???????????????
        File file = new File(path,"login2.txt");    //???????????????
        try {
            FileOutputStream fout = new FileOutputStream(file,true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fout));
            writer.write("");
            writer.close();
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //????????????????????????login??????
    private void readFile(){
        File path = getContext().getExternalFilesDir("").getAbsoluteFile(); //???????????????
        filename = new File(path,"login2.txt");    //???????????????
        try{
            FileInputStream fin = new FileInputStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fin));
            String line = "",wholedata = "";
            while ((line = reader.readLine()) != null){     //??????????????????????????????
                wholedata = wholedata + line + "\n";        //?????????+???+??????
            }
            login = wholedata.split("\n");  //???split??????????????????????????????
            reader.close();
            fin.close();
        }catch (Exception e){
            Toast.makeText(getActivity().getApplicationContext(),"error!",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case (R.id.btnOK):      //??????
                    readFile();     //????????????????????????login??????
                    //???????????????????????????????????????
                    if (edtID.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"?????????????????????",Toast.LENGTH_SHORT).show();
                        break;
                    }else if(edtPW.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"?????????????????????",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    Boolean flag = false;
                    for(int i = 0;i < login.length;i += 2){
                        if(edtID.getText().toString().equals(login[i])){    //????????????
                            flag = true;
                            if (edtPW.getText().toString().equals(login[i+1])){ //????????????
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("??????")
                                        .setMessage("???????????????\n???????????????????????????")
                                        .setPositiveButton("??????", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                //??????????????????????????????????????????????????????
                                            }
                                        })
                                        .show();
                            }else {
                                Toast.makeText(getActivity().getApplicationContext(),"??????????????????",Toast.LENGTH_LONG).show();
                                edtPW.setText("");
                                break;
                            }
                        }
                    }
                    if(!flag){
                        Toast.makeText(getActivity().getApplicationContext(),"??????????????????",Toast.LENGTH_LONG).show();
                        edtID.setText("");
                        edtPW.setText("");
                    }
                    break;
                case (R.id.btnReset):   //????????????
                    edtID.setText("");
                    edtPW.setText("");
                    break;
                case (R.id.btnRegister):
                    readFile();
                    //???????????????????????????????????????
                    if (edtID.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"?????????????????????",Toast.LENGTH_SHORT).show();
                        break;
                    }else if(edtPW.getText().toString().equals("")){
                        Toast.makeText(getActivity(),"?????????????????????",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    boolean flagHaveA = false;
                    for(int i = 0;i < login.length;i += 2){
                        if(edtID.getText().toString().equals(login[i])){    //????????????
                            Toast.makeText(getActivity().getApplicationContext(),"??????????????????",Toast.LENGTH_LONG).show();
                            flagHaveA = true;
                        }
                    }
                    if(!flagHaveA){
                        File path = getContext().getExternalFilesDir("").getAbsoluteFile();     //??????????????????????????????
                        File file = new File(path, "login2.txt");   //??????????????????????????????
                        try {
                            FileOutputStream fout = new FileOutputStream(file,true);
                            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fout));
                            writer.write(edtID.getText().toString());
                            writer.write("\n");
                            writer.write(edtPW.getText().toString());
                            writer.write("\n");
                            writer.close();
                            fout.close();
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("???????????????")
                                    .setMessage("?????????" + edtID.getText().toString() + "\n?????????" + edtPW.getText().toString())
                                    .show();
                            edtID.setText("");
                            edtPW.setText("");
                        }catch (Exception e){
                            Toast.makeText(getActivity().getApplicationContext(),"???????????????",Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };

}