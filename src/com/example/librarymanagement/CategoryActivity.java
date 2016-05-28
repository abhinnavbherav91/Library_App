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

public class CategoryActivity extends Activity {

	ArrayList<BookCategory>listCat = null;
	static int selectedCatId = 0;
	//boolean home;
	String home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		//home = getIntent().getBooleanExtra("startactivity", true);//getIntent().getExtras("startactivity",home);
		home=getIntent().getExtras().getString("startactivity");
		System.out.println(home);
		ListView lv = (ListView)findViewById(R.id.listCat);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long id) {
				navigateToNextActivity(pos);
				
			}
		});
	}

	void navigateToNextActivity(int pos){
		
		if(listCat!= null){
			selectedCatId = listCat.get(pos).id;
		}
		if(home.equals("addbook"))
		{
			Intent i = null;
			i = new Intent(this , AddBookActivity.class);
			startActivity(i);
		}
		else if(home.equals("showlist"))
		{
			Intent i = null;
			i = new Intent(this , BooksListActivity.class);
			startActivity(i);	
		}
		else
		{
			Intent i = null;
			i = new Intent(this , NonIssuedBooksActivity.class);
			startActivity(i);
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//-------------------->>>>>>>>>>  getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loadList();
		
	}
	
void loadList(){
		
		ListView lv = (ListView)findViewById(R.id.listCat);
		
		DBHandler db = new DBHandler(this);
		
		listCat = db.getBookCategories();
		
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        
        for (BookCategory b : listCat) {
            Map<String, String> map = new HashMap<String, String>(2);
            map.put("col1", b.name);
            
            data.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                                                  R.layout.list_categories,
                                                  new String[] {"col1"},
                                                  new int[] {R.id.col1});
        lv.setAdapter(adapter);
	}
}

