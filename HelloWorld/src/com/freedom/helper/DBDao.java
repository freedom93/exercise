package com.freedom.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBDao {
	DBOpenHelper dbOpenHelper;
	
	public DBDao(Context context){
		this.dbOpenHelper=new DBOpenHelper(context);
	}
	/**
	 * 添加一条数据
	 */
	public void save(Item Item){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("insert into Item(label,item1,item2) values(?,?,?)", new Object[]{Item.getLabel(),Item.getItem1(),Item.getItem2()});
		db.close();
	}
	/**
	 * 删除一条数据
	 */
	public void delete(int label){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from Item where label=?", new Object[]{label});
		db.close();
	}
	/**
	 * 更新一条数据
	 */
	public void update(Item Item){
		SQLiteDatabase db=dbOpenHelper.getWritableDatabase();
		db.execSQL("update Item set item1=?,item2=? where label=?", new Object[]{Item.getLabel(),Item.getItem1(),Item.getItem2()});
		db.close();
	}
	/**
	 * 查找一条数据
	 */
	public Item find(int label){
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor =db.rawQuery("select * from Item where label=?", new String[]{String.valueOf(label)});
		if(cursor.moveToFirst()){
			int label2=cursor.getInt(cursor.getColumnIndex("label"));
			String item1=cursor.getString(cursor.getColumnIndex("item1"));
			String item2=cursor.getString(cursor.getColumnIndex("item2"));
			Item Item=new Item();
			Item.setLabel(label2);
			Item.setItem1(item1);
			Item.setItem2(item2);
			return Item;
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 获取数据总数
	 */
	public long getCount(){
		SQLiteDatabase db=dbOpenHelper.getReadableDatabase();
		Cursor cursor =db.rawQuery("select count(*) from Item", null);
		cursor.moveToFirst();
		long reslut=cursor.getLong(0);
		return reslut;
	}
}