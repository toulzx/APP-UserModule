package com.toulzx.stitp_module_user.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.toulzx.stitp_module_user.MyViewModel;
import com.toulzx.stitp_module_user.R;
import com.toulzx.stitp_module_user.adpter.DataAdapter;
import com.toulzx.stitp_module_user.entity.Data;
import com.toulzx.stitp_module_user.entity.User;
import com.toulzx.stitp_module_user.utils.SPHelper;

import java.util.List;
import java.util.stream.Collectors;

public class dataFragment extends Fragment {
    private TextView tvUserName, tvCount ,tvLogout;
    private RecyclerView recyclerView;

    private MyViewModel myViewModel;
    private DataAdapter dataAdapter;

    public dataFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // bind
        FragmentActivity activity = requireActivity();
        tvUserName = activity.findViewById(R.id.tv_data_username);
        tvCount = activity.findViewById(R.id.tv_data_count);
        tvLogout = activity.findViewById(R.id.tv_data_logout);
        // initialize
        tvUserName.setText(SPHelper.getUserName());
        // viewModel
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // adapter
        dataAdapter = new DataAdapter(myViewModel);
        // recyclerView
        recyclerView = requireActivity().findViewById(R.id.rv_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(dataAdapter);
        // observer
        myViewModel.getAllDataLive().observe(requireActivity(), new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                // if database is updated, notify
                if (dataAdapter.getItemCount() != data.size()) {
                    dataAdapter.notifyDataSetChanged();
                }
                // reset the recyclerView
                List<Data> selectedData = myViewModel.loadDataByUserName(SPHelper.getUserName());
                dataAdapter.setDataFromUser(selectedData);
                // reset the count
                tvCount.setText("Counts: " + selectedData.size());
            }
        });
        // listener
        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set Status and navigate
                SPHelper.setValue(null, null, null, null, "false", null, null);
                // navigate
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_dataFragment_to_loginFragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false);
    }
}