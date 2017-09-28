package com.example.ucla201.first_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


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

public class MainActivity extends AppCompatActivity {

    final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


/*
    public void focusChange(View.OnFocusChangeListener f) {
        Intent changeFocusIntent = new Intent();


    }
*/








}
