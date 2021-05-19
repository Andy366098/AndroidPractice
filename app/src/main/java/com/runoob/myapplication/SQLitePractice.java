package com.runoob.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SQLitePractice extends AppCompatActivity {

    private EditText edtSQL;
    private Button btnDo;
    ListView listView;
    String str,itemdata;
    int n = 1;

    //宣告全域資料庫類別物件 db
    private SQLiteDatabase db = null;
    //建立資料表的欄位
    private final static String CREATE_TABLE = "CREATE TABLE table01(_id INTEGER PRIMARY KEY,num INTEGER,data TEXT)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_practice);

        edtSQL = (EditText)findViewById(R.id.edtSQL);
        btnDo = (Button)findViewById(R.id.btnDo);
        listView = (ListView) findViewById(R.id.listview);

        //預設SQL指令為新增資料
        itemdata = "資料項目" + n;
        str = "INSERT INTO table01 (num,data) values (" + n + ",'" + itemdata + "')";
        edtSQL.setText(str);

        //建立資料庫，若資料庫已經存在則將之開啟
        db = openOrCreateDatabase("db1.db",MODE_PRIVATE,null);
        try {
            db.execSQL(CREATE_TABLE);   //建立資料表
        }catch(Exception e){
            UpdateAdapter();        //自定義方法載入資料表至RecyclerView中
        }

        btnDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.execSQL(edtSQL.getText().toString());    //執行SQL
                    UpdateAdapter();        //更新RecyclerView
                    n++;
                    itemdata = "資料項目" + n;
                    str = "INSERT INTO table01 (num,data) values (" + n + ",'" + itemdata + "')";
                    edtSQL.setText(str);
                    setTitle("資料新增完畢！");
                }catch (Exception e){
                    setTitle("SQL語法錯誤！");
                }
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //故意刪除原本的資料表，讓每次執行時資料表是空的
        db.execSQL("DROP TABLE table01");
        db.close();     //關閉資料庫
    }
    public void UpdateAdapter(){
        Cursor cursor = db.rawQuery("SELECT * FROM table01", null);
        if (cursor != null && cursor.getCount() >= 0){
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_2, // 包含兩個資料項
                    cursor, // 資料庫的 Cursors 物件
                    new String[] { "num", "data" }, // num、data 欄位
                    new int[] { android.R.id.text1, android.R.id.text2 }, // 與 num、data對應的元件
                    0);  // adapter 行為最佳化
            listView.setAdapter(adapter); // 將adapter增加到listview01中
        }
    }
}