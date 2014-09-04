package com.freedom.welcomeview;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


/**
 * WelcomeActivity
 * @author lifen
 *
 */
public class WelcomeActivity extends Activity {
	WelcomeView welcomeView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//����ȫ��
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		initWelcomeView();
		//�л�����ӭ����
		this.setContentView(welcomeView);
	}

	/**
	 * ��ʼ����ӭ����
	 */
	public void initWelcomeView() {
		welcomeView = new WelcomeView(this);
	}

}