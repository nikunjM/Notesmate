package com.notesmates;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

// This class focuses on creating game invites 
public class Select_courses extends Activity {

	// Add the preferences
	//public static final String PREFS_NAME = "EventCreationPrefs";

	// Variable declarations
	
	Button createButton;
	String username;
	//String limit;
	ProgressDialog progressDialogEvent;
	int eventCheck = 0;
	JSONParser jsonParserEvent = new JSONParser();
	Button submit;
	Spinner course1Spinner;
	Spinner course2Spinner;
	Spinner course3Spinner;
	public static String filename = "Valustoringfile";
    SharedPreferences SP;
	
	String[] courses = { "-- Choose Course --", "CSE5324", "CSE5345",
			"CSE5311"};
	private static final String COURSE_SUCCESS = "success";
	private static final String EVENT_SUCCESS = "success";
	String chosencourse1 = "test1";
	String chosencourse2 = "test2";
	String chosencourse3 = "test3";
	
	ArrayAdapter<String> adapter;
	ArrayAdapter<String> adapter1;
	ArrayAdapter<String> adapter2;
	Bundle extras;
	int success = 0;
	
	// Overridden method onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.selectcourses);
		extras = getIntent().getExtras();
		if (extras != null) {
			   username = extras.getString("username");

			}

		// Spinner from XML
		course1Spinner = (Spinner) findViewById(R.id.coursespinner1);
		course2Spinner = (Spinner) findViewById(R.id.coursespinner2);
		course3Spinner = (Spinner) findViewById(R.id.coursespinner3);
		// Get the text box value
		

		// Link to button
		createButton = (Button) findViewById(R.id.createEventButton);

		// Use array adapter to display spinner and get the chosen value
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, courses);
		course1Spinner.setAdapter(adapter);
		course1Spinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					// Get the selected spinner value
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						int position = course1Spinner.getSelectedItemPosition();
						if (courses[+position] != "-- Choose sport --") {
							
							Toast.makeText(getApplicationContext(),
									"You have selected " + courses[+position],
									Toast.LENGTH_LONG).show();
							chosencourse1 = courses[+position];
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {

					}
				});
		// Use array adapter to display spinner and get the chosen value
				adapter1 = new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, courses);
				course2Spinner.setAdapter(adapter1);
				course2Spinner
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							// Get the selected spinner value
							@Override
							public void onItemSelected(AdapterView<?> arg0, View arg1,
									int arg2, long arg3) {
								int position = course2Spinner.getSelectedItemPosition();
								if (courses[+position] != "-- Choose sport --") {
									
									Toast.makeText(getApplicationContext(),
											"You have selected " + courses[+position],
											Toast.LENGTH_LONG).show();
									chosencourse2 = courses[+position];
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {

							}
						});
				// Use array adapter to display spinner and get the chosen value
				adapter2 = new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, courses);
				course3Spinner.setAdapter(adapter2);
				course3Spinner
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							// Get the selected spinner value
							@Override
							public void onItemSelected(AdapterView<?> arg0, View arg1,
									int arg2, long arg3) {
								int position = course3Spinner.getSelectedItemPosition();
								if (courses[+position] != "-- Choose sport --") {
									
									Toast.makeText(getApplicationContext(),
											"You have selected " + courses[+position],
											Toast.LENGTH_LONG).show();
									chosencourse3 = courses[+position];
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {

							}
						});



		// Add a listner to the create event button
		createButton.setOnClickListener(new OnClickListener() {

			// On click of create event button call the class eventCreation
			@Override
			public void onClick(View v) {
				// Check for server connectivity issues
				if (!checkServer(Select_courses.this)) {
					Toast.makeText(Select_courses.this, "Server issue exists",
							Toast.LENGTH_LONG).show();
					return;
				}

				// Call the execute method for AsyncTask
				new eventCreation().execute();
			}
			

			// Method to check connection errors
			private boolean checkServer(Context mContext) {
				ConnectivityManager cm = (ConnectivityManager) mContext
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo netInfo = cm.getActiveNetworkInfo();
				if (netInfo != null && netInfo.isConnectedOrConnecting()) {
					return true;
				}
				return false;
			}
		});
	}

	// Class eventCreaton inherits AsyncTask
	class eventCreation extends AsyncTask<String, String, String> {

		// Functionality to be performed before execution
		protected void onPreExecute() {
			super.onPreExecute();

			// Assign the progress dialog values
			progressDialogEvent = new ProgressDialog(Select_courses.this);
			progressDialogEvent.setMessage("Creating Event");
			progressDialogEvent.setIndeterminate(false);
			progressDialogEvent.setCancelable(true);
			progressDialogEvent.show();
		}

		// Method to perform functionality on background of the execution
		@Override
		protected String doInBackground(String... args) {

			// Prepare the list of parameters to be passed as part of HTTP request
			List<NameValuePair> passEventParams = new ArrayList<NameValuePair>();
			// Bind the parameters
			passEventParams
			.add(new BasicNameValuePair("username", username));
			
			passEventParams.add(new BasicNameValuePair("chosencourse1",
					chosencourse1));
			passEventParams.add(new BasicNameValuePair("chosencourse2" +
					"",
					chosencourse2));
			passEventParams
					.add(new BasicNameValuePair("chosencourse3", chosencourse3));
			
			SP = getSharedPreferences(filename, 0);
			SharedPreferences.Editor editit = SP.edit();
            editit.putString("key1", chosencourse1);
            editit.putString("key2",chosencourse2);
            
	        //etname.setText(getname);
	        //etpass.setText(getpass);
	        
			System.out.println("after binding params");
			// Send the HTTP request and parse the response through JSON
			JSONObject jsonEventObj = jsonParserEvent.makeHttpRequest(
					"http://omega.uta.edu/~akk1814/EventActions/choesncourse.php",
					"POST", passEventParams);
			Log.d("Create Response", jsonEventObj.toString());

			System.out.println("after json response");
			// If the JSON response returned SUCCESS then proceed with functionality
			try {
				if (jsonEventObj != null) {
					// Get the status of from JSON
					success = jsonEventObj.getInt(EVENT_SUCCESS);

					System.out.println("success is "+success);
					// Return values based on JSON
					if (success == 1) {
						return "1";
					} else {
						return "0";
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		// Functionality on completion
		protected void onPostExecute(String result) {

			// Dismiss the progress dialog
			if (progressDialogEvent != null)
				progressDialogEvent.dismiss();

			// start activity on success
			if (success == 1) {
				System.out.println("before intent");
				Intent i = new Intent(getApplicationContext(), MainProfile.class);
				i.putExtra("username",username);
				i.putExtra("course1",chosencourse1);
				i.putExtra("course2",chosencourse2);
				i.putExtra("course3",chosencourse3);
				startActivity(i);
				System.out.println("after intent");
				finish();
			} else {
				System.out.println("inside else");
				// Else request failed
			}

		}
	}
	public void onBackPressed()
	{
	    finish();
	}
}