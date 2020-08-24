package com.theta.location.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.theta.location.R;

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
        view = inflater.inflate(R.layout.fragment_profile, null);

        return view;
    }
}
