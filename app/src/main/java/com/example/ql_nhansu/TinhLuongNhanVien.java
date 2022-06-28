package com.example.ql_nhansu;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TinhLuongNhanVien extends AppCompatActivity {

    String arrChucvu[] = { "Giám đốc", "Phó giám đốc", "Trưởng phòng", "Nhân viên" };
    int arrLuongcb[] = {20000000,15000000,11000000,8000000};
    TextInputLayout layouthoten , layoutemail,  layoutsotien , layoutluongcb;
    TextInputEditText edthoten, edtemail,  edtsotien, edtluongcb;
    Spinner spn;
    CheckBox chkantrua , chkdienthoai , chkxangxe;
    RadioButton rdotienmat , rdochuyenkhoan;

    String strChucVu , strHoTen , strEmail, strLuong, strThanhToan;
    Integer luongcb, tongluong;

    ActivityResultLauncher<Intent> runActivity;

    Button btnXacNhan ,btnTinh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinhluongnhanvien);

        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setDisplayShowHomeEnabled(true);
        myActionBar.setIcon(R.mipmap.nhanvien);

        layouthoten = findViewById(R.id.layout_hotennv);
        layoutemail = findViewById(R.id.layout_email);
        layoutluongcb = findViewById(R.id.layout_luongcb);
        layoutsotien = findViewById(R.id.layout_sotien);

        edthoten = findViewById(R.id.edt_hotennv);
        edtemail = findViewById(R.id.edt_email);
        edtluongcb = findViewById(R.id.edt_luongcb);
        edtsotien = findViewById(R.id.edt_sotien);

        spn = findViewById(R.id.spn_chucvu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,arrChucvu);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(new ChonChucVu());

        chkantrua = (CheckBox) findViewById(R.id.chk_antrua);
        chkdienthoai =(CheckBox) findViewById(R.id.chk_dienthoai);
        chkxangxe = (CheckBox)findViewById(R.id.chk_xangxe);

        rdochuyenkhoan =findViewById(R.id.rdo_chuyenkhoan);
        rdotienmat = findViewById(R.id.rdo_tienmat);

        edthoten.setText("To Thien Khoi");
        edtemail.setText("tothienthoi123@gmail.com");

        btnXacNhan = findViewById(R.id.btn_xacnhan);
        btnTinh = findViewById(R.id.btn_tinhluong);

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tongluong = luongcb;
                btnXacNhan.setEnabled(true);
                if(chkantrua.isChecked())
                {tongluong = luongcb + 1500000;}

                if(chkdienthoai.isChecked())
                {tongluong = tongluong + 1000000;}

                if(chkxangxe.isChecked())
                {tongluong = tongluong + 500000;}

                edtsotien.setText("Số tiền: " + tongluong);
            }
        });

        runActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Toast.makeText(TinhLuongNhanVien.this, "Đồng ý !", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(TinhLuongNhanVien.this, "Không đồng ý !", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    private class ChonChucVu implements android.widget.AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            strChucVu = arrChucvu[i];
            luongcb = arrLuongcb[i];
            edtluongcb.setText("Lương cơ bản: " + luongcb);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
            strChucVu = "";
            luongcb = 0;
        }
    }

    public void XacNhan (View view){
        strHoTen = edthoten.getText().toString().trim();

        if (strHoTen.length() == 0){
            layouthoten.setError("Lỗi chưa nhập họ tên");
            edthoten.requestFocus();
            return;
        }else
            layouthoten.setError(null);

        strEmail = edtemail.getText().toString().trim();

        if (strEmail.length() == 0){
            layoutemail.setError("Lỗi chưa nhập email");
            edtemail.requestFocus();
            return;
        }else
            layoutemail.setError(null);

        strLuong = edtsotien.getText().toString().trim();

        RadioGroup grpThanhToan = findViewById(R.id.grp_thanhtoan);
        int id = grpThanhToan.getCheckedRadioButtonId();
        RadioButton rad = findViewById(id);
        strThanhToan = rad.getText().toString();

        Bundle bundleGoi = new Bundle();
        bundleGoi.putString("HoTen",strHoTen);
        bundleGoi.putString("Email",strEmail);
        bundleGoi.putString("Luong",strLuong);
        bundleGoi.putString("ThanhToan",strThanhToan);

        Intent intent = new Intent(this,XacNhanLuong.class);
        intent.putExtras(bundleGoi);

        runActivity.launch(intent);
    }

    public void Dong(View view){finish();}


}