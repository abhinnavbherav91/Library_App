package com.example.librarymanagement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

	private static String DB_NAME = "Library.sqlite";
    private  String DB_PATH = "";
    private final Context myContext;
    
    //CREATE TABLE "Books" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  
    //NOT NULL  UNIQUE , "catId" INTEGER, "name" TEXT, "author" TEXT)

    private static String TABLE_NAME_BOOKS = "Books";
    private static String KEY_BOOKS_ID = "id";
    private static String KEY_BOOKS_CATID= "catId";
    private static String KEY_BOOKS_NAME= "name";
    private static String KEY_BOOKS_AUTHOR = "author";
    
    //CREATE TABLE "BookIssuedList" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , 
    //"bookId" INTEGER, "studentRollNo" INTEGER, "studentName" TEXT, "issueDate" TEXT)
    private static String TABLE_NAME_ISSUED_LIST = "BookIssuedList";
    private static String KEY_ISSUED_LIST_ID = "id";
    private static String KEY_ISSUED_LIST_BOOKID= "bookId";
    private static String KEY_ISSUED_LIST_ROLLNO= "studentRollNo";
    private static String KEY_ISSUED_LIST_STUDENTNAME = "studentName";
    private static String KEY_ISSUED_LIST_DATE = "issueDate";
    
    //CREATE TABLE "BooksCategories" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , 
    //"name" TEXT)
    private static String TABLE_NAME_CATEGORIES = "BooksCategories";
    private static String KEY_CATEGORIES_ID = "id";
    private static String KEY_CATEGORIES_NAME= "name";
    

   //CREATE  TABLE  IF NOT EXISTS "main"."Login" ("username" TEXT NOT NULL , "password" TEXT NOT NULL )
    private static String TABLE_NAME_LOGIN = "Login";
    
    public DBHandler(Context context) {
		 
    	super(context, DB_NAME, null, 2);
        this.myContext = context;
        DB_PATH = this.myContext.getDatabasePath(DB_NAME).getPath();
    }	
	
    public void createDataBase() throws IOException{
    	
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
 
    		//By calling this method an empty database will be created into the default system path
               //of your application so we will be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
    }
 
 private void copyDataBase() throws IOException{
	 
    	//SQLiteDatabase newDB= this.getReadableDatabase();
    	
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(DB_NAME);
 
    	OutputStream myOutput = new FileOutputStream(DB_PATH );//); newDB.getPath()
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
    	
    	Log.v("DBHandler_Test","Copy Database !");
 
    }
 
 private boolean checkDataBase(){
    	
    	SQLiteDatabase checkDB = null;
 
    	try{
    		//String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
    		Log.v("DBHandler_Test","Datbase Exists !");
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
    	//Toast.makeText(myContext,"checkDataBase" , Toast.LENGTH_SHORT).show();
    	return checkDB != null ? true : false;
    }
 
 ArrayList<BookCategory> getBookCategories(){
	 ArrayList<BookCategory> list = new ArrayList<BookCategory>();
	 
	 String selectQuery = "SELECT * FROM "+ TABLE_NAME_CATEGORIES ;

		Log.v("Harsh COMMENT  Query",selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();//myDataBase;//
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor != null)
		if (cursor.moveToFirst()) {
			do {
				BookCategory bc = new BookCategory();
				bc.id =cursor.getInt(cursor.getColumnIndex(KEY_CATEGORIES_ID));
				bc.name= cursor.getString(cursor.getColumnIndex(KEY_CATEGORIES_NAME));
				list.add(bc);
			} while (cursor.moveToNext());
		}
		
	 return list;
 }
 
 ArrayList<BookissuedList> getissuedbookdetail(){
	 ArrayList<BookissuedList> list = new ArrayList<BookissuedList>();
	 String selectquery = "SELECT * FROM "+TABLE_NAME_ISSUED_LIST;
	 
	 Log.v("Harsh COMMENT  Query",selectquery);
	 
	 SQLiteDatabase db = this.getReadableDatabase();
	 Cursor cursor =db.rawQuery(selectquery, null);
	 if(cursor !=null)
		 if(cursor.moveToFirst())
		 {
			 do
			 {
				 BookissuedList bil = new BookissuedList();
				 bil.bookId = cursor.getInt(cursor.getColumnIndex(KEY_ISSUED_LIST_BOOKID));
				 bil.studentName = cursor.getString(cursor.getColumnIndex(KEY_ISSUED_LIST_STUDENTNAME));
				 bil.issueDate = cursor.getString(cursor.getColumnIndex(KEY_ISSUED_LIST_DATE));
				 bil.studentrollno = cursor.getInt(cursor.getColumnIndex(KEY_ISSUED_LIST_ROLLNO));
				 list.add(bil);
			 }
			 while(cursor.moveToNext());
		 }
	 return list;
 }
 
 ArrayList<Book> getBooksList(){
	 ArrayList<Book> list = new ArrayList<Book>();
	 //SELECT B.* , BIL.studentName ,  BIL.studentRollNo , BIL.issueDate FROM Books B LEFT OUTER JOIN BookIssuedList BIL ON BIL.bookId  = B.id 
	 String selectQuery = "SELECT B.* , BIL."+KEY_ISSUED_LIST_STUDENTNAME+" ,  BIL."+ KEY_ISSUED_LIST_ROLLNO+" , BIL."+KEY_ISSUED_LIST_DATE+" FROM "+TABLE_NAME_BOOKS+" B LEFT OUTER JOIN "+TABLE_NAME_ISSUED_LIST+" BIL ON BIL."+KEY_ISSUED_LIST_BOOKID+"  = B."+KEY_BOOKS_ID; 
		Log.v("Harsh COMMENT  Query",selectQuery);
		System.out.println(selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();//myDataBase;//
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor != null)
		if (cursor.moveToFirst()) {
			do {
				Book b = new Book();
				b.id =cursor.getInt(cursor.getColumnIndex(KEY_BOOKS_ID));
				b.name= cursor.getString(cursor.getColumnIndex(KEY_BOOKS_NAME));
				b.catId= cursor.getInt(cursor.getColumnIndex(KEY_BOOKS_CATID));
				b.author= cursor.getString(cursor.getColumnIndex(KEY_BOOKS_AUTHOR));
				b.issueDate= cursor.getString(cursor.getColumnIndex(KEY_ISSUED_LIST_DATE));
				b.studentName= cursor.getString(cursor.getColumnIndex(KEY_ISSUED_LIST_STUDENTNAME));
				b.studentRollNo= cursor.getString(cursor.getColumnIndex(KEY_ISSUED_LIST_ROLLNO));
				
				list.add(b);
			} while (cursor.moveToNext());
		}
		
	 return list;
 }
 

 
 void addBook(Book b){
	 SQLiteDatabase db = this.getReadableDatabase();
	 ContentValues cv = new ContentValues();
	 
	 cv.put(KEY_BOOKS_NAME, b.name);
	 cv.put(KEY_BOOKS_CATID, b.catId+"");
	 cv.put(KEY_BOOKS_AUTHOR, b.author);
	 
	 db.insert(TABLE_NAME_BOOKS, null, cv);
	 db.close();
 }
 
 void issueBook(Book data){
	 SQLiteDatabase db = this.getReadableDatabase();
	 ContentValues cv = new ContentValues();
	 cv.put(KEY_ISSUED_LIST_BOOKID, data.id);
	 cv.put(KEY_ISSUED_LIST_ROLLNO, data.studentRollNo);
	 cv.put(KEY_ISSUED_LIST_STUDENTNAME, data.studentName);
	 cv.put(KEY_ISSUED_LIST_DATE, data.issueDate);
	 
	 db.insert(TABLE_NAME_ISSUED_LIST, null, cv);
	 db.close();
 }
 
 public void updatepw()
 {
	 String newpw = ChangepasswordActivity.newpassword.getText().toString().trim();
	 Log.v("Harsh COMMENT  Query",newpw);
	 	SQLiteDatabase db = this.getReadableDatabase();
	 	ContentValues args = new ContentValues();
	    args.put("password", newpw);
	    db.update(TABLE_NAME_LOGIN, args,"1", null);
	  
 }
 

 public int Login(){
	 
	 String s = LoginActivity.edtpassword.getText().toString().trim();
	 String selectQuery = "SELECT * FROM "+ TABLE_NAME_LOGIN + " WHERE password =? "; //+ " AND password = " +LoginActivity.password.getText().toString() ;

		Log.v("Harsh COMMENT  Query",selectQuery);
		SQLiteDatabase db = this.getReadableDatabase();//myDataBase;//
		Cursor cursor = db.rawQuery(selectQuery,new String[]{s});
		int row = cursor.getCount();

		return row;
		// looping through all rows and adding to list
//		if (cursor != null)
//		if (cursor.moveToFirst()) {
//			do {
//				Login bc = new Login();
//				bc.Username =cursor.getString(cursor.getColumnIndex(KEY_USERNAME));
//				bc.Password= cursor.getString(cursor.getColumnIndex(KEY_PASSWORD));
//				list.add(bc);
//			} while (cursor.moveToNext());
//		}
//		
//	 return list;
 }
 
 public void returnbook()
 {
	  String s = Returnbook.rollno.getText().toString().trim();
	  String s2 =Returnbook.name.getText().toString().trim();
	   Log.v("amit_s",s);
	   SQLiteDatabase db = this.getWritableDatabase();
	   //db.delete(TABLE_NAME_ISSUED_LIST, KEY_ISSUED_LIST_STUDENTNAME + " = ?",
	     //       new String[] { s});
	    db.execSQL("DELETE FROM " + TABLE_NAME_ISSUED_LIST + " WHERE "+ KEY_ISSUED_LIST_STUDENTNAME + " = '" + s2 +"'" 
	     +" AND " +KEY_ISSUED_LIST_ROLLNO + " = '" + s +"'");
	    Log.v("amit","running return book method in database");
	    db.close();
 }
 
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int a, int b) {
		// TODO Auto-generated method stub

	
}
}