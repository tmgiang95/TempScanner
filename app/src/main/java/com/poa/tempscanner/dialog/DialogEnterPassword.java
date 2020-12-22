package com.poa.tempscanner.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.poa.tempscanner.R;

public class DialogEnterPassword extends DialogFragment {

    DialogCallback mCallback;

    public static DialogEnterPassword newInstance() {
        DialogEnterPassword login = new DialogEnterPassword();
        login.setStyle(STYLE_NO_TITLE, 0);
        login.setCancelable(false);
        return login;
    }

    public void setCallback(DialogCallback callback) {
        if (mCallback == null) {
            this.mCallback = callback;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
        getDialog().getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public void dismissDialog() {
        this.dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_password, container, false);

        final EditText passwordEditText = view.findViewById(R.id.edtPassword);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnOk = view.findViewById(R.id.btnOk);

        btnOk.setOnClickListener(view12 -> {
            String password = passwordEditText.getText().toString();
            mCallback.onClickOK(password);
            passwordEditText.setText("");
        });

        btnCancel.setOnClickListener(view1 -> mCallback.onClickCancel());

        return view;
    }

}
