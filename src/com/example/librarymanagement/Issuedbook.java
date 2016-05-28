package com.example.librarymanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Issuedbook extends Activity {

	ArrayList<BookissuedList>listissuedBooks = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_issuedbook);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		showissuedlist_data();
	}
	
	public void showissuedlist_data()
	{
		ListView listview = (ListView)findViewById(R.id.listView1);
		DBHandler db = new DBHandler(getApplicationContext());
		listissuedBooks = db.getissuedbookdetail();
		 Log.v("Harsh COMMENT  Query", listissuedBooks.toString());
		
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		
		for(BookissuedList bil : listissuedBooks)
		{
			String rollno =Integer.toString(bil.studentrollno);
			String bookid =Integer.toString(bil.bookId);
			Map<String, String> map = new HashMap<String, String>();
			map.put("Name", bil.studentName);
			map.put("rollno.", rollno);
			map.put("Bookid", bookid);
			map.put("issueddate", bil.issueDate);
			
			data.add(map);
		}
		SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), data, R.layout.list_issuedbook,new String[] {"Name","rollno.","Bookid","issueddate"},
				new int[] {R.id.list_textView1,R.id.list_textView2,R.id.list_textView3,R.id.list_textView4});
		listview.setAdapter(adapter);
	}
}
