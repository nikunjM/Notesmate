package com.notesmates;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.notesmates.library.DatabaseHandler;


public class StartScreen extends Activity {

    Button btnLogin;
    Button Btnregister;
    EditText inputEmail;
    EditText inputPassword;
    
    private TextView loginErrorMsg;
    /**
     * Called when the activity is first created.
     */
    private static String KEY_SUCCESS = "success";
    private static String KEY_UID = "uid";
    private static String KEY_USERNAME = "uname";
    private static String KEY_FIRSTNAME = "fname";
    private static String KEY_LASTNAME = "lname";
    private static String KEY_EMAIL = "email";
    private static String KEY_CREATED_AT = "created_at";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Btnregister = (Button) findViewById(R.id.registerbtn);
        btnLogin = (Button) findViewById(R.id.login);
    
        
    //    passreset.setOnClickListener(new View.OnClickListener() {
    //    public void onClick(View view) {
    //    Intent myIntent = new Intent(view.getContext(), MainActivity.class);
    //    startActivityForResult(myIntent, 0);
    //    finish();
    //    }});
        
        Btnregister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), RegActivity.class);
                startActivityForResult(myIntent, 0);
                finish();
             }});

/**
 * Login button click event
 * A Toast is set to alert when the Email and Password field is empty
 **/
        btnLogin.setOnClickListener(new View.OnClickListener() {

        	  public void onClick(View view) {
                  Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
                  startActivityForResult(myIntent, 0);
                  finish();
               }});
               
               
    }
    
}

              




   