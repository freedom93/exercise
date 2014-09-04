package com.freedom.welcomeview;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


/**
 * 刷帧线程
 * @author lifen
 *
 */
public class WelcomeViewDrawThread extends Thread {
	WelcomeView welcomeView;
	private int sleepSpan = 100;
	//循环标记位
	private boolean flag = true;
	private SurfaceHolder surfaceHolder;

	public WelcomeViewDrawThread(WelcomeView welcomeView) {
		this.welcomeView = welcomeView;
		surfaceHolder = welcomeView.getHolder();
	}

	@Override
	public void run() {
		//声明画布
		Canvas c;
		while (this.flag) {
			c = null;
			try {
				//锁定整个画布
				c = this.surfaceHolder.lockCanvas(null);
				//同步
				synchronized (this.surfaceHolder) {
					//调用绘制方法
					welcomeView.onDraw(c);
				}
			} finally {
				//用finally保证一定被执行
				if (c != null) {
					//更新屏幕显示内容
					this.surfaceHolder.unlockCanvasAndPost(c);
				}
			}
			try {
				Thread.sleep(sleepSpan);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 循环标记位的set方法
	 * @param flag
	 */
	public void setFlag(boolean flag) { 
		this.flag = flag;
	}
}
