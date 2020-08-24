package com.theta.location.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.theta.location.utils.Utils;

public class SplashViewModel extends ViewModel {

    public String version = "";

    public String getVersion(Context context) {
        version = Utils.getApplicationVersionCode(context);
        return version;
    }
}
