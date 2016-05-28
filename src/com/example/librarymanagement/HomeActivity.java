package com.example.librarymanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class HomeActivity extends Activity {

	//boolean startactivity;
	String navigate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		LoginActivity.edtpassword.setText("");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//--------------->>>>>>>>getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void addBookClick(View v){
		navigate="addbook";
		Intent i = new Intent(this,CategoryActivity.class);
		i.putExtra("startactivity",navigate);
		startActivity(i);
	}
	
	public void showList(View v){
		navigate="showlist";
//		Intent i = new Intent(this,BooksListActivity.class);
		Intent i = new Intent(this,CategoryActivity.class);
		i.putExtra("startactivity",navigate);
		startActivity(i);
		
	}
	public void issueBook(View v){
		navigate="issuebook";
		//Intent i = new Intent(this,NonIssuedBooksActivity.class);
		Intent i = new Intent(this,CategoryActivity.class);
		i.putExtra("startactivity",navigate);
		startActivity(i);
	}

	public void returnbook(View v)
	{
		Intent i = new Intent(this,Returnbook.class);
		startActivity(i);
	}
	
	public void changepassword(View v){
		Intent i = new Intent(this,ChangepasswordActivity.class);
		startActivity(i);
	}
	public void issuedbook(View v){
		Intent i = new Intent(this,Issuedbook.class);
		startActivity(i);
	}
}
