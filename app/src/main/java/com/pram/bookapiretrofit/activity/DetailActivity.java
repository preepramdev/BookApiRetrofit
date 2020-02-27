package com.pram.bookapiretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pram.bookapiretrofit.R;
import com.pram.bookapiretrofit.fragment.DetailFragment;

public class DetailActivity extends AppCompatActivity {

    private int bookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initInstances();

        if (getIntent() != null) {
            bookId = getIntent().getIntExtra("bookId", 0);
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, DetailFragment.newInstance(bookId))
                    .commit();
        }
    }

    private void initInstances() {

    }
}
