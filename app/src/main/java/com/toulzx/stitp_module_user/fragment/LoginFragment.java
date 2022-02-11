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

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.toulzx.stitp_module_user.MyViewModel;
import com.toulzx.stitp_module_user.R;
import com.toulzx.stitp_module_user.entity.Data;
import com.toulzx.stitp_module_user.entity.User;
import com.toulzx.stitp_module_user.utils.SPHelper;
import com.toulzx.stitp_module_user.utils.ToastUtil;

import java.util.List;

public class LoginFragment extends Fragment {
    private EditText etUserName, etPassword;
    private Button bt;
    private TextView tvRegister;

    private MyViewModel myViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {}
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Test Status. If true, navigate directly
        if (SPHelper.getStatus().equals("true")){
            NavController navController = Navigation.findNavController(requireView());
//            navController.navigate(R.id.action_loginFragment_to_dataFragment);
            navController.navigate(R.id.action_loginFragment_to_shotFragment);
        }
        // bind
        FragmentActivity activity = requireActivity();
        etUserName = activity.findViewById(R.id.et_login_username);
        etPassword = activity.findViewById(R.id.et_login_password);
        bt = activity.findViewById(R.id.bt_login);
        tvRegister = activity.findViewById(R.id.tv_login_to_register);
        // textWatcher
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String userNameText = etUserName.getText().toString().trim();
                String passwordText = etPassword.getText().toString().trim();
                bt.setEnabled(!userNameText.isEmpty() && !passwordText.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
        // initialize
        bt.setEnabled(false);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        // observer
        myViewModel.getAllUsersLive().observe(requireActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                // if database is empty, initialize database, test needed
                if (users.size() == 0) {
                    for (int i = 0; i < usersNames.length; i++) {
                        myViewModel.insertUser(new User(usersNames[i], passwords[i]));
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
                        myViewModel.insertData(new Data(
                                owners[i], timeTexts[i], cupboardTexts[i], cableIndexTexts[i], cableContentTexts[i]
                        ));
                    }
                }
            }
        });
        // listener
        etUserName.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = etUserName.getText().toString().trim();
                String passwordText = etPassword.getText().toString().trim();
                // Search them in database
                User tempUser = myViewModel.getUser(usernameText);
                if (tempUser == null) {
                    ToastUtil.toastShort(requireContext(), "查无此人！");
                } else {
                    // Verify Password
                    if (passwordText.equals(tempUser.getPassword())) {
                        // navigate
                        NavController navController = Navigation.findNavController(v);
//                        navController.navigate(R.id.action_loginFragment_to_dataFragment);
                        navController.navigate(R.id.action_loginFragment_to_shotFragment);
                        // Set Login Status
                        SPHelper.setValue(String.valueOf(tempUser.getId()),
                                tempUser.getUsername(),
                                tempUser.getPassword(),
                                null,   // TODO: set it later
                                "true",
                                null,   // TODO: set it later
                                null);  // TODO: set it later
                    } else {
                        ToastUtil.toastShort(requireContext(), "密码错误！");
                    }
                }
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // navigate
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    private String[] usersNames = {
            "tou",
            "lzx",
    };
    private String[] passwords = {
            "111",
            "111"
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

}