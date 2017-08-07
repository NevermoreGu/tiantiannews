package com.tiantiannews.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tiantiannews.ui.fragment.ArrayListFragment;

/**
 * Created by guqian on 2017/7/28.
 */

public class TestFragmentPagerStateAdapter extends FragmentStatePagerAdapter {

    public TestFragmentPagerStateAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {
        return ArrayListFragment.newInstance(position);
    }
}
