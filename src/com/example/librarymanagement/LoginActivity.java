package com.example.librarymanagement;

import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	/*static EditText username;
	static EditText password;*/
	static int font_Large;
	static int font_Normal;
	static Point screenSize;
	static Bitmap bit;
	static int logoNameWidth;

	// private static final int FFFFFF = 0;

	
	// private Button btnMakeObjectRequest;// btnMakeArrayRequest;

	private ProgressDialog pDialog;

	// private String jsonResponse;
	public EditText edtusername;
	public static EditText edtpassword;
	static String userId;
	private CheckBox saveLoginCheckBox;
	private SharedPreferences loginPreferences;
	private SharedPreferences.Editor loginPrefsEditor;
	private Boolean saveLogin;
	private String username, password;
	int row;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		initializeControls();
		Globals.fontFamily = Typeface.createFromAsset(getAssets(),
				"fonts/fontawesome-webfont.ttf");
		Button sampleText = (Button) findViewById(R.id.btnLogin);
		sampleText.setTypeface(Globals.fontFamily);
		sampleText.setText("\uF090 Login");
		sampleText.setTextColor(Color.WHITE);
		/*username = (EditText)findViewById(R.id.edtusername);
		password = (EditText)findViewById(R.id.edtpassword);*/
		
		 DBHandler db = new DBHandler(this);
			try {
				db.createDataBase();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void initializeControls() {
		font_Large = Globals.getAppFontSize_Large(this);
		font_Normal = Globals.getAppFontSize(this);
		screenSize = Globals.getScreenSize(this);
		Point btnSize = Globals.getAppButtonSize(this);
Log.d("Abhinav","btnSize---->"+btnSize );
		
		TextView txtH1 = (TextView) findViewById(R.id.txtHeading1);
		TextView txtH2 = (TextView) findViewById(R.id.txtHeading2);
		txtH1.setTextSize(font_Large);
		txtH2.setTextSize(font_Large);
		edtusername = (EditText) findViewById(R.id.edtUsername);
		edtpassword = (EditText) findViewById(R.id.edtPassword);
		saveLoginCheckBox = (CheckBox) findViewById(R.id.saveLoginCheckBox);
		loginPreferences = getSharedPreferences("loginPrefs", 0);
		loginPrefsEditor = loginPreferences.edit();
		saveLogin = loginPreferences.getBoolean("saveLogin", false);
		if (saveLogin == true) {
			edtusername.setText(loginPreferences.getString("username", ""));
			Log.d("response", "edtpassword is" + edtpassword);
			edtpassword.setText(loginPreferences.getString("password", ""));
			saveLoginCheckBox.setChecked(true);
		}

		edtusername.setTextSize(font_Normal);
		edtpassword.setTextSize(font_Normal);
		Button btnLogin = (Button)findViewById(R.id.btnLogin);
		btnLogin.setTextSize(font_Normal);

		btnLogin.getLayoutParams().width = btnSize.x;
		btnLogin.getLayoutParams().height = btnSize.y;
		Log.d("Abhinav", ""+btnLogin.getLayoutParams().height +""+btnLogin.getLayoutParams().width);

	

		// btnMakeObjectRequest = (Button) findViewById(R.id.btnLogin);
		// btnMakeArrayRequest = (Button) findViewById(R.id.btnArrayRequest);
		pDialog = new ProgressDialog(this);
		pDialog.setMessage("Please wait...");
		pDialog.setCancelable(false);

	}
	Boolean validateCredentials() {

		username = edtusername.getText().toString();
		password = edtpassword.getText().toString();

		if (username == null || password == null) {
			Toast.makeText(getApplicationContext(),
					"please enter username or password", Toast.LENGTH_SHORT)
					.show();
			return false;

		}
		if (username.trim().equals("") || password.trim().equals("")) {
			Toast.makeText(getApplicationContext(),
					"please enter username or password", Toast.LENGTH_SHORT)
					.show();
			return false;
		}
		return true;
	}
	public void onloginbtnclick(View v)
	{
		if (validateCredentials()) {
			InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(edtusername.getWindowToken(), 0);

			if (saveLoginCheckBox.isChecked()) {
				loginPrefsEditor.putBoolean("saveLogin", true);
				loginPrefsEditor.putString("username", username);
				loginPrefsEditor.putString("password", password);
				loginPrefsEditor.commit();
			} else {
				loginPrefsEditor.clear();
				loginPrefsEditor.commit();
			}
		DBHandler db = new DBHandler(this);		
		row = db.Login();
		if(row>0)
		{
			Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_LONG).show();
			Intent i = new Intent(this,HomeActivity.class);
			startActivity(i);
		}
	 }
	}
}
