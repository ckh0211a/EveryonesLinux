package com.teamsquare.everyoneslinux.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teamsquare.everyoneslinux.R;

/**
 * Created by hongchul on 2019-04-02.
 */

public class FragSearch extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_search, container, false);

        return view;
    }
}
