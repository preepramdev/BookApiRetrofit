package com.pram.bookapiretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pram.bookapiretrofit.R;
import com.pram.bookapiretrofit.fragment.AddFragment;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initInstances();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, AddFragment.newInstance())
                    .commit();
        }
    }

    private void initInstances() {

    }
}
