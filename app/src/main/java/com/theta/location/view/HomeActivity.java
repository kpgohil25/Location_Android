package com.theta.location.view;

import android.Manifest;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.tabs.TabLayout;
import com.theta.location.R;
import com.theta.location.adapters.HomeActionAdapters;
import com.theta.location.databinding.ActivityHomeBinding;
import com.theta.location.utils.CustomViewPager;
import com.theta.location.view.fragments.HomeFragment;
import com.theta.location.view.fragments.MapFragment;
import com.theta.location.view.fragments.ProfileFragment;

/**
 * Home Activity
 * <p>
 * Pruthviraj
 * <p>
 * Purpose :- Three Different Stage
 * <p>
 * Home:- Display List
 * Map :- Display Location
 * Profile :- Display Profile
 */
public class HomeActivity extends AppCompatActivity {

    private String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    private static final int Permission_Request_Code = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setHandler(this);
        binding.setManager(getSupportFragmentManager());
    }

    /**
     * Set Adapter
     *
     * @param viewPager
     * @param activity
     */
    @BindingAdapter({"bind:handler"})
    public static void bindViewPagerAdapter(final ViewPager viewPager, final HomeActivity activity) {
        HomeActionAdapters adapter = new HomeActionAdapters(activity, activity.getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), activity.getResources().getString(R.string.txt_home));
        adapter.addFragment(new MapFragment(), activity.getResources().getString(R.string.txt_map));
        adapter.addFragment(new ProfileFragment(), activity.getResources().getString(R.string.txt_profile));
        viewPager.setAdapter(adapter);
    }

    /**
     * Bind Custom View Pager
     *
     * @param view
     * @param pagerView
     */
    @BindingAdapter({"bind:pager"})
    public static void bindViewPagerTabs(final TabLayout view, final CustomViewPager pagerView) {
        pagerView.setPagingEnabled(false);
        view.setupWithViewPager(pagerView, true);
    }


    /**
     * Get Current Class Context
     *
     * @return
     */
    private HomeActivity getActivity() {

        return HomeActivity.this;
    }
}