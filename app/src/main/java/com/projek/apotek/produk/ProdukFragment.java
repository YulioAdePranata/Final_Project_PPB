package com.projek.apotek.produk;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.projek.apotek.R;
import com.projek.apotek.databinding.FragmentProdukBinding;

public class ProdukFragment extends Fragment {

    private FragmentProdukBinding binding;
    private ProdukAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProdukBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecylerView();
        initViewModel();
    }

    private void initViewModel() {
        ProdukViewModel viewModel = new ViewModelProvider(this).get(ProdukViewModel.class);

        binding.progressBar.setVisibility(View.VISIBLE);
        viewModel.setListProduct();
        viewModel.getProductList().observe(this, camera -> {
            if (camera.size() > 0) {
                binding.noData.setVisibility(View.GONE);
                adapter.setData(camera);
            } else {
                binding.noData.setVisibility(View.VISIBLE);
            }
            binding.progressBar.setVisibility(View.GONE);
        });
    }

    private void initRecylerView() {
        binding.rvProduct.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new ProdukAdapter();
        binding.rvProduct.setAdapter(adapter);
    }
}