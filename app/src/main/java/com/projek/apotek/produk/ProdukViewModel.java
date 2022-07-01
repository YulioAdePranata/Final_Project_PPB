package com.projek.apotek.produk;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class ProdukViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<ProdukModel>> listProduct = new MutableLiveData<>();
    final ArrayList<ProdukModel> productModelArrayList = new ArrayList<>();
    private static final String TAG = ProdukViewModel.class.getSimpleName();

    public void setListProduct() {
        productModelArrayList.clear();

        try {
            FirebaseFirestore
                    .getInstance()
                    .collection("product")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                ProdukModel model = new ProdukModel();
                                model.setNamaProduk("" + document.get("name"));
                                model.setImageProduk("" + document.get("dp"));
                                model.setPriceProduk(Integer.parseInt("" + document.get("harga")));
                                model.setProdukId("" + document.get("produkId"));
                                productModelArrayList.add(model);
                            }
                            listProduct.postValue(productModelArrayList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public LiveData<ArrayList<ProdukModel>> getProductList() {
        return listProduct;
    }

}
