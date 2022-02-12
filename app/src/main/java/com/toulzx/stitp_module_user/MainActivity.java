package com.toulzx.stitp_module_user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.toulzx.stitp_module_user.utils.SPHelper;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化 SharedPreferences
        SPHelper.init(getApplication());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 顶部导航栏的返回按钮 UI 设置
        navController = Navigation.findNavController(findViewById(R.id.fragmentContainerView));
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        // 激活部导航栏的返回按钮的功能
        navController.navigateUp();
        // 隐藏软键盘
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.fragmentContainerView).getWindowToken(), 0);

        return super.onSupportNavigateUp();
    }
}