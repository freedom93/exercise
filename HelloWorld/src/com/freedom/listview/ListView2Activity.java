package com.freedom.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.freedom.helloworld.R;
import com.freedom.helper.Item;
import com.freedom.helper.MyBaseAdapter;

public class ListView2Activity extends Activity {
	
	//定义Person
	List<Item> itemlist ;
	ListAdapter adapter = null;
	ListView listView01 = null;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取组件
        listView01 = (ListView)findViewById(R.id.listView01);
        
        //显示ListView
        initListAllitem();
        showByMyBaseAdapter();
        
        //初始化ListView的事件
       // initListView01Event();
        
        OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				setTitle("您选中的是:  "+parent.getItemAtPosition(position).toString());
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				setTitle("");
				
			}
			
		};
		listView01.setOnItemSelectedListener(itemSelectedListener);
    }
	
	public void initListAllitem(){
		itemlist = new ArrayList<Item>();
		for(int i = 1; i <= 20; i++){
			itemlist.add(new Item(i,"item"+i+".1","item"+i+".2"));
		}
	}
	
	public void showByMyBaseAdapter(){
		adapter = new MyBaseAdapter(this, itemlist);
		listView01.setAdapter(adapter);
	}

	
	

	/**
	 * 初始化listView01的事件.
	 */
	public void initListView01Event(){
		
		//ListView的item点击事件
		listView01.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), 
						"[OnItemClickListener]点击了："+itemlist.get(position).item1, 
						Toast.LENGTH_SHORT).show();
			}
		});
		
		//ListView的item长按点击事件
		listView01.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), 
						"[OnItemLongClickListener]点击了："+itemlist.get(position).item1, 
						Toast.LENGTH_SHORT).show();
				return false;
			}
			
		});
		
		//ListView的键盘选中事件(直接触摸屏幕选中不会激发)
		listView01.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), 
						"[OnItemSelectedListener:onItemSelected]点击了："
+itemlist.get(position).item1, 
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(), 
						"[OnItemSelectedListener:onNothingSelected]点击了", 
						Toast.LENGTH_SHORT).show();
			}
			
		});
	}
}