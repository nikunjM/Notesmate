package com.notesmates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// This class focuses on Registration and validation of user
public class RegActivity extends BaseActivity {
	// Set the preferences
	public static final String PREFS_NAME = "RegistrationPrefs";
	// Variable declarations
	EditText edit_text_email, edit_text_userName, edit_text_password,
			edit_text_re_password;
	String string_email, string_userName, string_password,
			string_retypePassword;
	public static String string_code;
	ProgressDialog progressDialog;
	int flag = 0;
	int email_match = 0;
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 6;
	JSONParser jsonParser = new JSONParser();
	Button submit;
	private static final String TAG_SUCCESS = "success";
	final String emailPattern = "[a-zA-Z0-9._-]+@mavs.uta.edu";
	int success = 0;
	Bundle extras;
	String username;

	// Overridden method onCreate
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reg);
		extras = getIntent().getExtras();
		if (extras != null) {
			   username = extras.getString("username");
			}
		// Fetch details from text boxes
		edit_text_userName = (EditText) findViewById(R.id.username);
		edit_text_email = (EditText) findViewById(R.id.emailId);
		edit_text_password = (EditText) findViewById(R.id.password);
		edit_text_re_password = (EditText) findViewById(R.id.retypepwd);
		// Set to the registration button
		submit = (Button) findViewById(R.id.registerButton);
		// Bind a listener to the button to be performed on click of the button
		submit.setOnClickListener(new OnClickListener() {
			// Overridden method from Activity
			@Override
			public void onClick(View v) {
				// Get the text boxes to a string
				string_userName = edit_text_userName.getText().toString()
						.trim();
				string_email = edit_text_email.getText().toString().trim();
				string_password = edit_text_password.getText().toString();
				string_retypePassword = edit_text_re_password.getText()
						.toString();
				// check if emailID entered is UTA MAVS mailID, password match
				// and password length
				if (string_email.matches(emailPattern)
						&& (string_password.equals(string_retypePassword))
						&& (string_password.length() >= 6)) {
					email_match = 1;
					new loginAccess().execute();
				}
				// If the emailID is not a UTA ID,then display appropriate error
				// message
				else if (!string_email.matches(emailPattern)) {
					Toast.makeText(
							getApplicationContext(),
							"Invalid email address! Please enter a valid MAVS E-Mail ID",
							Toast.LENGTH_SHORT).show();
				}
				// If the password and retype password does not match, display
				// the appropriate error message
				else if (!string_password.equals(string_retypePassword)) {
					Toast.makeText(getApplicationContext(),
							"Password and Re-type password mismatch",
							Toast.LENGTH_SHORT).show();
				}
				// If the password length is less, display the appropriate error
				// message
				else if (string_password.length() < 6) {
					Toast.makeText(getApplicationContext(),
							"Password should be atleast 6 characters",
							Toast.LENGTH_SHORT).show();
				}
				// Check server connectivity issues
				if (!checkServer(RegActivity.this)) {
					Toast.makeText(RegActivity.this, "Server issue exists",
							Toast.LENGTH_LONG).show();
					return;
				}
				// Call to loginAccess class object
			}

			// Funtionality of server connectivity
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

	// Class inherits async
	class loginAccess extends AsyncTask<String, String, String> {

		// Functionality to be performed before execution
		protected void onPreExecute() {
			super.onPreExecute();
			// Assign the progress dialog values
			progressDialog = new ProgressDialog(RegActivity.this);
			progressDialog.setMessage("Registration in progress");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(true);
			progressDialog.show();
		}

		// Method to perform functionality on background of the execution
		@Override
		protected String doInBackground(String... arg0) {
			// Prepare the list of parameters to be passed as part of HTTP
			// request
			List<NameValuePair> passParams = new ArrayList<NameValuePair>();
			string_code = getRandomString();
			System.out.println("after split "+string_code.split(" "));
			
			System.out.println("Random code:" + string_code);
			// Assign the edit text values to a string
			string_userName = edit_text_userName.getText().toString().trim();
			string_email = edit_text_email.getText().toString().trim();
			string_password = edit_text_password.getText().toString();
			string_retypePassword = edit_text_re_password.getText().toString();

			// Bind the parameters
			passParams.add(new BasicNameValuePair("name", string_userName));
			passParams.add(new BasicNameValuePair("email", string_email));
			passParams.add(new BasicNameValuePair("password", string_password));
			passParams.add(new BasicNameValuePair("retypepwd",
					string_retypePassword));
			passParams.add(new BasicNameValuePair("verificationCode",
					string_code));

			// Send the HTTP request and parse the response through JSON
			
			
			JSONObject json = jsonParser.makeHttpRequest(
					"http://omega.uta.edu/~akk1814/RegStudents.php", "POST",
					passParams);

			//Log.d("Create Response", json.toString());
			// If the JSON response returned SUCCESS then proceed with
			// functionality
			try {
				if (json != null) {
					// Get the status of from JSON
					System.out.println("TAG_SUCCESS   "+(TAG_SUCCESS).toString());
					success = json.getInt(TAG_SUCCESS);
					// Enter based on JSON and validations
					if (success == 1) {
						success = 1;
						return "1";
					} else {
						
						success = 0;
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
			if (progressDialog != null)
				progressDialog.dismiss();
			System.out.println("Result in postExec "+result);
			System.out.println("Success in postExec "+success);
			if(success == 0)
			{
				Toast.makeText(
						getApplicationContext(),
						"Username already exists! Please choose a new username",
						Toast.LENGTH_SHORT).show();
			}
			if(success == 1)
			{
				Intent i = new Intent(getApplicationContext(),
						UserVerificationActivity.class);
				i.putExtra("username",string_userName);
				i.putExtra("email",string_email);
				i.putExtra("verificationcode",string_code);
				startActivity(i);
				//finish();
			} 
		}
	}

	private String getRandomString() {
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	// Generate the random number from given CHAR_LIST
	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	@Override
	public void onBackPressed()
	{ 
		finish();
	}
}