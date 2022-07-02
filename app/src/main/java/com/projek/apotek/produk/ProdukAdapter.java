package com.projek.apotek.produk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projek.apotek.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ProdukAdapter extends RecyclerView.Adapter<ProdukAdapter.ViewHolder> {

    private final ArrayList<ProdukModel> listProduct = new ArrayList<>();

    public void setData(ArrayList<ProdukModel> items) {
        listProduct.clear();
        listProduct.addAll(items);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_produk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout cv;
        TextView namaProduk, hargaProduk;
        ImageView dp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            namaProduk = itemView.findViewById(R.id.name);
            hargaProduk = itemView.findViewById(R.id.tv_harga);
            dp = itemView.findViewById(R.id.dp);
        }


        @SuppressLint("SetTextI18n")
        public void bind(ProdukModel model) {
            NumberFormat formatter = new DecimalFormat("#,###");

            Glide.with(itemView.getContext())
                    .load(model.getImageProduk())
                    .into(dp);
            namaProduk.setText(model.getNamaProduk());
            hargaProduk.setText("IDR "+ formatter.format((model.getPriceProduk())));


            cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailProdukActivity.class);
                    intent.putExtra(DetailProdukActivity.EXTRA_PRODUK, model);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
