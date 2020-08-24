package com.theta.location.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.theta.location.R;
import com.theta.location.databinding.ActivitySplashBinding;
import com.theta.location.utils.PrefsUtil;
import com.theta.location.utils.Utils;
import com.theta.location.viewmodel.SplashViewModel;

/**
 * Splash Screen
 * <p>
 * Pruthviraj Gohil
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySplashBinding binding = DataBindingUtil.setContentView(getActivity(), R.layout.activity_splash);
        SplashViewModel splashViewModel = ViewModelProviders.of(getActivity()).get(SplashViewModel.class);
        binding.setSplashModel(splashViewModel);
//        splashViewModel.getVersion(getActivity());


//         Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PrefsUtil.with(getActivity()).readBoolean(Utils.IS_LOGIN)) {
                    Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(homeIntent);
                    finish();
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                } else {
                    Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                    loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                    finish();
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                }
            }
        }, 2000);

    }

    /**
     * Get Current Activity
     *
     * @return
     */
    private SplashActivity getActivity() {
        return SplashActivity.this;
    }
}