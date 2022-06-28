package com.example.ql_nhansu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DanhSachNhanVien extends AppCompatActivity {

    DbHelper dbHelper;
    ListView ds_NV;


    NhanVienArrayAdapter nvArrayAdapter;
    List<NhanVien> lstNhanVien;
    ListView lvNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachnhanvien);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.nhanvien);

        dbHelper = new DbHelper(DanhSachNhanVien.this);

        //anh xa
        Button them = findViewById(R.id.btn_them);
        Button sua = findViewById(R.id.btn_sua);
        Button xoa = findViewById(R.id.btn_xoa);
        Button xem = findViewById(R.id.btn_xem);
        ds_NV = findViewById(R.id.ds_nhanvien);

        taiLaiDS();

        //nhan xem de hien dannh sach
        xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taiLaiDS();
            }
        });
        //nhan them de hien dialog them
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienDiologThem();
            }
        });
        //nhan sua de gien dialog sua
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienDiologTimID();
            }
        });
        //nhan xoa de hien dialog xoa
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienDiologXoa();
            }
        });
    }

    private void taiLaiDS() {

        lvNhanVien = findViewById(R.id.ds_nhanvien);
        lstNhanVien = dbHelper.TimTatCaNV();
        nvArrayAdapter = new NhanVienArrayAdapter(this, R.layout.danhsach_nhanvien_list, (ArrayList<NhanVien>) lstNhanVien);
        lvNhanVien.setAdapter(nvArrayAdapter);

    }

    private void hienDiologThem(){
        AlertDialog.Builder al = new AlertDialog.Builder(DanhSachNhanVien.this);
        View view = getLayoutInflater().inflate(R.layout.them_dialog,null);

        final EditText edthoten = view.findViewById(R.id.edt_hotennv);
        final EditText edtemail = view.findViewById(R.id.edt_emailnv);
        final EditText edtsdt = view.findViewById(R.id.edt_sdtnv);
        final EditText edtchucvu = view.findViewById(R.id.edt_chucvunv);
        Button btnthem = view.findViewById(R.id.btn_themnv);

        al.setView(view);
        AlertDialog alertDialog = al.show();

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nhanvien = new NhanVien();
                nhanvien.setHoten(edthoten.getText().toString());
                nhanvien.setEmail(edtemail.getText().toString());
                nhanvien.setSdt(edtsdt.getText().toString());
                nhanvien.setChucvu(edtchucvu.getText().toString());
                 dbHelper.ThemNV(nhanvien);
                 alertDialog.dismiss();
                 taiLaiDS();
            }
        });

    }

    private void hienDiologTimID(){
        AlertDialog.Builder al = new AlertDialog.Builder(DanhSachNhanVien.this);
        View view = getLayoutInflater().inflate(R.layout.suatheoid_dialog,null);

        final EditText edtid = view.findViewById(R.id.edt_sua_nv_id);
        Button btntim = view.findViewById(R.id.btn_sua_nv_id);

        al.setView(view);
        AlertDialog alertDialog = al.show();

        btntim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hienDiologSua(edtid.getText().toString());
                alertDialog.dismiss();
            }
        });
    }

    private void hienDiologSua(String id) {
        NhanVien nhanvien = dbHelper.TimNV(Integer.parseInt(id));
        AlertDialog.Builder al = new AlertDialog.Builder(DanhSachNhanVien.this);
        View view = getLayoutInflater().inflate(R.layout.sua_dialog,null);

        final EditText edthoten = view.findViewById(R.id.edt_hoten_nv);
        final EditText edtemail = view.findViewById(R.id.edt_email_nv);
        final EditText edtsdt = view.findViewById(R.id.edt_sdt_nv);
        final EditText edtchucvu = view.findViewById(R.id.edt_chucvu_nv);
        Button btnSua = view.findViewById(R.id.btn_sua_nv);

        al.setView(view);
        AlertDialog alertDialog = al.show();

        //Gán dữu liệu vào edittext
        edthoten.setText(nhanvien.getHoten());
        edtemail.setText(nhanvien.getEmail());
        edtsdt.setText(nhanvien.getSdt());
        edtchucvu.setText(nhanvien.getChucvu());

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nhanvien = new NhanVien();
                nhanvien.setID(id);
                nhanvien.setHoten(edthoten.getText().toString());
                nhanvien.setEmail(edtemail.getText().toString());
                nhanvien.setSdt(edtsdt.getText().toString());
                nhanvien.setChucvu(edtchucvu.getText().toString());
                dbHelper.CapNhatNV(nhanvien);
                alertDialog.dismiss();
                taiLaiDS();
            }
        });
    }

    private void hienDiologXoa(){
        AlertDialog.Builder al = new AlertDialog.Builder(DanhSachNhanVien.this);
        View view = getLayoutInflater().inflate(R.layout.xoa_dialog,null);

        final EditText edtid = view.findViewById(R.id.edt_xoa_id);
        Button btnxoa= view.findViewById(R.id.btn_xoa_nv);

        al.setView(view);//hien dialog
        AlertDialog alertDialog = al.show();

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dbHelper.XoaNV(edtid.getText().toString());
               alertDialog.dismiss();
               taiLaiDS();
            }
        });
    }
}