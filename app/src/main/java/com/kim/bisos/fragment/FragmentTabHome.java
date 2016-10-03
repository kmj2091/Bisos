package com.kim.bisos.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kim.bisos.R;

public class FragmentTabHome extends Fragment {

    FragmentTabHost mNavFTH;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.fragment_tab_home, container, false);

        mNavFTH = (FragmentTabHost) V.findViewById(R.id.nav_fth);
        mNavFTH.setup(getActivity(), getChildFragmentManager(), R.id.tab_container);

        mNavFTH.addTab(mNavFTH.newTabSpec("tab1").setIndicator("주행전"),
                FragmentTabBefore.class, null);
        mNavFTH.addTab(mNavFTH.newTabSpec("tab2").setIndicator("주행후"),
                FragmentTabAfter.class, null);



        return V;
    }



}