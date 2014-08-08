package com.freedom.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.freedom.activityof3.Activity02;
import com.freedom.activityof3.Activity03;
import com.freedom.helloworld.R;
import com.freedom.helper.Item;
import com.freedom.helper.MyBaseAdapter;

public class ListViewActivity extends Activity {
	
	//定义Person
	List<Item> itemlist ;
	ListAdapter adapter = null;
	ListView listView01 = null;

	Button button01;
	Button button02;
	Button button03;
	static final int REQUEST_CODE = 1;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        Log.i("info", "Activity01---onCreate");
        button01 = (Button) findViewById(R.id.button01_2);
        button01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListViewActivity.this, Activity02.class);
				intent.putExtra("Activity01", "数据来自ListViewActivity");
				intent.putExtra("id", "1");
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
        
        button02 = (Button) findViewById(R.id.button01_3);
        button02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListViewActivity.this, Activity03.class);
				intent.putExtra("Activity01", "数据来自ListViewActivity");
				intent.putExtra("id", "1");
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
		button03 = (Button) findViewById(R.id.button01_23);
        button03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ListViewActivity.this, Activity02.class);
				intent.putExtra("Activity01", "数据来自ListViewActivity");
				intent.putExtra("id", "1");
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
        //获取组件
        listView01 = (ListView)findViewById(R.id.listView01);
        
        //显示ListView
        initListAllitem();
        showByMyBaseAdapter();
        
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
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	Log.i("info", "Activity01---onActivityResult");
    	 Bundle extras = data.getExtras();
    	 String id = extras.getString("id");
		 if(id.equals("2")||id.equals("3")){
			 button03.setText(" back_to_activity"+id);
		 }else{
			 button03.setText(" back_to_activity(2|3)");
		 }
    	if (requestCode == REQUEST_CODE) {
			if (resultCode == RESULT_CANCELED)
				setTitle("取消");
			else if (resultCode == RESULT_OK) {
				String temp=null;
			        if (extras != null) {
			        	temp = extras.getString("store");
			        }
//				setTitle(temp);
				Log.i("info","现在是在Activity01里:"+temp);
			}
		}
    }

@Override
protected void onRestoreInstanceState(Bundle savedInstanceState) {
	super.onRestoreInstanceState(savedInstanceState);
	Log.i("info", "Activity01---onRestoreInstanceState");
}

@Override
protected void onSaveInstanceState(Bundle outState) {
	super.onSaveInstanceState(outState);
	Log.i("info", "Activity01---onSaveInstanceState");
}

@Override
protected void onStart() {
	super.onStart();
	Log.i("info", "Activity01---onStart");
}

@Override
protected void onRestart() {
	super.onRestart();
	Log.i("info", "Activity01---onRestart");
}

@Override
protected void onResume() {
	super.onResume();
	Log.i("info", "Activity01---onResume");
}

@Override
protected void onPause() {
	super.onPause();
	Log.i("info", "Activity01---onPause");
}

@Override
protected void onStop() {
	super.onStop();
	Log.i("info", "Activity01---onStop");
}

@Override
protected void onDestroy() {
	super.onDestroy();
	Log.i("info", "Activity01---onDestroy");
}

@Override
public void onBackPressed() {
	super.onBackPressed();
	Log.i("info", "Activity01---onBackPressed");
}

@Override
public void finish() {
	super.finish();
	Log.i("info", "Activity01---finish");
}

}