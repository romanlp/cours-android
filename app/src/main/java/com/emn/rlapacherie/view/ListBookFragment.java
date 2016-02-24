package com.emn.rlapacherie.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<Book> books;
    private BookFragmentEvent listener;

    public ListBookFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // cast context to listener
        listener = (BookFragmentEvent) context;
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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new BookRecycledAdapter(books);
        mRecyclerView.setAdapter(mAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = mRecyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && mGestureDetector.onTouchEvent(e)){
                    Book b = books.get(mRecyclerView.getChildAdapterPosition(child));
                    listener.onBookSelected(b);
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        return view;
    }

    private void loadBooks() {

        // Plant logger cf. Android Timber
        Timber.plant(new Timber.DebugTree());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://henri-potier.xebia.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService service = retrofit.create(BookService.class);

        Call<List<Book>> call = service.listBooks();

        call.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Response<List<Book>> response, Retrofit retrofit) {
                // TODO success
                for(Book b : response.body()) {
                    Timber.i("Book : " + b.getTitle());
                    books.add(b);
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Throwable t) {
                Timber.e(t, "Failure !");
            }
        });

    }
}
