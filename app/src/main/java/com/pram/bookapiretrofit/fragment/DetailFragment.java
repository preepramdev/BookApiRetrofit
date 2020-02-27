package com.pram.bookapiretrofit.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pram.bookapiretrofit.R;
import com.pram.bookapiretrofit.activity.UpdateActivity;
import com.pram.bookapiretrofit.api.callback.RetrofitCallback;
import com.pram.bookapiretrofit.api.controller.BookApiController;
import com.pram.bookapiretrofit.dialog.OneButtonDialogFragment;
import com.pram.bookapiretrofit.dialog.TwoButtonDialogFragment;
import com.pram.bookapiretrofit.manager.Contextor;
import com.pram.bookapiretrofit.model.Book;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class DetailFragment extends Fragment implements
        OneButtonDialogFragment.OnDialogListener,
        TwoButtonDialogFragment.OnDialogListener {
    private static final String TAG = "DetailFragment";
    private Context mContext;
    private View mRootView;
    private BookApiController apiController;
    private Book mBook;
    private int mBookId;

    private TextView tvBookId;
    private TextView tvBookTitle;
    private TextView tvBookAuthor;
    private TextView tvBookPages;
    private Button btnUpdate;
    private Button btnRemove;

    public DetailFragment() {
        super();
    }

    @Override
    public void onOneButtonClick() {
        requireActivity().finish();
    }

    @Override
    public void onTwoButtonPositiveClick() {
        removeBook();
    }

    @Override
    public void onTwoButtonNegativeClick() {

    }

    @SuppressWarnings("unused")
    public static DetailFragment newInstance(int bookId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt("bookId", bookId);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
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

        if (getArguments() != null) {
            mBookId = getArguments().getInt("bookId");
        }

        tvBookId = mRootView.findViewById(R.id.tvBookId);
        tvBookTitle = mRootView.findViewById(R.id.tvBookTitle);
        tvBookAuthor = mRootView.findViewById(R.id.tvBookAuthor);
        tvBookPages = mRootView.findViewById(R.id.tvBookPages);
        btnUpdate = mRootView.findViewById(R.id.btnUpdate);
        btnRemove = mRootView.findViewById(R.id.btnCancel);

        btnUpdate.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, UpdateActivity.class);
            intent.putExtra("book", mBook);
            startActivity(intent);
        });

        btnRemove.setOnClickListener(v -> {
            callTwoButtonDialog("Remove?", "Ok", "Cancel");
        });

//        fetchApi();
    }

    private void fetchApi() {
        apiController.getBook(mBookId, new RetrofitCallback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.e("onSuccess", response.body().toString());
                        mBook = (Book) response.body();
                        if (mBook != null) {
                            String id = mBook.getId().toString();
                            String title = mBook.getTitle();
                            String author = mBook.getAuthor();
                            String page = mBook.getPages();

                            tvBookId.setText(id);
                            tvBookTitle.setText(title);
                            tvBookAuthor.setText(author);
                            tvBookPages.setText(page);
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

    private void removeBook() {
        apiController.removeBook(mBookId, new RetrofitCallback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    Log.e("onEmptyResponse", "Returned empty response");
                    callOneButtonDialog("Removed", "Ok");
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void callOneButtonDialog(String message, String submit) {
        OneButtonDialogFragment fragment = new OneButtonDialogFragment.Builder()
                .setMessage(message)
                .setSubmit(submit)
                .build();
        fragment.show(getChildFragmentManager(), "OneButtonDialogFragment");
    }

    private void callTwoButtonDialog(String message, String positive, String negative) {
        TwoButtonDialogFragment fragment = new TwoButtonDialogFragment.Builder()
                .setMessage(message)
                .setPositive(positive)
                .setNegative(negative)
                .build();
        fragment.show(getChildFragmentManager(), "TwoButtonDialogFragment");
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchApi();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }


    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }
}
