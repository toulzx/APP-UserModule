package com.toulzx.stitp_module_user.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.toulzx.stitp_module_user.MyViewModel;
import com.toulzx.stitp_module_user.R;
import com.toulzx.stitp_module_user.adpter.DataAdapter;
import com.toulzx.stitp_module_user.entity.Data;
import com.toulzx.stitp_module_user.utils.SPHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DataFragment extends Fragment {
    private TextView tvUserName, tvCount ,tvLogout;
    private RecyclerView recyclerView;
    private FloatingActionButton floatBtn;

    private MyViewModel myViewModel;
    private DataAdapter dataAdapter;

    public DataFragment() {
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
        floatBtn = activity.findViewById(R.id.floatBtn_data_insert);
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
                SPHelper.setValue(null, null, null, null, "false");
                // navigate
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_dataFragment_to_loginFragment);
            }
        });
        floatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // current time
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
                // update database
                myViewModel.insertData(new Data(
                        SPHelper.getUserName(),
                        dateFormat.format(new Date()),
                        "cbN",
                        "cI",
                        "nice pair"
                ));
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