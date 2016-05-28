package com.example.librarymanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Returnbook extends Activity {

	static EditText rollno;
	static EditText name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_returnbook);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		Button submit = (Button)findViewById(R.id.submit);
		rollno = (EditText)findViewById(R.id.edttxtrollno);
		name = (EditText)findViewById(R.id.edttxtname);
		submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Log.v("amit","running return book onclick");
				DBHandler db = new DBHandler(getApplicationContext());
				db.returnbook();
				//nevigate to home activity after done above work or issue book
				Intent i =new Intent(Returnbook.this,HomeActivity.class);	
				startActivity(i);
			}
		});
		Toast.makeText(getApplicationContext(), "Book return successfully", Toast.LENGTH_SHORT).show();
	}
}
