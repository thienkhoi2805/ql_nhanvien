package com.example.ql_nhansu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class XacNhanLuong extends AppCompatActivity {

    String strHoTen , strEmail , strLuong , strThanhToan, strThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xacnhanluong);

        ActionBar myActionBAr = getSupportActionBar();
        myActionBAr.setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        Bundle bundleNhan = intent.getExtras();

        strHoTen = bundleNhan.getString("HoTen", "");
        strEmail = bundleNhan.getString("Email", "");
        strLuong = bundleNhan.getString("Luong", "");
        strThanhToan = bundleNhan.getString("ThanhToan", "");

        strThongTin = "Họ tên nhân viên: " + strHoTen;
        strThongTin += "\nEmail: " + strEmail;
        strThongTin += "\nTổng lương: " + strLuong;
        strThongTin += "\nHình thức nhận lương: " + strThanhToan;

        TextView txtNhanVien = findViewById(R.id.txt_nhanvien);
        txtNhanVien.setText(strThongTin);
    }

    public void DongY(View view) {
        setResult(RESULT_OK);
        finish();
    }
    public void HuyBo(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}