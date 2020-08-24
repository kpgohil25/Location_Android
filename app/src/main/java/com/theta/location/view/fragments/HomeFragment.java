package com.theta.location.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.theta.location.R;

/**
 * Home Fragment
 * <p>
 * Pruthviraj Gohil
 * <p>
 * Purpose : Recharge Fragment
 * <p>
 * -> Make a International Recharge of mobile number
 * -> Manage Country wise
 */
public class HomeFragment extends Fragment {

    private View view;

    /**
     * Default Constructor
     */
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, null);

        return view;
    }
}
