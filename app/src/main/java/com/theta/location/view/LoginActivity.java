package com.theta.location.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.theta.location.R;
import com.theta.location.databinding.ActivityLoginBinding;
import com.theta.location.interfaces.LoginAuthentication;
import com.theta.location.utils.LogFile;
import com.theta.location.utils.PrefsUtil;
import com.theta.location.utils.Utils;
import com.theta.location.viewmodel.LoginViewModel;

/**
 * Login Screen
 * <p>
 * <p>
 * Pruthviraj
 * <p>
 * Purpose : Email and Password Authentication
 * This is View Only UI Related Changes Here
 * <p>
 * Here We Can Use Static User name and Password
 * <p>
 * UserName :- maitrey@thetatechnolabs.com
 * Password :- Maitrey@123
 */
public class LoginActivity extends AppCompatActivity implements LoginAuthentication {

    private String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int Permission_Request_Code = 101;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        binding.setLoginModel(loginViewModel);

        loginViewModel.loginAuthentication = this;
    }

    /**
     * Get Current Activity
     *
     * @return
     */
    private LoginActivity getActivity() {
        return LoginActivity.this;
    }

    @Override
    public void onSuccess() {
        LogFile.e("Login Success");

        PrefsUtil.with(getActivity()).write(Utils.IS_LOGIN, true);
        PrefsUtil.with(getActivity()).write(Utils.USER_NAME, "maitrey@thetatechnolabs.com");

        Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        finish();
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    @Override
    public void onFail(String message) {
        PrefsUtil.with(getActivity()).write(Utils.IS_LOGIN, false);
        Utils.showToast(getActivity(), message, false);
    }
}