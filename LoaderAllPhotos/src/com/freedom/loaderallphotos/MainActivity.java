package com.freedom.loaderallphotos;

import android.app.Dialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;

/**
 * @Description: Android实现获取本机中所有图片
 * @author lifen
 *
 */
public class MainActivity extends FragmentActivity implements LoaderCallbacks<Cursor>{
	private Bitmap bitmap = null;
	private byte[] mContent = null;
	
	private ListView listView = null;
	private SimpleCursorAdapter simpleCursorAdapter = null;
	
	private static final String[] STORE_IMAGES = {
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.LATITUDE,
        MediaStore.Images.Media.LONGITUDE,
        MediaStore.Images.Media._ID
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        listView = (ListView)findViewById(android.R.id.list);
        simpleCursorAdapter = new SimpleCursorAdapter(
        		this, 
        		R.layout.simple_list_item, 
        		null, 
        		STORE_IMAGES, 
        		new int[] { R.id.item_title, R.id.item_value}, 
        		0
        		);
        
        simpleCursorAdapter.setViewBinder(new ImageLocationBinder());
        listView.setAdapter(simpleCursorAdapter);
        //初始化加载器
        //注意此处是getSupportLoaderManager()-android-support-v4.jar，而不是getLoaderManager()方法-Android3.0。
        getSupportLoaderManager().initLoader(0, null, this);
        /**
        initLoader()方法的参数：
        1.一个标识加载器的唯一ID。加载器的ID是0。
        2. 一个可选的参数以支持加载器的构建。在本示例中，使用null。
        3. 一个LoaderManager.LoaderCallbacks的实现。
                          被LoaderManager调用以报告装载器的事件，
                          在本示例中实现了LoaderManager.LoaderCallbacks这个接口，
                          因此它传递this这个参数。
         initLoader()方法能够确保加载器被初始化并激活。它有两种可能的结果：
　　		 1. 如果被ID标识的加载器已经存在，那么该加载器将被重新使用。
　　		 2. 如果被ID标识的加载器不存在，initLoader()
                             将激发LoaderManager.LoaderCallbacks的onCreateLoader()方法．
                             返回一个新加载器。
         */
        
        //单击显示图片
        listView.setOnItemClickListener(new ShowItemImageOnClickListener());
    }
    /**
     * 创建新的加载器
     */
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
    	// 为了查看信息，需要用到CursorLoader。
    	CursorLoader cursorLoader = new CursorLoader(
    			this, 
    			MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
    			STORE_IMAGES, 
    			null, 
    			null, 
    			null);
    	return cursorLoader;
    }
    /**
     * 如果我们想放弃旧的数据，则应使用restartLoader()方法。
　　	   LoaderManager.LoaderCallbacks包含以下的方法：
　　	   1. onCreateLoader()：根据所给出的ID，初始化并返回一个新的加载器。
　　          2. onLoadFinished()：当一个先前被创建的加载器完成了它的加载过程时被调用。
　　          3.onLoaderReset()：当一个先前被创建的加载器被重置时被调用，然后使加载器的数据无效。
	        在onLoaderReset()方法中，当一个先前被创建的加载器被重置时该方法会被调用，
	        然后使加载器的数据无效。
	        该回调方法让我们发现数据在什么时候将被释放以便我们删除对它的引用。
	        通常我们实现swapCursor()方法，并给方法传递一个null参数。
     */
    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
    	simpleCursorAdapter.swapCursor(null);
    }
    
    /**
     * 在onLoadFinished()方法中，加载器一旦知道应用不再使用数据时，
     * 加载器将会释放数据。如果数据是一个从CursorLoader来的cursor，
     * 我们不应该调用它自己的close()方法，
     * 如果cursor被放置在CursorAdapter或是SimpleCursorAdapter中，
     * 我们应该使用它自己的swapCursor()方法以使旧的Cursor不被关闭。
     */
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
    	// 使用swapCursor()方法，以使旧的游标不被关闭．
    	simpleCursorAdapter.swapCursor(cursor);
    }
    
    // 将图片的位置绑定到视图
    private class ImageLocationBinder implements ViewBinder{ 
    	@Override
    	public boolean setViewValue(View view, Cursor cursor, int arg2) {
    		if (arg2 == 1) {
    			// 图片经度和纬度
                double latitude = cursor.getDouble(arg2);
                double longitude = cursor.getDouble(arg2 + 1);
                
                if (latitude == 0.0 && longitude == 0.0) {
                    ((TextView)view).setText("位置：未知");
                } else {
                    ((TextView)view).setText("位置：" + latitude + ", " + longitude);
                }
                
                // 需要注意：在使用ViewBinder绑定数据时，必须返回真；否则，SimpleCursorAdapter将会用自己的方式绑定数据。
                return true;
            } else {
                return false;
            }
    	}
    }
    
    // 单击项显示图片事件监听器
    private class ShowItemImageOnClickListener implements OnItemClickListener{
    	@Override
    	public void onItemClick(AdapterView<?> parent, View view, int position,
    			long id) {
    		final Dialog dialog = new Dialog(MainActivity.this);
    		// 以对话框形式显示图片
			dialog.setContentView(R.layout.image_show);
			dialog.setTitle("图片显示");

			ImageView ivImageShow = (ImageView) dialog.findViewById(R.id.ivImageShow);
			Button btnClose = (Button) dialog.findViewById(R.id.btnClose);

			btnClose.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					
					// 释放资源
					if(bitmap != null){
						bitmap.recycle();
					}
				}
			});
			//通过Uri的形式查询到图片
			Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().
					  appendPath(Long.toString(id)).build();
			FileUtil file = new FileUtil();
			ContentResolver resolver = getContentResolver();
			
			// 从Uri中读取图片资源
			try {
				mContent = file.readInputStream(resolver.openInputStream(Uri.parse(uri.toString())));
				bitmap = file.getBitmapFromBytes(mContent, null);
				ivImageShow.setImageBitmap(bitmap);
			} catch (Exception e) {
				e.printStackTrace();
			}

			dialog.show();
    	}
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	
    	if(bitmap != null){
			bitmap.recycle();
		}
    }
}