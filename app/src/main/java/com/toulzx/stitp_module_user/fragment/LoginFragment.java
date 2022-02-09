package com.toulzx.stitp_module_user.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.toulzx.stitp_module_user.R;

public class LoginFragment extends Fragment {
    private EditText etId, etPassword;
    private Button bt;
    private TextView tvRegister;

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
        // bind
        FragmentActivity activity = requireActivity();
        etId = activity.findViewById(R.id.et_login_id);
        etPassword = activity.findViewById(R.id.et_login_password);
        bt = activity.findViewById(R.id.bt_login);
        tvRegister = activity.findViewById(R.id.tv_login_to_register);
        // textWatcher
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String idText = etId.getText().toString().trim();
                String passwordText = etPassword.getText().toString().trim();
//                bt.setEnabled(!idText.isEmpty() && !passwordText.isEmpty());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
        // initialize
        etId.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
//        btLogin.setEnabled(false);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String idText = etId.getText().toString().trim();
//                String passwordText = etPassword.getText().toString().trim();
                // TODO: Search them in database
                // navigate
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_loginFragment_to_dataFragment);
                // TODO: Set Login Status
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
}