package com.emn.rlapacherie.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emn.rlapacherie.R;
import com.emn.rlapacherie.model.Book;
import com.emn.rlapacherie.model.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListBookFragment extends Fragment {

    private static final Random RANDOM = new Random();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Book> books;
    public ListBookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);

        this.books = new ArrayList<>();
        this.loadBooks();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        //mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BookRecycledAdapter(books);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private void loadBooks() {

        // Plant logger cf. Android Timber
        Timber.plant(new Timber.DebugTree());

        // TODO build Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // TODO create a service
        BookService service = retrofit.create(BookService.class);

        // TODO listBooks()
        Call<List<Book>> call = service.listBooks();

        // TODO enqueue call and display book title
        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Response<List<Book>> response, Retrofit retrofit) {
                // TODO success
                for(Book b : response.body()) {
                    Timber.i("Book : " + b.getTitle());
                    books.add(b);
                }
            }
            @Override
            public void onFailure(Throwable t) {
                // TODO error occurred
                Timber.e(t, "Failure !");
            }
        });

    }
}
