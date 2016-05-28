package com.example.librarymanagement;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class IssueBookActivity extends Activity {

	static Book book = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_issue_book);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		setData();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//------------------------>>>>>>>>.  getMenuInflater().inflate(R.menu.issue_book, menu);
		return true;
	}
	
	public void setData(){
		TextView txtHeading = (TextView)findViewById(R.id.txtHeading);
		txtHeading.setText("");
		if(book!= null){
			txtHeading.setText(book.name + " ("+book.author+")");
		}
	}
	
	public void onclickIssue(View v){
		
		EditText edStudentName = (EditText)findViewById(R.id.edtStudentName);
		EditText edStudentRoll = (EditText)findViewById(R.id.edtRollNo);
		
		
		String name = edStudentName.getText().toString();
		String rollNo = edStudentRoll.getText().toString();
		
		if(name == null || name.equals("")){
			Toast.makeText(getApplicationContext(), "Please Enter Student Name", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(rollNo == null || rollNo.equals("")){
			Toast.makeText(getApplicationContext(), "Please Enter Student Roll No.", Toast.LENGTH_SHORT).show();
			return;
		}
		
		book.studentName = name;
		book.studentRollNo = rollNo;
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		book.issueDate = getTwoDigit(day)+"/"+getTwoDigit(month)+"/"+year;
		
		DBHandler db = new DBHandler(this);
		db.issueBook(book);
		Toast.makeText(getApplicationContext(), "Book Issued Succesfully", Toast.LENGTH_SHORT).show();
		
		//nevigate to home activity after done above work or issue book
		Intent i =new Intent(this,HomeActivity.class);
		startActivity(i);
	}
	
	String getTwoDigit(int i){
		String str = "";
		
		if(i<10){
			str = "0"+i;
		}else{
			str  = ""+i;
		}
		return str;
	}

}
