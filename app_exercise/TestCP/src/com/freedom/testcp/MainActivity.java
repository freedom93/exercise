package com.freedom.testcp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private View v1;
	private View v2;
	
	private ViewGroup mContainer; 
	private Boolean flag = false;
	
	ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v1 = inflater.inflate(R.layout.activity_calculator, null);  
		v2 = inflater.inflate(R.layout.main, null);
		mContainer = (ViewGroup) findViewById(R.id.container);
//	    img = (ImageView) findViewById(R.id.picture);
//	    img.setImageResource(R.drawable.picture);
		mContainer.addView(v1);
		mContainer.addView(v2);
		v1.setVisibility(View.VISIBLE);
		v2.setVisibility(View.INVISIBLE);
		v1.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				flag = true;
		        applyRotation(flag, 0, 90); 
				
			}
		});
		v2.setClickable(true);    
        v2.setFocusable(true);  
        //点击v2时，返回v1    
        v2.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				 flag = false;
				 applyRotation(flag, 360, 270);   
				
			}
		}); 
       
      
      //设置需要保存缓存     
        mContainer.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);  
	}

	/**     
     * 使用Rotate3d实现翻转
     * @param 
     * @param start 翻转起始角度   
     * @param end 翻转终止角度   
     */   
    private void applyRotation(Boolean flag, float start, float end) {    
        // 计算中心点     
        final float centerX = mContainer.getWidth() / 2.0f;    
        final float centerY = mContainer.getHeight() / 2.0f;    
     
        final Rotate3dAnimation rotation =    
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);    
        rotation.setDuration(500);    
        rotation.setFillAfter(true);    
        rotation.setInterpolator(new AccelerateInterpolator());    
        //设置监听     
        rotation.setAnimationListener(new DisplayNextView(flag));    
   
        mContainer.startAnimation(rotation);    
    }    
    /**
	 * 用于监听前90度翻转完成
	 */ 
    private final class DisplayNextView implements Animation.AnimationListener {    
        private final Boolean tflag;    
   
        private DisplayNextView(Boolean flag) {    
        	tflag = flag;    
        }    
   
        public void onAnimationStart(Animation animation) {    
        }    
        //动画结束     
        public void onAnimationEnd(Animation animation) {    
            mContainer.post(new SwapViews(tflag));    
        }    
   
        public void onAnimationRepeat(Animation animation) {    
        }    
    }    
   
    /**
	 * 用于翻转剩下的90度
	 */ 
    private final class SwapViews implements Runnable {    
        private final Boolean tflag;    
   
        public SwapViews(Boolean flag) {    
        	tflag = flag;  
        }    
   
        public void run() {    
            final float centerX = mContainer.getWidth() / 2.0f;    
            final float centerY = mContainer.getHeight() / 2.0f;    
            Rotate3dAnimation rotation;    
                
            if (tflag == true) {    
                //显示v2    
                v1.setVisibility(View.GONE);    
                v2.setVisibility(View.VISIBLE);    
                v2.requestFocus();    
   
                rotation = new Rotate3dAnimation(-90, 0, centerX, centerY, 310.0f, false);    
            } else {    
                //返回v1    
                v2.setVisibility(View.GONE);    
                v1.setVisibility(View.VISIBLE);    
                v1.requestFocus();    
   
                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);    
            }    
   
            rotation.setDuration(500);    
            rotation.setFillAfter(true);    
            rotation.setInterpolator(new DecelerateInterpolator());    
            //开始动画     
            mContainer.startAnimation(rotation);    
        }    
    }    

}
