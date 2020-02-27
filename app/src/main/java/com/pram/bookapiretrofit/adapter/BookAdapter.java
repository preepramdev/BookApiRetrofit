package com.pram.bookapiretrofit.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pram.bookapiretrofit.R;
import com.pram.bookapiretrofit.model.Book;
import com.pram.bookapiretrofit.viewholder.BookViewHolder;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private List<Book> mModels;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public BookAdapter(List<Book> mModels) {
        this.mModels = mModels;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book model = mModels.get(position);

        holder.setTvBookId(model.getId().toString());
        holder.setTvBookTitle(model.getTitle());
        holder.setTvBookAuthor(model.getAuthor());
        holder.setTvBookPage(model.getPages());
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }
}
