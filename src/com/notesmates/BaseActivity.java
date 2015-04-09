package com.notesmates;


import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BaseActivity extends Activity {
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {  
	   MenuInflater inflater = getMenuInflater();
	   inflater.inflate(R.menu.login, menu);
	   return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {  
	   switch (item.getItemId()) {
	   case R.id.home:
	      showHome();
	      return true;
	   case R.id.logout:
	      logout();
	      return true;
	   case R.id.back:
		      goBack();
		      return true;
		   
	   }
	     
	   return true;
	}

	private void showHome(){
	   //Show the Home Screen screen 
		 Intent mainprofile = new Intent(getApplicationContext(), MainProfile.class);
         mainprofile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
         startActivity(mainprofile);
         finish();
	}
	
	//Logout of the session and redirect to login screen
	private void logout(){
          Intent login = new Intent(getApplicationContext(), LoginActivity.class);
          login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          startActivity(login);
          finish();
	}
	
	// Back to previous Activity
	private void goBack(){
		Intent home = new Intent(getApplicationContext(), MainActivity.class);
        home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home);
        finish();
		

		}
		

	}
	


