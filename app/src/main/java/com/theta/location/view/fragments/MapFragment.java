package com.theta.location.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.theta.location.R;

/**
 * Map Fragment
 * <p>
 * Pruthviraj Gohil
 * <p>
 * Purpose : View Location And Map
 */
public class MapFragment extends Fragment {

    private View view;

    /**
     * Default Constructor
     */
    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, null);

        return view;
    }
}
