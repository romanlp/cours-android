package com.emn.rlapacherie.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.emn.rlapacherie.R;
import com.emn.rlapacherie.model.Book;

import java.util.List;

/**
 * Created by roman on 22/01/2016.
 */
public class BookRecycledAdapter extends RecyclerView.Adapter<BookRecycledAdapter.ViewHolder> {

    private List<Book> books;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView name;
        public ImageView image;
        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.textViewListItemName);
            image = (ImageView) v.findViewById(R.id.imageViewListItem);
        }
    }

    public BookRecycledAdapter(List<Book> b) {
        books = b;
    }

    @Override
    public BookRecycledAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_book_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BookRecycledAdapter.ViewHolder holder, int position) {

        holder.name.setText(this.books.get(position).getTitle());

        Glide.with(holder.image.getContext())
        .load(this.books.get(position).getCover())
        .centerCrop()
        .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

}
