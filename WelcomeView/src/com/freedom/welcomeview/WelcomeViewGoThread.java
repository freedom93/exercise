package com.freedom.welcomeview;

/**
 * 动画生成线程
 * @author lifen
 *
 */
public class WelcomeViewGoThread extends Thread {
	WelcomeView welcomeView;
	private int sleepSpan = 150;
	//循环标记位
	private boolean flag = true;

	public WelcomeViewGoThread(WelcomeView welcomeView) {
		this.welcomeView = welcomeView;
	}

	@Override
	public void run() {
		while (flag) {
			welcomeView.drawIndex++;
			if (welcomeView.drawIndex > welcomeView.bitmapsID.length - 1) {
				welcomeView.drawIndex = welcomeView.bitmapsID.length - 10;
			}
			if (welcomeView.drawIndex % 5 == 0) {
				welcomeView.drawString = !welcomeView.drawString;
			}
			try {
				Thread.sleep(sleepSpan);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 循环标志位的设置方法
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}