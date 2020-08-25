package com.theta.location.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
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

    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityLoginBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_login);
        LoginViewModel loginViewModel = ViewModelProviders.of(getActivity()).get(LoginViewModel.class);
        binding.setLoginModel(loginViewModel);

        loginViewModel.loginAuthentication = this;

        edtPassword = findViewById(R.id.edtPassword);

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