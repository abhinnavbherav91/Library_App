package com.example.librarymanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class ChangepasswordActivity extends Activity {
	
	static EditText newpassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_changepassword);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		newpassword = (EditText)findViewById(R.id.edtnewpassword);
		//Button  setpw = (Button)findViewById(R.id.btnsetpw);
	}
	
	public void changepassword(View v)
	{
		DBHandler db = new DBHandler(this);
		db.updatepw();
		Toast.makeText(getApplicationContext(), "PASSWORD CHANGED", Toast.LENGTH_SHORT).show();
	}
}
