package com.notesmates.Fragments;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.notesmates.LoginActivity;
import com.notesmates.MainActivity;
import com.notesmates.MainProfile;
import com.notesmates.R;


@SuppressLint("NewApi")
public class HomeFragment extends Fragment {
	
	Button btnLogout;
    Button btnEnterPro;
    Button btnSeledtClass;
	
	public HomeFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        btnLogout = (Button) rootView.findViewById(R.id.logout);
        btnEnterPro = (Button)  rootView.findViewById(R.id.enterprofile);
        btnSeledtClass = (Button) rootView.findViewById(R.id.seledtcourse);
        
        
        
        
 

        /**
         * Change Password Activity Started
         **/

       /**
        *LogoutFragment from the User Panel which clears the data in Sqlite database
        **/
        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
            startActivityForResult(myIntent, 0);
            }});

        
        btnEnterPro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            Intent myIntent = new Intent(view.getContext(), MainProfile.class);
            startActivityForResult(myIntent, 0);
            }});

        
        btnSeledtClass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            Intent myIntent = new Intent(view.getContext(), MainActivity.class);
            startActivityForResult(myIntent, 0);
           
            }});     
         
        return rootView;
    }
}
