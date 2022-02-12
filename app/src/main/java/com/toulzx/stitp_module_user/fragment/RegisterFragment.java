package com.toulzx.stitp_module_user.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.toulzx.stitp_module_user.MyViewModel;
import com.toulzx.stitp_module_user.R;
import com.toulzx.stitp_module_user.entity.User;
import com.toulzx.stitp_module_user.utils.SPHelper;
import com.toulzx.stitp_module_user.utils.ToastUtil;

public class RegisterFragment extends Fragment {
    private EditText etUserName, etPassword;
    private Button bt;

    private MyViewModel myViewModel;

    public RegisterFragment() {
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
        etUserName = activity.findViewById(R.id.et_register_username);
        etPassword = activity.findViewById(R.id.et_register_password);
        bt = activity.findViewById(R.id.bt_register);
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
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        bt.setEnabled(false);
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
                    // Add new User to database
                    User newUser = new User(usernameText, passwordText);
                    myViewModel.insertUser(newUser);
                    // Set Login Status
                    SPHelper.setValue(String.valueOf(newUser.getId()),
                            usernameText,
                            passwordText,
                            null,
                            "true");
                    // navigate
                    NavController navController = Navigation.findNavController(v);
                    navController.navigate(R.id.action_registerFragment_to_dataFragment);
                } else {
                    ToastUtil.toastShort(requireContext(), "用户名已被占用！");
                }
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}