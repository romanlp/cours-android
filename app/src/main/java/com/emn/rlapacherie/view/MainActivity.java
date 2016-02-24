package com.emn.rlapacherie.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.emn.rlapacherie.R;
import com.emn.rlapacherie.model.Book;
import com.emn.rlapacherie.model.BookService;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements BookFragmentEvent{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new ListBookFragment())
                .commit();

    }

    @Override
    public void onBookSelected(Book book) {
        DetailsBookFragment newFragment = new DetailsBookFragment();
        Bundle args = new Bundle();
        args.putParcelable("BOOK", book);
        newFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .addToBackStack(ListBookFragment.class.getSimpleName())
                .commit();
    }



}

interface BookFragmentEvent {

    public void onBookSelected(Book book);
}
