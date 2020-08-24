package com.theta.location.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AlertDialog;

import com.theta.location.R;

/**
 * Connection Status
 * <p>
 * Pruthviraj Gohil
 * <p>
 * Purpose : Check Connection Status
 */
public class ConnectionCheck {

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = cm.getActiveNetworkInfo();
            if (ni == null) {
                // There are no active networks.
                return false;
            } else
                return true;
        } else {
            return false;
        }
    }

    /**
     * Show internet connection dialog
     *
     * @param context
     * @return
     */
    public AlertDialog.Builder showConnectionDialog(Context context) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setCancelable(false)
                .setMessage(R.string.internet_error_message)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        builder.show();
        return builder;
    }
}