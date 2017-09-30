package com.example.ucla201.first_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    Bitmap bitmap;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        focusChange();

        imageView = findViewById(R.id.image);
        if( bitmap != null)
            imageView.setImageBitmap(bitmap);
    }

    public void camButtonClicked(View v){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the bitmap
        Log.d("STATE", "onSaveState");
        outState.putParcelable("IMG", bitmap);
    }


    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("STATE", "onRestoreState");
        bitmap = savedInstanceState.getParcelable("IMG");
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    //CLEAR BUTTON
    public void clearButtonClicked(View v){
        EditText x = findViewById(R.id.editHandle);
        String y = x.toString();
        if (y != "") x.setText("", TextView.BufferType.EDITABLE);

        EditText x2 = findViewById(R.id.editName);
        String y2 = x2.toString();
        if (y2 != "") x2.setText("", TextView.BufferType.EDITABLE);

        EditText x3 = findViewById(R.id.editPassword);
        String y3 = x3.toString();
        if (y3 != "") x3.setText("", TextView.BufferType.EDITABLE);

    }





    //trying to figure out password check

    //last other thing added was line in editPassword for calling this function upon change in
    // focus...unsure how this all works tg...



    public void passwordCheck() {
        EditText verify = findViewById(R.id.passCheckEdit);
        if (password == verify.toString()) {
            //destroy the dialog
        }
        else return;
    }


    void showDialog() {
        DialogFragment passCheck = new DialogFragment();
        //somehow siphon from xml for dialog
        passCheck.show(getFragmentManager(), "dialog");
    }


    public void focusChange() {

        final EditText pass1 = findViewById(R.id.editPassword);

        pass1.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    EditText t = findViewById(R.id.editPassword);
                    password = t.toString();
                    showDialog();
                }
            }
        });
    }













}
