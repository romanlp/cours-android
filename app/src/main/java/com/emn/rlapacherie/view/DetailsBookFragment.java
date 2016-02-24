package com.emn.rlapacherie.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emn.rlapacherie.R;
import com.emn.rlapacherie.model.Book;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsBookFragment extends Fragment {

    public DetailsBookFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book_details, container, false);


        Book book = getArguments().getParcelable("BOOK");

        TextView titleView = (TextView) view.findViewById(R.id.details_title);
        titleView.setText(book.getTitle());

        ImageView imgView = (ImageView) view.findViewById(R.id.details_image);
        Glide.with(getContext())
                .load(book.getCover())
                .centerCrop()
                .into(imgView);

        TextView priceView = (TextView) view.findViewById(R.id.details_price);
        priceView.setText("PRIX : "+ book.getPrice());

        return view;
    }
}
