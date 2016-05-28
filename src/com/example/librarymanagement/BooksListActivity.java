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

public class BooksListActivity extends Activity {

	ArrayList<Book>listBooks = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_books_list);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		
		ListView lv = (ListView)findViewById(R.id.listBooks);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long id) {
				navigateToBookDetail(pos);
				
			}
		});
	}

	void navigateToBookDetail(int pos){
		if(listBooks != null){
			BookDetailActivity.book = listBooks.get(pos);
		}
		Intent i = new Intent(this,BookDetailActivity.class);
		startActivity(i);
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loadList();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//-------------------->>>>>>>>>>>>>  getMenuInflater().inflate(R.menu.books_list, menu);
		return true;
	}

	void loadList(){
		
		ListView lv = (ListView)findViewById(R.id.listBooks);
		
		DBHandler db = new DBHandler(this);
		
		listBooks = db.getBooksList();
		
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        
        for (Book b : listBooks) {
        	if(b.catId==CategoryActivity.selectedCatId)
        	{
        		Map<String, String> map = new HashMap<String, String>(2);
        		map.put("col1", b.name);
        		map.put("col2", b.author);
        		if(b.studentName == null)
        		{
        			map.put("col3", "None");
        		}
        		else
        		{
        			map.put("col3", b.studentName);
        		}
        	data.add(map);
        	}
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                                                  R.layout.list_books,
                                                  new String[] {"col1", "col2","col3"},
                                                  new int[] {R.id.col1,
        		R.id.col2,R.id.col3});
        lv.setAdapter(adapter);
	}
}
