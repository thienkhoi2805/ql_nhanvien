package com.example.ql_nhansu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] arrActivity = {"Tính lương nhân viên", "Danh sách sinh viên"};
    ListView lvActivity;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.nhanvien);

        lvActivity = findViewById(R.id.lst_activity);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrActivity);
        lvActivity.setAdapter(adapter);
        lvActivity.setOnItemClickListener(new ChonCongViec());
    }

    private class ChonCongViec implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, TinhLuongNhanVien.class);
            if (i == 1)
                intent = new Intent(MainActivity.this, DanhSachNhanVien.class);
            startActivity(intent);
        }
    }
}