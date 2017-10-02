package com.example.ucla201.first_project;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Chris Moorad on 9/29/17.
 */

public class PassCheck extends DialogFragment {

    EditText passVerify;
    View view;
    String p;

    public PassCheck() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_box, container);
        getDialog();
        return view;
    }


    //using previous password (passed in by showDialog method), checks to see if password matches
    public void passwordCheck() {
        passVerify = view.findViewById(R.id.passCheckEdit);
        if (p == passVerify.toString()) {
            this.onDestroy();
        }
        else {
            passVerify.setText("");
            passVerify.setHint("Try again");
        }
    }


}
