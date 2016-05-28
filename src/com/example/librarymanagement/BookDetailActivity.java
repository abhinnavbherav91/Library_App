package com.example.librarymanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class BookDetailActivity extends Activity {

	static Book book = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book_detail);
	//	requestWindowFeature(Window.FEATURE_NO_TITLE);

		showData();
	}

	public void showData(){
		TextView txtBName = (TextView)findViewById(R.id.txtBookName);
		TextView txtAuthor = (TextView)findViewById(R.id.txtAuthor);
		TextView txtSName= (TextView)findViewById(R.id.txtStudentName);
		TextView txtRollNo = (TextView)findViewById(R.id.txtRollNo);
		TextView txtIssueDate = (TextView)findViewById(R.id.txtissueDate);
		
		
		if(book != null){
			txtBName.setText(book.name);
			txtAuthor.setText(book.author);
			
			if(book.studentName != null){
				txtSName.setText(book.studentName);
			}else{
				txtSName.setText("None");
			}
			
			if(book.studentRollNo != null){
				txtRollNo.setText(book.studentRollNo);
			}else{
				txtRollNo.setText("N/A");
			}
			
			if(book.issueDate != null){
				txtIssueDate.setText(book.issueDate);
			}else{
				txtIssueDate.setText("N/A");
			}
			
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//-------------------------->>>>	getMenuInflater().inflate(R.menu.book_detail, menu);
		return true;
	}

}