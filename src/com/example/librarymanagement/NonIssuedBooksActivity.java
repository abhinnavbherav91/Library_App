package com.example.librarymanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class NonIssuedBooksActivity extends Activity {

	ArrayList<Book>listBooks = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_non_issued_books);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		ListView lv = (ListView)findViewById(R.id.listBooks);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long id) {
				navigateToIssueBook(pos);
				
			}
		});
	}

	void navigateToIssueBook(int pos){
		if(listBooks != null){
			IssueBookActivity.book = listBooks.get(pos);
		}
			Intent i = new Intent(this,IssueBookActivity.class);
			startActivity(i);
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//-------------------->>>>  getMenuInflater().inflate(R.menu.non_issued_books, menu);
		return true;
	}
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loadList();
		
	}
	
	ArrayList<Book> getNonIssuedList(ArrayList<Book> totalList){
		
		listBooks = new ArrayList<Book>();
		 for (Book b : totalList) {
	           if(b.catId==CategoryActivity.selectedCatId)
	           {
	            if(b.studentName == null){
	            	listBooks.add(b);
	            }
	            }
		 }
		 
		 return listBooks;
	}
void loadList(){
		
		ListView lv = (ListView)findViewById(R.id.listBooks);
		
		DBHandler db = new DBHandler(this);
		
		listBooks = getNonIssuedList(db.getBooksList());
		
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        
        for (Book b : listBooks) {
           
            	 Map<String, String> map = new HashMap<String, String>(2);
                 map.put("col1", b.name + " (" + b.author +")");
            	data.add(map);     
            
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                                                  R.layout.list_categories,
                                                  new String[] {"col1"},
                                                  new int[] {R.id.col1});
        lv.setAdapter(adapter);
	}

}

