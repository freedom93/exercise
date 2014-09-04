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
		//设置全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		initWelcomeView();
		//切换到欢迎界面
		this.setContentView(welcomeView);
	}

	/**
	 * 初始化欢迎界面
	 */
	public void initWelcomeView() {
		welcomeView = new WelcomeView(this);
	}

}