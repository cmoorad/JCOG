package com.example.ucla201.first_project;



/*

Christopher Moorad and Jack Taylor
CS 65; 17F
Lab 1
Group: JCOG

 */



//Many of these imports are unnecessary, but I call them so I can retrace my steps
// (where example code came from) for future assignments

import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

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
import java.util.Set;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageView;
    Bitmap bitmap;
    String password;
    boolean existingAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        focusChange();

        imageView = findViewById(R.id.image);
        if (bitmap != null) imageView.setImageBitmap(bitmap);




        Button load = findViewById(R.id.button);

        SharedPreferences preferences;
        preferences = getSharedPreferences("text", 0);

        //New attempt at setting Load button using boolean to check for existing data
        String value = preferences.getString("y1",null);

        if (value == "") {
            existingAccount = false;
        } else {
            load.setText("Load");
            existingAccount = true;
        }



        //First attempt at setting Load button
        /*
        if (!getPreferences(MODE_PRIVATE).getString("y1","").equals("")) {
            load = findViewById(R.id.button);
            load.setText("Load");
            existingAccount = false;
        }
        else {

            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("y1", "");
            editor.apply();

            existingAccount = true;

        }
        */


    }



    //THIS AND NEXT FOUR ARE CAMERA FUNCTIONS
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





    //CLEAR simultaneously LOAD BUTTON
    public void clearLoadButtonClicked(View v){

        Button clearLoad = findViewById(R.id.button);


        //From first attempt...
        //if !clearLoad.getText().equals("Load")


        //If there is no data to load, it is a clear button
        if (!existingAccount) {

            EditText x = findViewById(R.id.editHandle);
            String y = x.toString();
            if (!y.equals("")) x.setText("", TextView.BufferType.EDITABLE);
            EditText x2 = findViewById(R.id.editName);
            String y2 = x2.toString();
            if (!y2.equals("")) x2.setText("", TextView.BufferType.EDITABLE);

            EditText x3 = findViewById(R.id.editPassword);
            String y3 = x3.toString();
            if (!y3.equals("")) x3.setText("", TextView.BufferType.EDITABLE);
        }

        //if there is data to load, it's the load button
        else {
            SharedPreferences sp;
            sp = this.getPreferences(MODE_PRIVATE);

            String y1 = sp.getString("y1", "");
            EditText x1 = findViewById(R.id.editHandle);
            x1.setText(y1, TextView.BufferType.EDITABLE);

            String y2 = sp.getString("y2", "");
            EditText x2 = findViewById(R.id.editName);
            x2.setText(y2, TextView.BufferType.EDITABLE);

            String y3 = sp.getString("y3", "");
            EditText x3 = findViewById(R.id.editPassword);
            x3.setText(y3, TextView.BufferType.EDITABLE);

            clearLoad.setText("Clear");
        }

    }





    //calls the dialog fragment instance to be shown
    void showDialog() {
        FragmentManager fm = getSupportFragmentManager();
        PassCheck passCheck = new PassCheck();
        passCheck.show(fm, "dialog_box");
        passCheck.p = password;
    }


    //CALLS PASSWORD-CHECK DIALOG to show (above) - called in onCreate to instantiate listener
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



//called on Save Click
    public void saveData(View v) {

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        EditText x = findViewById(R.id.editHandle);
        String y1 = x.toString();

        EditText x2 = findViewById(R.id.editName);
        String y2 = x2.toString();

        EditText x3 = findViewById(R.id.editPassword);
        String y3 = x3.toString();

        editor.putString("y1", y1);
        editor.putString("y2", y2);
        editor.putString("y3", y3);
        editor.apply();

    }


}
