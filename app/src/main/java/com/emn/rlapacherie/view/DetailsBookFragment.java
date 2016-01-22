package com.emn.rlapacherie.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emn.rlapacherie.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsBookFragment extends Fragment {

    public DetailsBookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_list, container, false);
    }
}
