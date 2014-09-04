package com.freedom.welcomeview;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * WelcomeView
 * @author lifen
 */
public class WelcomeView extends SurfaceView implements SurfaceHolder.Callback{
	
	WelcomeActivity activity;
	//ˢ֡�߳�
	WelcomeViewDrawThread welcomeViewDrawThread;
	//���������߳�
	WelcomeViewGoThread welcomeViewGoThread;
	//ͼƬID
	int[] bitmapsID = new int[]{
		R.drawable.w1,R.drawable.w2,
		R.drawable.w3,R.drawable.w4,
		R.drawable.w5,R.drawable.w6,
		R.drawable.w7,R.drawable.w8,
		R.drawable.w9,R.drawable.w10,
		R.drawable.w11,R.drawable.w12,
		R.drawable.w13,R.drawable.w14,
		R.drawable.w15,R.drawable.w16,
		R.drawable.w17,R.drawable.w18,
		R.drawable.w19,R.drawable.w20,
		R.drawable.w21,R.drawable.w22,
		R.drawable.w23,R.drawable.w24,
		R.drawable.w25,R.drawable.w26,
		R.drawable.w27,R.drawable.w28,
		R.drawable.w29,R.drawable.w30,
	};
	//ͼƬ����
	Bitmap[] bitmaps;
	//��ǰ���Ƶڼ�֡
	int drawIndex = 0;
	//�Ƿ��������
	boolean drawString = false;
	//0��ӭ״̬
	int status = 0;
	//����
	Paint paint;
	
	public WelcomeView(WelcomeActivity activity) {
		super(activity);
		this.activity = activity;
		//���Callback�ӿڵ�ʵ��
		getHolder().addCallback(this);
		//���ó�ʼ������
		initBitmap();
		//ˢ֡�߳�
		welcomeViewDrawThread = new WelcomeViewDrawThread(this);
		//���������߳�
		welcomeViewGoThread = new WelcomeViewGoThread(this);
	}
	
	/**
	 * ��ʼ��ͼƬ����
	 */
	public void initBitmap() {
		paint = new Paint();
		paint.setColor(Color.WHITE);
		//ȥ���
		paint.setAntiAlias(true);
		paint.setTextSize(18);
		bitmaps = new Bitmap[bitmapsID.length];
		for (int i = 0; i < bitmaps.length; i++) {
			bitmaps[i] = BitmapFactory.decodeResource(getResources(),
					bitmapsID[i]);
		}
	}
	
	/**
	 * ���Ʒ���
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//�屳��
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(bitmaps[drawIndex], 88, 200, paint);
		if (status == 0) {
			if (drawString) {
				canvas.drawText("�����Ļ����...", 200, 480, paint);
			}
		}else if(status == 1){
			//�����Ļ��Ҫ�����״̬
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		//����ѭ����־λ
		welcomeViewDrawThread.setFlag(true);
		welcomeViewGoThread.setFlag(true);
		//����ˢ֡�߳�
		welcomeViewDrawThread.start();
		//�������������߳�
		welcomeViewGoThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		//ѭ����־λ
		boolean retry = true;
		//����ѭ����־λ
		welcomeViewDrawThread.setFlag(false);
		welcomeViewGoThread.setFlag(false);
		//���ϵ�ѭ����ֱ��ˢ֡�߳̽���
		while (retry) {
			try {
				//�õ�ˢ֡�߳̽���
				welcomeViewDrawThread.join();
				//�õ����������߳̽���
				welcomeViewGoThread.join();
				retry = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}