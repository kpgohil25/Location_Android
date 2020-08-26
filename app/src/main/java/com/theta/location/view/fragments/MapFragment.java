package com.theta.location.view.fragments;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.theta.location.R;
import com.theta.location.databinding.FragmentMapBinding;
import com.theta.location.utils.CurrentLocationListener;
import com.theta.location.utils.Utils;

/**
 * Map Fragment
 * <p>
 * Pruthviraj Gohil
 * <p>
 * Purpose : Get Current Location and View on Map
 */
public class MapFragment extends Fragment{

    private View view;
    private FragmentMapBinding binding;
    private double latitude, longitude;

    /**
     * Default Constructor
     */
    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map, container, false);
        view = binding.getRoot();
        getCurrentLocation();

        return view;
    }

    @BindingAdapter("app:latLong")
    public static void bindLocationToMap(MapView mapView, LatLng latLong) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLong, 10);
    }

    /**
     * Current Location
     */
    private void getCurrentLocation() {

        CurrentLocationListener.getInstance(getActivity()).observe(this, new Observer<Location>() {
            @Override
            public void onChanged(@Nullable Location location) {
                Log.e("MAP", "location" + location);
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                binding.txtLat.setText(getResources().getString(R.string.txt_lat) + Utils.getDecimalValue(location.getLatitude()));
                binding.txtLng.setText(getResources().getString(R.string.txt_lng) + Utils.getDecimalValue(location.getLongitude()));
            }
        });
    }
}
