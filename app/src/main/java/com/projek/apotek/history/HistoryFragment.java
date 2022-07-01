package com.projek.apotek.history;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projek.apotek.R;
import com.projek.apotek.databinding.FragmentHistoryBinding;


public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private HistoryAdapter adapter;


    @Override
    public void onResume() {
        super.onResume();
        initViewModel();
        initRecyclerView();
    }

    private void initRecyclerView() {
        binding.rvOrder.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HistoryAdapter();
        binding.rvOrder.setAdapter( adapter);
    }

    private void initViewModel() {
        HistoryViewModel viewModel = new ViewModelProvider(this).get(HistoryViewModel.class);
        binding.pbOrder.setVisibility(View.VISIBLE);
        viewModel.setListHistory();
        viewModel.getHistoryList().observe(this, historyModels -> {
            if(historyModels.size() > 0){
                adapter.setData(historyModels);
            }else {
                binding.noData.setVisibility(View.VISIBLE);
            }
            binding.pbOrder.setVisibility(View.GONE);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}