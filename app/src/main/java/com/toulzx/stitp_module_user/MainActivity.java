package com.toulzx.stitp_module_user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.toulzx.stitp_module_user.utils.SPHelper;

public class MainActivity extends AppCompatActivity {
    private NavController navController;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final int REQUEST_CAMERA = 3;

    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取权限
        permissionRequest();
        // 初始化 SharedPreferences
        SPHelper.init(getApplication());
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 顶部导航栏的返回按钮 UI 设置，需配合有顶栏的主题
//        navController = Navigation.findNavController(findViewById(R.id.fragmentContainerView));
//        NavigationUI.setupActionBarWithNavController(this, navController);
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

    /**
     * 动态申请权限
     */
    private void permissionRequest() {
        // 内存读取权限申请
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        // 相机权限申请
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // You can directly ask for the permission.
            requestPermissions(new String[] { Manifest.permission.CAMERA }, REQUEST_CAMERA);
        }

    }

}