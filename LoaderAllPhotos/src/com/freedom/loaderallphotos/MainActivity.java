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
 * @Description: Androidʵ�ֻ�ȡ����������ͼƬ
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
        //��ʼ��������
        //ע��˴���getSupportLoaderManager()-android-support-v4.jar��������getLoaderManager()����-Android3.0��
        getSupportLoaderManager().initLoader(0, null, this);
        /**
        initLoader()�����Ĳ�����
        1.һ����ʶ��������ΨһID����������ID��0��
        2. һ����ѡ�Ĳ�����֧�ּ������Ĺ������ڱ�ʾ���У�ʹ��null��
        3. һ��LoaderManager.LoaderCallbacks��ʵ�֡�
                          ��LoaderManager�����Ա���װ�������¼���
                          �ڱ�ʾ����ʵ����LoaderManager.LoaderCallbacks����ӿڣ�
                          ���������this���������
         initLoader()�����ܹ�ȷ������������ʼ��������������ֿ��ܵĽ����
����		 1. �����ID��ʶ�ļ������Ѿ����ڣ���ô�ü�������������ʹ�á�
����		 2. �����ID��ʶ�ļ����������ڣ�initLoader()
                             ������LoaderManager.LoaderCallbacks��onCreateLoader()������
                             ����һ���¼�������
         */
        
        //������ʾͼƬ
        listView.setOnItemClickListener(new ShowItemImageOnClickListener());
    }
    /**
     * �����µļ�����
     */
    @Override
    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
    	// Ϊ�˲鿴��Ϣ����Ҫ�õ�CursorLoader��
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
     * �������������ɵ����ݣ���Ӧʹ��restartLoader()������
����	   LoaderManager.LoaderCallbacks�������µķ�����
����	   1. onCreateLoader()��������������ID����ʼ��������һ���µļ�������
����          2. onLoadFinished()����һ����ǰ�������ļ�������������ļ��ع���ʱ�����á�
����          3.onLoaderReset()����һ����ǰ�������ļ�����������ʱ�����ã�Ȼ��ʹ��������������Ч��
	        ��onLoaderReset()�����У���һ����ǰ�������ļ�����������ʱ�÷����ᱻ���ã�
	        Ȼ��ʹ��������������Ч��
	        �ûص����������Ƿ���������ʲôʱ�򽫱��ͷ��Ա�����ɾ�����������á�
	        ͨ������ʵ��swapCursor()������������������һ��null������
     */
    @Override
    public void onLoaderReset(Loader<Cursor> arg0) {
    	simpleCursorAdapter.swapCursor(null);
    }
    
    /**
     * ��onLoadFinished()�����У�������һ��֪��Ӧ�ò���ʹ������ʱ��
     * �����������ͷ����ݡ����������һ����CursorLoader����cursor��
     * ���ǲ�Ӧ�õ������Լ���close()������
     * ���cursor��������CursorAdapter����SimpleCursorAdapter�У�
     * ����Ӧ��ʹ�����Լ���swapCursor()������ʹ�ɵ�Cursor�����رա�
     */
    @Override
    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
    	// ʹ��swapCursor()��������ʹ�ɵ��α겻���رգ�
    	simpleCursorAdapter.swapCursor(cursor);
    }
    
    // ��ͼƬ��λ�ð󶨵���ͼ
    private class ImageLocationBinder implements ViewBinder{ 
    	@Override
    	public boolean setViewValue(View view, Cursor cursor, int arg2) {
    		if (arg2 == 1) {
    			// ͼƬ���Ⱥ�γ��
                double latitude = cursor.getDouble(arg2);
                double longitude = cursor.getDouble(arg2 + 1);
                
                if (latitude == 0.0 && longitude == 0.0) {
                    ((TextView)view).setText("λ�ã�δ֪");
                } else {
                    ((TextView)view).setText("λ�ã�" + latitude + ", " + longitude);
                }
                
                // ��Ҫע�⣺��ʹ��ViewBinder������ʱ�����뷵���棻����SimpleCursorAdapter�������Լ��ķ�ʽ�����ݡ�
                return true;
            } else {
                return false;
            }
    	}
    }
    
    // ��������ʾͼƬ�¼�������
    private class ShowItemImageOnClickListener implements OnItemClickListener{
    	@Override
    	public void onItemClick(AdapterView<?> parent, View view, int position,
    			long id) {
    		final Dialog dialog = new Dialog(MainActivity.this);
    		// �ԶԻ�����ʽ��ʾͼƬ
			dialog.setContentView(R.layout.image_show);
			dialog.setTitle("ͼƬ��ʾ");

			ImageView ivImageShow = (ImageView) dialog.findViewById(R.id.ivImageShow);
			Button btnClose = (Button) dialog.findViewById(R.id.btnClose);

			btnClose.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					
					// �ͷ���Դ
					if(bitmap != null){
						bitmap.recycle();
					}
				}
			});
			//ͨ��Uri����ʽ��ѯ��ͼƬ
			Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI.buildUpon().
					  appendPath(Long.toString(id)).build();
			FileUtil file = new FileUtil();
			ContentResolver resolver = getContentResolver();
			
			// ��Uri�ж�ȡͼƬ��Դ
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