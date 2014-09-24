package com.freedom.gridview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("GridViewActivity");
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));
    }
    
    
    public class ImageAdapter extends BaseAdapter {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {  
                imageView = new ImageView(mContext);
                imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(10, 10, 10, 10);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        private Integer[] mThumbIds = {
        		R.drawable.grid_view_01, R.drawable.grid_view_02,
                R.drawable.grid_view_03, R.drawable.grid_view_04,
                R.drawable.grid_view_05, R.drawable.grid_view_06,
                R.drawable.grid_view_07, R.drawable.grid_view_08,
                R.drawable.grid_view_09, R.drawable.grid_view_10,
                R.drawable.grid_view_11, R.drawable.grid_view_12,
        		R.drawable.picture1,R.drawable.picture2, R.drawable.picture3, 
    			R.drawable.picture4,R.drawable.picture5,R.drawable.picture6, 
    			R.drawable.picture7, R.drawable.picture8,R.drawable.picture9,
    			R.drawable.grid_view_01, R.drawable.grid_view_02,
                R.drawable.grid_view_03, R.drawable.grid_view_04,
                R.drawable.grid_view_05, R.drawable.grid_view_06,
                R.drawable.grid_view_07, R.drawable.grid_view_08,
                R.drawable.grid_view_09, R.drawable.grid_view_10,
                R.drawable.grid_view_11, R.drawable.grid_view_12,
        		R.drawable.picture1,R.drawable.picture2, R.drawable.picture3, 
    			R.drawable.picture4,R.drawable.picture5,R.drawable.picture6, 
    			R.drawable.picture7, R.drawable.picture8,R.drawable.picture9};
    }
 

 
}