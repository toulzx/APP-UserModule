package com.toulzx.stitp_module_user.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.toulzx.stitp_module_user.MyViewModel;
import com.toulzx.stitp_module_user.R;
import com.toulzx.stitp_module_user.adpter.DataAdapter;
import com.toulzx.stitp_module_user.entity.Data;
import com.toulzx.stitp_module_user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class dataFragment extends Fragment {
    private RecyclerView recyclerView;

    private MyViewModel myViewModel;
    private DataAdapter dataAdapter;

    private String[] usersNames = {
            "tou",
            "lzx",
    };
    private String[] owners = {
            "tou",
            "tou",
            "tou",
            "lzx",
            "lzx",
    };
    private String[] timeTexts = {
            "2021-01-01-09-20-41",
            "2021-02-02-10-30-52",
            "2021-03-03-11-40-03",
            "2021-04-04-12-50-14",
            "2021-05-05-13-00-25",
    };
    private String[] cupboardTexts = {
            "1-21UD",
            "1TD",
            "1-1QD",
            "BD",
            "ZD",
    };
    private String[] cableIndexTexts = {
            "2",
            "4",
            "9",
            "31",
            "1",
    };
    private String[] cableContentTexts = {
            "wrong pair",
            "extra pair",
            "missing pair",
            "extra pair",
            "wrong pair",
    };

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
        // viewModel
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // adapter
        dataAdapter = new DataAdapter(myViewModel);
        // recyclerView
        recyclerView = requireActivity().findViewById(R.id.rv_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(dataAdapter);
        // observer
        myViewModel.getAllUsersLive().observe(requireActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // if database is empty, initialize database, test needed
                if (users.size() == 0) {
                    for (int i = 0; i < usersNames.length; i++) {
                        myViewModel.insertUser(new User(usersNames[i]));
                    }
                }
            }
        });
        myViewModel.getAllDataLive().observe(requireActivity(), new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> data) {
                // if database is empty, initialize database, test needed
                if (data.size() == 0) {
                    for (int i = 0; i < timeTexts.length; i++) {
                        myViewModel.insertData(new Data(owners[i], timeTexts[i], cupboardTexts[i], cableIndexTexts[i], cableContentTexts[i]));
                    }
                }
                // if database is updated, notify
                if (dataAdapter.getItemCount() != data.size()) {
                    dataAdapter.notifyDataSetChanged();
                }
                // reset the recyclerView
                List<Data> selectedData = myViewModel.loadDataByUserName(new User("tou")); // test needed
                dataAdapter.setDataFromUser(selectedData);
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