package com.projek.apotek.history;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.projek.apotek.R;
import com.projek.apotek.produk.ProdukAdapter;
import com.projek.apotek.produk.ProdukModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private final ArrayList<HistoryModel> listHistory = new ArrayList<>();

    public void setData(ArrayList<HistoryModel> items) {
        listHistory.clear();
        listHistory.addAll(items);
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        holder.bind(listHistory.get(position));
    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tglOrderTV, namaProduk, qtyProduk, pemesan, totalOrder;
        ImageView imgview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tglOrderTV = itemView.findViewById(R.id.tglorder);
            namaProduk = itemView.findViewById(R.id.nama_order);
            qtyProduk = itemView.findViewById(R.id.jml_order);
            pemesan = itemView.findViewById(R.id.pemesan);
            totalOrder = itemView.findViewById(R.id.total_order);
            imgview = itemView.findViewById(R.id.img_history);

        }

        public void bind(HistoryModel historyModel) {
            NumberFormat numberFormat = new DecimalFormat("#,###");
            Glide.with(itemView.getContext())
                    .load(historyModel.getDp())
                    .into(imgview);
            tglOrderTV.setText(historyModel.getDateOrder());
            namaProduk.setText(historyModel.getNamaProduk());
            qtyProduk.setText("Quantity : " + historyModel.getQty());
            pemesan.setText("Pemesan : "+historyModel.getNamaLengkap());
            totalOrder.setText("Total : Rp. "+numberFormat.format(Double.parseDouble(historyModel.getHarga())));
        }
    }
}
