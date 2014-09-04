package com.freedom.welcomeview;

import android.graphics.Canvas;
import android.view.SurfaceHolder;


/**
 * ˢ֡�߳�
 * @author lifen
 *
 */
public class WelcomeViewDrawThread extends Thread {
	WelcomeView welcomeView;
	private int sleepSpan = 100;
	//ѭ�����λ
	private boolean flag = true;
	private SurfaceHolder surfaceHolder;

	public WelcomeViewDrawThread(WelcomeView welcomeView) {
		this.welcomeView = welcomeView;
		surfaceHolder = welcomeView.getHolder();
	}

	@Override
	public void run() {
		//��������
		Canvas c;
		while (this.flag) {
			c = null;
			try {
				//������������
				c = this.surfaceHolder.lockCanvas(null);
				//ͬ��
				synchronized (this.surfaceHolder) {
					//���û��Ʒ���
					welcomeView.onDraw(c);
				}
			} finally {
				//��finally��֤һ����ִ��
				if (c != null) {
					//������Ļ��ʾ����
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
	 * ѭ�����λ��set����
	 * @param flag
	 */
	public void setFlag(boolean flag) { 
		this.flag = flag;
	}
}
