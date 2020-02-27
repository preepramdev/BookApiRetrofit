package com.pram.bookapiretrofit.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.pram.bookapiretrofit.R;

public class DismissTwoButtonDialogFragment extends DialogFragment {
    private static final String KEY_MESSAGE = "key_message";
    private static final String KEY_POSITIVE = "key_positive";
    private static final String KEY_NEGATIVE = "key_negative";

    private TextView tvMessage;
    private Button btnPositive;
    private Button btnNegative;

    private String message;
    private String positive;
    private String negative;

    public interface OnDialogListener {
        void onDismissTwoButtonPositiveClick();

        void onDismissTwoButtonNegativeClick();
    }

    public static DismissTwoButtonDialogFragment newInstance(String message, String positive, String negative) {
        DismissTwoButtonDialogFragment fragment = new DismissTwoButtonDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_POSITIVE, positive);
        bundle.putString(KEY_NEGATIVE, negative);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            restoreArguments(getArguments());
        } else {
            restoreInstanceState(savedInstanceState);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_two_button, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupView();
    }

    private void bindView(View view) {
        tvMessage = view.findViewById(R.id.tvMessage);
        btnPositive = view.findViewById(R.id.btnSubmit);
        btnNegative = view.findViewById(R.id.btnNegative);
    }

    private void setupView() {
        tvMessage.setText(message);
        btnPositive.setText(positive);
        btnNegative.setText(negative);

        btnPositive.setOnClickListener(v -> {
            OnDialogListener listener = getOnDialogListener();
            if (listener != null) {
                listener.onDismissTwoButtonPositiveClick();
            }
            dismiss();
        });

        btnNegative.setOnClickListener(v -> {
            OnDialogListener listener = getOnDialogListener();
            if (listener != null) {
                listener.onDismissTwoButtonNegativeClick();
            }
            dismiss();
        });
    }

    private OnDialogListener getOnDialogListener() {
        Fragment fragment = getParentFragment();
        try {
            if (fragment != null) {
                return (OnDialogListener) fragment;
            } else {
                return (OnDialogListener) getActivity();
            }
        } catch (ClassCastException ignored) {
        }
        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_MESSAGE, message);
        outState.putString(KEY_POSITIVE, positive);
        outState.putString(KEY_NEGATIVE, negative);
    }

    private void restoreInstanceState(Bundle bundle) {
        message = bundle.getString(KEY_MESSAGE);
        positive = bundle.getString(KEY_POSITIVE);
        negative = bundle.getString(KEY_NEGATIVE);
    }

    private void restoreArguments(Bundle bundle) {
        message = bundle.getString(KEY_MESSAGE);
        positive = bundle.getString(KEY_POSITIVE);
        negative = bundle.getString(KEY_NEGATIVE);
    }

    public static class Builder {
        private String message;
        private String positive;
        private String negative;

        public Builder() {
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setPositive(String positive) {
            this.positive = positive;
            return this;
        }

        public Builder setNegative(String negative) {
            this.negative = negative;
            return this;
        }

        public DismissTwoButtonDialogFragment build() {
            return DismissTwoButtonDialogFragment.newInstance(message, positive, negative);
        }
    }
}
