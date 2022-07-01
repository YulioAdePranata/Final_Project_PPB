package com.projek.apotek.history;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.projek.apotek.produk.ProdukModel;

import java.util.ArrayList;

public class HistoryViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<HistoryModel>> listHistory = new MutableLiveData<>();
    final ArrayList<HistoryModel> itemList = new ArrayList<>();
    public static final String TAG = HistoryViewModel.class.getSimpleName();


    public void setListHistory(){
        itemList.clear();
        try {
            FirebaseFirestore
                    .getInstance()
                    .collection("order")
                    .get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HistoryModel model = new HistoryModel();
                                model.setCheckoutiId("" + document.get("checkoutId"));
                                model.setNamaProduk("" + document.get("namaproduk"));
                                model.setNamaLengkap(""+document.get("namalengkap"));
                                model.setAlamat("" + document.get("alamat"));
                                model.setDateOrder("" + document.get("dateorder"));
                                model.setDp("" + document.get("dp"));
                                model.setHarga("" + document.get("harga"));
                                model.setProdukId("" + document.get("produkId"));
                                model.setNoTelp("" + document.get("notelpon"));
                                model.setQty("" + document.get("qty"));
                                itemList.add(model);
                            }
                            listHistory.postValue(itemList);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LiveData<ArrayList<HistoryModel>> getHistoryList() {
        return listHistory;
    }

}
