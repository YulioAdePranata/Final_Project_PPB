package com.projek.apotek.produk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.projek.apotek.R;
import com.projek.apotek.databinding.ActivityDetailProdukBinding;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DetailProdukActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUK = "produk";
    private ProdukModel model;
    private ActivityDetailProdukBinding binding;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProdukBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        model = getIntent().getParcelableExtra(EXTRA_PRODUK);
        NumberFormat formatter = new DecimalFormat("#,###");

        Glide.with(this)
                .load(model.getImageProduk())
                .placeholder(R.drawable.ic_baseline_image_24)
                .into(binding.imgDetail);

        binding.name.setText(model.getNamaProduk());
        binding.hargaDetail.setText("IDR "+ formatter.format(model.getPriceProduk()));

        binding.checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formValidation();
            }
        });
    }


    private void formValidation() {
        String alamat = binding.address.getText().toString().trim();
        String namaLengkap = binding.namadetail.getText().toString().trim();
        String notelp = binding.notelp.getText().toString().trim();
        String qty = binding.tvQty.getText().toString().trim();

        if (alamat.isEmpty()){
            Toast.makeText(this, "Alamat jangan kosong!", Toast.LENGTH_SHORT).show();
            return;
        }else if(namaLengkap.isEmpty()){
            Toast.makeText(this, "Nama Lengkap jangan kosong", Toast.LENGTH_SHORT).show();
            return;
        }else if(notelp.isEmpty()){
            Toast.makeText(this, "No Telphone jangan kosong", Toast.LENGTH_SHORT).show();
            return;
        }else if(qty.isEmpty()){
            Toast.makeText(this, "Jumlah produk jangan kosong", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());

        binding.pgDetailProduk.setVisibility(View.VISIBLE);

        String uid = String.valueOf(System.currentTimeMillis());
        Map<String, Object> datas = new HashMap<>();
        datas.put("checkoutId", uid);
        datas.put("productId", model.getProdukId());
        datas.put("alamat", alamat);
        datas.put("namalengkap", namaLengkap);
        datas.put("notelpon", notelp);
        datas.put("qty", Integer.parseInt(qty));
        datas.put("namaproduk", model.getNamaProduk());
        datas.put("harga", model.getPriceProduk()*Integer.parseInt(qty));
        datas.put("dp", model.getImageProduk());
        datas.put("dateorder", currentDateandTime);

        FirebaseFirestore
                .getInstance()
                .collection("order")
                .document(uid)
                .set(datas)
                .addOnCompleteListener(task-> {
                    if(task.isSuccessful()){
                        showSuccessDialog();
                    }else{
                        showFailureDialog();
                    }
                });
    }

    private void showFailureDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Failure Checkout Produk")
                .setMessage("Anda gagal melakukan order produk")
                .setIcon(R.drawable.ic_baseline_clear_24)
                .setPositiveButton("Oke", ((dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    onBackPressed();
                })
                ).show();
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Success Checkout Produk")
                .setMessage("Anda telah sukses melakukan order produk")
                .setIcon(R.drawable.ic_baseline_check_circle_outline_24)
                .setPositiveButton("Oke", ((dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    onBackPressed();
                })
                ).show();

    }
}