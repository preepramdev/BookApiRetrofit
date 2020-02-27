package com.pram.bookapiretrofit.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pram.bookapiretrofit.R;

public class BookViewHolder extends RecyclerView.ViewHolder {
    private TextView tvBookId;
    private TextView tvBookTitle;
    private TextView tvBookAuthor;
    private TextView tvBookPage;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        tvBookId = itemView.findViewById(R.id.tvBookId);
        tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
        tvBookAuthor = itemView.findViewById(R.id.tvBookAuthor);
        tvBookPage = itemView.findViewById(R.id.tvBookPages);
    }

    public void setTvBookId(String bookId) {
        tvBookId.setText(bookId);
    }

    public void setTvBookTitle(String bookTitle) {
        tvBookTitle.setText(bookTitle);
    }

    public void setTvBookAuthor(String bookAuthor) {
        tvBookAuthor.setText(bookAuthor);
    }

    public void setTvBookPage(String bookPage) {
        tvBookPage.setText(bookPage);
    }
}
