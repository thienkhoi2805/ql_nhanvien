package com.example.ql_nhansu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class NhanVienArrayAdapter extends ArrayAdapter<NhanVien> {
    Activity context;
    int layoutId;
    ArrayList<NhanVien> nvArray;

    public NhanVienArrayAdapter(Activity context, int layoutId, ArrayList<NhanVien> nvArray) {
        super(context, layoutId, nvArray);
        this.context = context;
        this.nvArray = nvArray;
        this.layoutId = layoutId;
    }
    public void setNvArray(ArrayList<NhanVien> nvArray) {
        this.nvArray = nvArray;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return nvArray.size();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        if(nvArray.size()>0 && position>=0) {
            final NhanVien nv = nvArray.get(position);
            final TextView txt_id = convertView.findViewById(R.id.txt_id);
            txt_id.setText(nv.getID());
            final TextView txt_hoten = convertView.findViewById(R.id.txt_hoten);
            txt_hoten.setText(nv.getHoten());
            final TextView txt_sdt = convertView.findViewById(R.id.txt_sdt);
            txt_sdt.setText(nv.getSdt());
            final TextView txt_email = convertView.findViewById(R.id.txt_email);
            txt_email.setText(nv.getEmail());
//            final TextView txt_chucvu = convertView.findViewById(R.id.txt_chucvu);
//            txt_chucvu.setText(nv.getChucvu());
        }
        return convertView;
    }
}
