package com.projek.apotek.history;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoryModel implements Parcelable {
    private String checkoutiId;
    private String produkId;
    private String alamat;
    private String namaProduk;
    private String dateOrder;
    private String dp;
    private String harga;
    private String namaLengkap;
    private String noTelp;
    private String qty;


    public HistoryModel(){}

    public String getProdukId() {
        return produkId;
    }

    public void setProdukId(String produkId) {
        this.produkId = produkId;
    }

    protected HistoryModel(Parcel in) {
        checkoutiId = in.readString();
        alamat = in.readString();
        namaProduk = in.readString();
        dateOrder = in.readString();
        dp = in.readString();
        harga = in.readString();
        namaLengkap = in.readString();
        noTelp = in.readString();
        qty = in.readString();
        produkId = in.readString();
    }

    public static final Creator<HistoryModel> CREATOR = new Creator<HistoryModel>() {
        @Override
        public HistoryModel createFromParcel(Parcel in) {
            return new HistoryModel(in);
        }

        @Override
        public HistoryModel[] newArray(int size) {
            return new HistoryModel[size];
        }
    };

    public String getCheckoutiId() {
        return checkoutiId;
    }

    public void setCheckoutiId(String checkoutiId) {
        this.checkoutiId = checkoutiId;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDp() {
        return dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(checkoutiId);
        parcel.writeString(alamat);
        parcel.writeString(namaProduk);
        parcel.writeString(dateOrder);
        parcel.writeString(dp);
        parcel.writeString(harga);
        parcel.writeString(namaLengkap);
        parcel.writeString(noTelp);
        parcel.writeString(qty);
        parcel.writeString(produkId);
    }
}
