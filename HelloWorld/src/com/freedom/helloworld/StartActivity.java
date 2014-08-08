package com.freedom.helloworld;

import com.freedom.activityof3.Activity01;
import com.freedom.listview.DbActivity;
import com.freedom.listview.ListView2Activity;
import com.freedom.listview.ListViewActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity {
	
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Button btn5;
	Button btn6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		btn1 = (Button)findViewById(R.id.btn01);
		btn1.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this,HelloActivity.class);
				startActivity(intent);
			}
		});
		btn2 = (Button)findViewById(R.id.btn02);
		btn2.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this,MainActivity.class);
				startActivity(intent);
			}
		});
		btn3 = (Button)findViewById(R.id.btn03);
		btn3.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this,Activity01.class);
				startActivity(intent);
			}
		});
		
		btn4 = (Button)findViewById(R.id.btn04);
		btn4.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this,ListViewActivity.class);
				startActivity(intent);
			}
		});
		btn5 = (Button)findViewById(R.id.btn05);
		btn5.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this,DbActivity.class);
				startActivity(intent);
			}
		});
		btn6 = (Button)findViewById(R.id.btn06);
		btn6.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(StartActivity.this,ListView2Activity.class);
				startActivity(intent);
			}
		});
		
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.first, menu);
		return true;
	}

}
