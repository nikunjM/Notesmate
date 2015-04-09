package com.notesmates;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.notesmates.library.DatabaseHandler;

public class MainProfile extends BaseActivity {
	
	 protected static final Intent MainActivity = null;
	 	Button btncourse1;
	    Button btncourse2;
	    Button btncourse3;
	    Button btnLogout;
	    Button changepas;
	    Button btnEnterPro;
	    Button btnSeledtClass;
	    Bundle extras;
	    String username;
	    
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainprofile);
		
		 
		 btncourse1 = (Button) findViewById(R.id.course1);
	     btncourse2 = (Button) findViewById(R.id.course2);
	     btncourse3 = (Button)  findViewById(R.id.course3);
	     
	     extras = getIntent().getExtras();
	     username = extras.getString("username");

	        btnLogout = (Button) findViewById(R.id.logout);
	       // btnEnterPro = (Button)  findViewById(R.id.enterprofile);
	        btnSeledtClass = (Button) findViewById(R.id.seledtcourse);
	        
	     

	       /**
	        *LogoutFragment from the User Panel which clears the data in Sqlite database
	        **/
	        btnLogout.setOnClickListener(new View.OnClickListener() {

	            public void onClick(View arg0) {

	                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
	                login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                startActivity(login);
	                finish();
	            }
	        });
	        
	        /**
	         *Enter profile from the Main page which directs user to main profile .
	         **/
	        btnSeledtClass.setOnClickListener(new View.OnClickListener() {

	            public void onClick(View arg0) {

	                Intent seledtcourse = new Intent(getApplicationContext(), Select_courses.class);
	                seledtcourse.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                startActivity(seledtcourse);
	                finish();
	            }
	        });
	     
	   
	        String Course1txt=extras.getString("course1");
	        String Course2txt=extras.getString("course2");
	        String Course3txt=extras.getString("course3");
	        
	        
	         btncourse1.setText(Course1txt);
	         btncourse2.setText(Course2txt);
	         btncourse3.setText(Course3txt);
	         
	         /**
	          *Enter Course1 homepage
	          **/
	         btncourse1.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View view) {
	             Intent myIntent = new Intent(view.getContext(), MainActivity.class);
	             startActivityForResult(myIntent, 0);
	             finish();
	             }});

	         
	         btncourse2.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View view) {
	             Intent myIntent = new Intent(view.getContext(), MainActivity.class);
	             startActivityForResult(myIntent, 0);
	             finish();
	             }});

	         
	         btncourse3.setOnClickListener(new View.OnClickListener() {
	             public void onClick(View view) {
	             Intent myIntent = new Intent(view.getContext(), MainActivity.class);
	             startActivityForResult(myIntent, 0);
	             finish();
	             }});
	         



	}

}
