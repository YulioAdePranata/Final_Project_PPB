package com.projek.apotek.produk;

import android.os.Parcel;
import android.os.Parcelable;

public class ProdukModel implements Parcelable {
    private String produkId;
    private String namaProduk;
    private String imageProduk;
    private int priceProduk;

    public ProdukModel(){}

    public String getImageProduk() {
        return imageProduk;
    }

    public void setImageProduk(String imageProduk) {
        this.imageProduk = imageProduk;
    }

    public String getProdukId() {
        return produkId;
    }

    public void setProdukId(String produkId) {
        this.produkId = produkId;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public int getPriceProduk() {
        return priceProduk;
    }

    public void setPriceProduk(int priceProduk) {
        this.priceProduk = priceProduk;
    }

    protected ProdukModel(Parcel in) {
        produkId = in.readString();
        namaProduk = in.readString();
        imageProduk = in.readString();
        priceProduk = in.readInt();
    }

    public static final Creator<ProdukModel> CREATOR = new Creator<ProdukModel>() {
        @Override
        public ProdukModel createFromParcel(Parcel in) {
            return new ProdukModel(in);
        }

        @Override
        public ProdukModel[] newArray(int size) {
            return new ProdukModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(produkId);
        parcel.writeString(namaProduk);
        parcel.writeString(imageProduk);
        parcel.writeInt(priceProduk);

    }
}
