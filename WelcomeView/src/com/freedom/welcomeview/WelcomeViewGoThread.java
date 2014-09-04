package com.freedom.welcomeview;

/**
 * ���������߳�
 * @author lifen
 *
 */
public class WelcomeViewGoThread extends Thread {
	WelcomeView welcomeView;
	private int sleepSpan = 150;
	//ѭ�����λ
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
	 * ѭ����־λ�����÷���
	 * @param flag
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}