package com.example.ql_nhansu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "qlnv.db";
    private static final String TABLE_NAME = "nhanvien";
    private static final String ID= "id";
    private static final String  hoten = "hoten";
    private static final String email = "email";
    private static final String sdt = "sdt";
    private static final String chucvu = "chucvu";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME ,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                hoten+ " TEXT , " +
                email+ " TEXT , " +
                sdt+ " TEXT , " +
                chucvu+ " TEXT " + ")";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE if EXISTS " + TABLE_NAME);
    }

    public void ThemNV(NhanVien nhanvien){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(hoten, nhanvien.getHoten());
        contentValues.put(email, nhanvien.getEmail());
        contentValues.put(sdt, nhanvien.getSdt());
        contentValues.put(chucvu, nhanvien.getChucvu());
        db.insert(TABLE_NAME,null,contentValues);
        db.close();
    }

    public NhanVien TimNV(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cs = db.query(TABLE_NAME,new String[]{ID,hoten,email,sdt,chucvu}, ID + " = ? ", new String[]{String.valueOf(id)} ,null , null ,null);
        if ( cs != null){
            cs.moveToFirst();
        }
        NhanVien nhanVien = new NhanVien(cs.getString(0), cs.getString(1), cs.getString(2), cs.getString(3) , cs.getString(4));
        db.close();
        return nhanVien;

    }

    public List<NhanVien> TimTatCaNV(){
        List<NhanVien> DSNhanVien = new ArrayList<>();
        String sql ="SELECT * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cs = db.rawQuery(sql,null);
        if (cs.moveToFirst()){
            do{
                NhanVien nhanVien = new NhanVien(cs.getString(0), cs.getString(1), cs.getString(2), cs.getString(3),cs.getString(4));
                DSNhanVien.add(nhanVien);
            }while(cs.moveToNext());
        }
        db.close();
        return DSNhanVien;
    }

    public int CapNhatNV (NhanVien nhanvien){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(hoten, nhanvien.getHoten());
        contentValues.put(email, nhanvien.getEmail());
        contentValues.put(sdt, nhanvien.getSdt());
        contentValues.put(chucvu, nhanvien.getChucvu());
        return db.update(TABLE_NAME,contentValues,ID + " = ?",new String[]{String.valueOf(nhanvien.getID())});
    }

    public void XoaNV (String id ){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,ID +" = ?",new String[]{id});
        db.close();
    }
}
