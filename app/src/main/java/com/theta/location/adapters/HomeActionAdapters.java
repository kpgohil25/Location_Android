package com.theta.location.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.theta.location.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Set  View pager data
 */
public class HomeActionAdapters extends FragmentPagerAdapter {

    private Context context;
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private Drawable myDrawable;
    private String title;

    public HomeActionAdapters(Context context, FragmentManager manager) {
        super(manager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }


    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                myDrawable = context.getResources().getDrawable(R.drawable.ic_action_email);
                title = mFragmentTitleList.get(position);
                break;
            case 1:
                myDrawable = context.getResources().getDrawable(R.drawable.ic_action_email);
                title = mFragmentTitleList.get(position);
                break;
            case 2:
                myDrawable = context.getResources().getDrawable(R.drawable.ic_action_email);
                title = mFragmentTitleList.get(position);
                break;
        }
//
//        SpannableStringBuilder sb = new SpannableStringBuilder("   " + title); // space added before text for convenience
//
//        try {
//            myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
//            ImageSpan span = new ImageSpan(myDrawable, DynamicDrawableSpan.ALIGN_BASELINE);
//            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        } catch (Exception e) {
//            // TODO: handle exception
//        }

        return title;
    }

}
