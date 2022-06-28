package com.example.ql_nhansu;

public class NhanVien {
    private String ID;
    private String hoten;
    private String email;
    private String sdt;
    private String chucvu;

    public NhanVien(String id ,String hoten, String email, String sdt, String chucvu) {
        this.ID = id;
        this.hoten = hoten;
        this.email = email;
        this.sdt = sdt;
        this.chucvu = chucvu;
    }

    public NhanVien(){
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }
}
