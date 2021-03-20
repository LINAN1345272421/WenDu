package com.example.helloworld.Adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerLIneAdapter extends FragmentPagerAdapter {
    private List<Fragment> viewLists;
    public PagerLIneAdapter(@NonNull FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.viewLists = fragmentList;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return viewLists.get(position);
    }

    @Override
    public int getCount() {
        return viewLists.size();
    }
}
