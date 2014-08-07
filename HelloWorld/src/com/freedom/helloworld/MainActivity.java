package com.freedom.helloworld;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	//Activity����ʱonCreate������  
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
				//intent��3�ַ�ʽ����һ���µ�Activity����������һ�ַ�ʽ����Ҫ�µ�Activity�з���ֵ
				startActivityForResult(intent, 10);
			}
		});
    }	
	//onActivityResult���������µ�Activity�ķ���ֵ
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	Log.i("info", "onActivityResult");
    }
	/** 
     * Activity��ϵͳɱ�������ؽ�ʱonRestoreInstanceState������. 
     * ����:��Ļ����ı�ʱ,Activity���������ؽ�;��ǰActivity���ں�̨,ϵͳ��Դ���Ž���ɱ��,�û���������Activity. 
     * �����������onRestoreInstanceState���ᱻ����,��onStart֮��. 
     */
    @Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.i("info", "onRestoreInstanceState");
	}
    /** 
     * Activity��ϵͳɱ��ʱonSaveInstanceState������. 
     * ����:��Ļ����ı�ʱ,Activity���������ؽ�;��ǰActivity���ں�̨,ϵͳ��Դ���Ž���ɱ��. 
     * ����,����ת������Activity���߰�Home���ص�����ʱ�÷���Ҳ�ᱻ����,ϵͳ��Ϊ�˱��浱ǰView�����״̬. 
     * ��onPause֮ǰonSaveInstanceState������. 
     */ 
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.i("info", "onSaveInstanceState");
	}
	//Activity�������ߴӺ�̨���»ص�ǰ̨ʱonStart������  
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("info", "onStart");
	}
	//Activity�Ӻ�̨���»ص�ǰ̨ʱonRestart������  
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i("info", "onRestart");
	}
	//Activity�������ߴӱ����ǡ���̨���»ص�ǰ̨ʱonResume������  
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("info", "onResume");
	}
	//Activity�����ǵ������������ʱonPause������  
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("info", "onPause");
		//�п�����ִ����onPause��onStop��,ϵͳ��Դ���Ž�Activityɱ��,�����б�Ҫ�ڴ˱���־�����  
	}
	//�˳���ǰActivity������ת����ActivityʱonStop������  
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("info", "onStop");
	}
	//�˳���ǰActivityʱonDestroy������,����֮��Activity�ͽ�����  
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("info", "onDestroy");
	}
	//��ϵͳ�ڴ治��ʱ�Ż���ã�����һ����ٷְٵ��á�
	//����ܼ�ʱ���������ã�Ҳ������û�õ���֮ǰ�ͽ�����ر���,��ô�Ͳ���������������
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		Log.i("info", "onLowMemory");
	}
	//����Backʱ����ִ��onPause֮ǰonBackPressed������
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Log.i("info", "onBackPressed");
	}
	//Ҫ�ر�Activityʱ����finish()����
	@Override
	public void finish() {
		super.finish();
		Log.i("info", "finish");
	}
	//Activity���ڻ�û�ʧȥ����ʱ������,��onResume֮���onPause֮��  
//    @Override 
//    public void onWindowFocusChanged(boolean hasFocus) { 
//        super.onWindowFocusChanged(hasFocus); 
//        Log.i("info", "onWindowFocusChanged called."); 
//    }  
    
}