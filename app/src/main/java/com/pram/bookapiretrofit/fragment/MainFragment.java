package com.pram.bookapiretrofit.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pram.bookapiretrofit.R;
import com.pram.bookapiretrofit.activity.AddActivity;
import com.pram.bookapiretrofit.activity.DetailActivity;
import com.pram.bookapiretrofit.adapter.BookAdapter;
import com.pram.bookapiretrofit.api.callback.RetrofitCallback;
import com.pram.bookapiretrofit.api.controller.BookApiController;
import com.pram.bookapiretrofit.manager.Contextor;
import com.pram.bookapiretrofit.model.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class MainFragment extends Fragment {
    private static final String TAG = "MainFragment";
    private Context mContext;
    private View mRootView;
    private BookApiController apiController;

    private List<Book> mModels;
    private BookAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView rvBookList;
    private FloatingActionButton fabAdd;

    public MainFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        mContext = Contextor.getInstance().getContext();
        apiController = new BookApiController();
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        mRootView = rootView;
        mModels = new ArrayList();
        mAdapter = new BookAdapter(mModels);
        mLayoutManager = new LinearLayoutManager(mContext);

        rvBookList = mRootView.findViewById(R.id.rvBookList);
        fabAdd = mRootView.findViewById(R.id.fabAdd);

        rvBookList.setLayoutManager(mLayoutManager);
        rvBookList.setHasFixedSize(true);
        rvBookList.setAdapter(mAdapter);

        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AddActivity.class);
            startActivity(intent);
        });

        mAdapter.setOnItemClickListener(position -> {
            Log.e(TAG, "onItemClick: " + position);
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("bookId", mModels.get(position).getId());
            startActivity(intent);
        });

//        fetchApi();
    }

    private void fetchApi() {
        apiController.getBooks(new RetrofitCallback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.e("onSuccess", response.body().toString());

                        List<Book> models = (List<Book>) response.body();

                        if (models != null && models.size() > 0) {
                            mModels.addAll(models);
                            mAdapter.notifyDataSetChanged();
                        }

                    } else {
                        Log.e("onEmptyResponse", "Returned empty response");
                    }
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("onFailure", t.getMessage());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mModels.clear();
        fetchApi();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
    }

}
