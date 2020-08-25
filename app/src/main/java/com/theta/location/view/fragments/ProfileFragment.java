package com.theta.location.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
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

        PrefsUtil.with(getActivity()).write(Utils.IS_LOGIN, false);
        Intent homeIntent = new Intent(getActivity(), LoginActivity.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(homeIntent);
        getActivity().finish();
        getActivity().overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }
}
