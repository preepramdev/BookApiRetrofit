package com.pram.bookapiretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pram.bookapiretrofit.R;
import com.pram.bookapiretrofit.fragment.UpdateFragment;
import com.pram.bookapiretrofit.model.Book;

public class UpdateActivity extends AppCompatActivity {

    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initInstances();

        if (getIntent() != null) {
            book = getIntent().getParcelableExtra("book");
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, UpdateFragment.newInstance(book))
                    .commit();
        }
    }

    private void initInstances() {

    }
}
