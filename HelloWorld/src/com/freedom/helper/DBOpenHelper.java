package com.freedom.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
	public DBOpenHelper(Context context) {
		super(context, "hello.db", null, 1);
	}

	//数据库第一次创建时候调用
	public void onCreate(SQLiteDatabase db) {
        Log.i("info", "DBOpenHelper---onCreate");
		db.execSQL("create table Item(label varchar(20), item1 varchar(20), item2 varchar(20))");
	}

	//数据库文件版本号发生变化时调用
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        Log.i("info", "DBOpenHelper---onUpgrade");

	}
}