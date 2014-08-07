package com.freedom.activityof3;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.freedom.helloworld.R;

public class Activity02 extends Activity {

	OnClickListener listener1 = null;
	Button button1;
//	Button button2;
	Button button3;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity02);
        Log.i("info", "Activity02---onCreate");

        listener1 = new OnClickListener() {
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putString("store", "数据来自Activity02");
				bundle.putString("id", "2");
				Intent mIntent = new Intent();
				mIntent.putExtras(bundle);
				setResult(RESULT_OK, mIntent);
				finish();
			}
		};
		button1 = (Button) findViewById(R.id.button02_1);
		button1.setOnClickListener(listener1);
		button3 = (Button) findViewById(R.id.button02_13);
		String id = getIntent().getExtras().getString("id");
		button3.setText(" back_to_activity"+id);
		button3.setOnClickListener(listener1);
		String data=null;
		 Bundle extras = getIntent().getExtras();
	        if (extras != null) {
	             data = extras.getString("Activity01");
	        }
	    Log.i("info","现在是在Activity02里:"+data);
	       
    }

    @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.i("info", "Activity02---onRestoreInstanceState");
	}
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Log.i("info", "Activity02---onActivityResult");
    }
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i("info", "Activity02---onSaveInstanceState");
	}
    
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("info", "Activity02---onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("info", "Activity02---onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("info", "Activity02---onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("info", "Activity02---onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.i("info", "Activity02---onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("info", "Activity02---onDestroy");
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.i("info", "Activity02---onLowMemory");
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Log.i("info", "Activity02---onBackPressed");
	}

	@Override
	public void finish() {
		super.finish();
		Log.i("info", "Activity02---finish");
	}
    
}