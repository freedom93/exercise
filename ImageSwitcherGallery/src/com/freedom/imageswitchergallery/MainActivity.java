package com.freedom.imageswitchergallery;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity implements
		OnItemClickListener, ViewFactory {

	private Gallery mGallery;
	private ImageSwitcher mImageSwitcher;
	private SimpleAdapter mSimpleAdapter;
	private List<Map<String, Integer>> mList = new ArrayList<Map<String, Integer>>();

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mGallery = (Gallery) findViewById(R.id.gallery);
		mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

		initSimpleAdapter();
		mGallery.setAdapter(mSimpleAdapter);
		mGallery.setOnItemClickListener(this);

		// 设置工厂，实现ViewFactory的makeView方法生成ImageView给ImageSwitcher
		mImageSwitcher.setFactory(this);
	}

	private void initSimpleAdapter() {
		// private int[] arrayImages = new int[] { R.drawable.picture1,
		// R.drawable.picture2, R.drawable.picture3, R.drawable.picture4,
		// R.drawable.picture5,R.drawable.picture6, R.drawable.picture7, 
		// R.drawable.picture8,R.drawable.picture9};
		// 以上的方法之适用显示比较少的图片，要是现在要显示1000张图片呢，不可能把1000张图片一个一个列出来
		// 可以使用Java的反射机制来解决此问题，使用反射机制动态加载（最好图片的命名要有规律）
		Field[] fields = R.drawable.class.getDeclaredFields(); // 取得drawable下的全部属性
		// 循环取得的属性，以picture开头的都是要显示的图片，保存到list里面
		for (Field field : fields) {
			if (field.getName().startsWith("picture")) {
				Map<String, Integer> map = new HashMap<String, Integer>();
				try {
					map.put("image", field.getInt(R.drawable.class));
				} catch (Exception e) {
					e.printStackTrace();
				}
				mList.add(map);
			}
		}
		mSimpleAdapter = new SimpleAdapter(this, mList, R.layout.gallery_item,
				new String[] { "image" }, new int[] { R.id.image });
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		@SuppressWarnings("unchecked")
		Map<String, Integer> map = (Map<String, Integer>) parent.getAdapter()
				.getItem(position);
		mImageSwitcher.setImageResource(map.get("image"));
	}

	@Override
	public View makeView() {
		ImageView imageView = new ImageView(this);
		imageView.setBackgroundColor(0xFFFFFFFF);
		// 设置居中对齐
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		imageView.setLayoutParams(new ImageSwitcher.LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		return imageView;
	}
}