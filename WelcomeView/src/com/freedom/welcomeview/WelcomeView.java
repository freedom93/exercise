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
	//刷帧线程
	WelcomeViewDrawThread welcomeViewDrawThread;
	//动画生成线程
	WelcomeViewGoThread welcomeViewGoThread;
	//图片ID
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
	//图片数组
	Bitmap[] bitmaps;
	//当前绘制第几帧
	int drawIndex = 0;
	//是否绘制文字
	boolean drawString = false;
	//0欢迎状态
	int status = 0;
	//画笔
	Paint paint;
	
	public WelcomeView(WelcomeActivity activity) {
		super(activity);
		this.activity = activity;
		//添加Callback接口的实现
		getHolder().addCallback(this);
		//调用初始化方法
		initBitmap();
		//刷帧线程
		welcomeViewDrawThread = new WelcomeViewDrawThread(this);
		//动画生成线程
		welcomeViewGoThread = new WelcomeViewGoThread(this);
	}
	
	/**
	 * 初始化图片方法
	 */
	public void initBitmap() {
		paint = new Paint();
		paint.setColor(Color.WHITE);
		//去锯齿
		paint.setAntiAlias(true);
		paint.setTextSize(18);
		bitmaps = new Bitmap[bitmapsID.length];
		for (int i = 0; i < bitmaps.length; i++) {
			bitmaps[i] = BitmapFactory.decodeResource(getResources(),
					bitmapsID[i]);
		}
	}
	
	/**
	 * 绘制方法
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//清背景
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(bitmaps[drawIndex], 88, 200, paint);
		if (status == 0) {
			if (drawString) {
				canvas.drawText("点击屏幕继续...", 200, 480, paint);
			}
		}else if(status == 1){
			//点击屏幕后要处理的状态
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		//设置循环标志位
		welcomeViewDrawThread.setFlag(true);
		welcomeViewGoThread.setFlag(true);
		//启动刷帧线程
		welcomeViewDrawThread.start();
		//启动动画生成线程
		welcomeViewGoThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	
	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		//循环标志位
		boolean retry = true;
		//设置循环标志位
		welcomeViewDrawThread.setFlag(false);
		welcomeViewGoThread.setFlag(false);
		//不断地循环，直到刷帧线程结束
		while (retry) {
			try {
				//得到刷帧线程结束
				welcomeViewDrawThread.join();
				//得到动画生成线程结束
				welcomeViewGoThread.join();
				retry = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}