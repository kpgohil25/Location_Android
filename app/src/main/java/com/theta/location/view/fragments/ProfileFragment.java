package com.theta.location.view.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.theta.location.R;
import com.theta.location.databinding.FragmentProfileBinding;
import com.theta.location.utils.PrefsUtil;
import com.theta.location.utils.Utils;
import com.theta.location.view.LoginActivity;

/**
 * Profile Fragment
 * <p>
 * Pruthviraj Gohil
 * <p>
 * Purpose : View Profile
 */
public class ProfileFragment extends Fragment {

    private View view;

    /**
     * Default Constructor
     */
    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentProfileBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_profile, container, false);
        view = binding.getRoot();

        TextView txtEmail = binding.txtEmail;
        txtEmail.setText(PrefsUtil.with(getActivity()).readString(Utils.USER_NAME));

        Button llLogout = binding.llLogout;
        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutCall();
            }
        });

        return view;
    }

    /**
     * Logout Call
     */
    public void logoutCall() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity(), R.style.AlertDialogStyle);
        alertDialogBuilder.setTitle(getResources().getString(R.string.app_name));
        alertDialogBuilder
                .setMessage(getResources().getString(R.string.exit_question))
                .setCancelable(false)
                .setPositiveButton(getResources().getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                PrefsUtil.with(getActivity()).write(Utils.IS_LOGIN, false);
                                Intent homeIntent = new Intent(getActivity(), LoginActivity.class);
                                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(homeIntent);
                                getActivity().finish();
                                getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                            }
                        })

                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
