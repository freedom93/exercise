package com.freedom.helloworld;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	//Activity创建时onCreate被调用  
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.i("info", "onCreate");
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				//intent有3种方式启动一个新的Activity，这是其中一种方式：需要新的Activity有返回值
				startActivityForResult(intent, 10);
			}
		});
    }	
	//onActivityResult方法处理新的Activity的返回值
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Log.i("info", "onActivityResult");
    }
	/** 
     * Activity被系统杀死后再重建时onRestoreInstanceState被调用. 
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死,用户又启动该Activity. 
     * 这两种情况下onRestoreInstanceState都会被调用,在onStart之后. 
     */
    @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.i("info", "onRestoreInstanceState");
	}
    /** 
     * Activity被系统杀死时onSaveInstanceState被调用. 
     * 例如:屏幕方向改变时,Activity被销毁再重建;当前Activity处于后台,系统资源紧张将其杀死. 
     * 另外,当跳转到其他Activity或者按Home键回到主屏时该方法也会被调用,系统是为了保存当前View组件的状态. 
     * 在onPause之前onSaveInstanceState被调用. 
     */ 
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i("info", "onSaveInstanceState");
	}
	//Activity创建或者从后台重新回到前台时onStart被调用  
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("info", "onStart");
	}
	//Activity从后台重新回到前台时onRestart被调用  
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("info", "onRestart");
	}
	//Activity创建或者从被覆盖、后台重新回到前台时onResume被调用  
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("info", "onResume");
	}
	//Activity被覆盖到下面或者锁屏时onPause被调用  
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("info", "onPause");
		//有可能在执行完onPause或onStop后,系统资源紧张将Activity杀死,所以有必要在此保存持久数据  
	}
	//退出当前Activity或者跳转到新Activity时onStop被调用  
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("info", "onStop");
	}
	//退出当前Activity时onDestroy被调用,调用之后Activity就结束了  
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("info", "onDestroy");
	}
	//当系统内存不足时才会调用，但不一定会百分百调用。
	//如果能及时调用则会调用，也可能在没用调用之前就将程序关闭了,那么就不会调用这个方法。
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.i("info", "onLowMemory");
	}
	//按了Back时，在执行onPause之前onBackPressed被调用
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Log.i("info", "onBackPressed");
	}
	//要关闭Activity时调用finish()方法
	@Override
	public void finish() {
		super.finish();
		Log.i("info", "finish");
	}
	//Activity窗口获得或失去焦点时被调用,在onResume之后或onPause之后  
//    @Override 
//    public void onWindowFocusChanged(boolean hasFocus) { 
//        super.onWindowFocusChanged(hasFocus); 
//        Log.i("info", "onWindowFocusChanged called."); 
//    }  
    
}