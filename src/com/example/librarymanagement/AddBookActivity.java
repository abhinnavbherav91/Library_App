package com.example.librarymanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_book);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//----------------->>>>>>>>   getMenuInflater().inflate(R.menu.add_book, menu);
		return true;
	}

	public void addBookClick(View v){
		
		EditText edtName = (EditText)findViewById(R.id.edtBookName);
		EditText edtAuthor = (EditText)findViewById(R.id.edtAuthor);
		
		String name = edtName.getText().toString();
		String author = edtAuthor.getText().toString();
		
		if(name == null || name.equals("")){
			Toast.makeText(getApplicationContext(), "Please Enter Book Name", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(author == null || author.equals("")){
			Toast.makeText(getApplicationContext(), "Please Enter Author Name", Toast.LENGTH_SHORT).show();
			return;
		}
		
		Book b = new Book();
		b.name = name;
		b.author = author;
		b.catId = CategoryActivity.selectedCatId;
		
		DBHandler db = new DBHandler(this);
		db.addBook(b);
		Toast.makeText(getApplicationContext(), "Book Added Succesfully", Toast.LENGTH_SHORT).show();
		
		//nevigate to home activity after done above work or add book
		Intent i =new Intent(this,HomeActivity.class);
		startActivity(i);
	}
}

