package com.freedom.activityof3;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.freedom.helloworld.R;

public class Activity01 extends Activity {

	Button button01;
	Button button02;
	Button button03;
	
	static final int REQUEST_CODE = 1;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity01);
        Log.i("info", "Activity01---onCreate");
        button01 = (Button) findViewById(R.id.button01_2);
        button01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Activity01.this, Activity02.class);
				intent.putExtra("Activity01", "数据来自Activity01");
				intent.putExtra("id", "1");
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
        
        button02 = (Button) findViewById(R.id.button01_3);
        button02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Activity01.this, Activity03.class);
				intent.putExtra("Activity01", "数据来自Activity01");
				intent.putExtra("id", "1");
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
		button03 = (Button) findViewById(R.id.button01_23);
        button03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Activity01.this, Activity02.class);
				intent.putExtra("Activity01", "数据来自Activity0");
				intent.putExtra("id", "1");
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
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
//					setTitle(temp);
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