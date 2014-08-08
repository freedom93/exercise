package com.freedom.helper;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.freedom.activityof3.Activity02;
import com.freedom.activityof3.Activity03;
import com.freedom.helloworld.R;

public class MyBaseAdapter extends BaseAdapter {
	private List<Item> item;
	Context context;
	static final int REQUEST_CODE = 1;

	public MyBaseAdapter(Context context, List<Item> item) {
		this.item = item;
		this.context = context;
	}

	@Override
	public int getCount() {
		return (item == null) ? 0 : item.size();
	}

	@Override
	public Object getItem(int position) {
		return item.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public class ViewHolder {
		TextView textViewItem01;
		TextView textViewItem02;
		TextView textViewItem03;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Item item = (Item) getItem(position);
		ViewHolder viewHolder = null;
		if (convertView == null) {
			Log.d("MyBaseAdapter", "新建convertView,position=" + position);
			convertView = LayoutInflater.from(context).inflate(R.layout.item,
					null);

			viewHolder = new ViewHolder();
			viewHolder.textViewItem01 = (TextView) convertView
					.findViewById(R.id.listView01Item01);
			viewHolder.textViewItem02 = (TextView) convertView
					.findViewById(R.id.listView01Item02);
			viewHolder.textViewItem03 = (TextView) convertView
					.findViewById(R.id.listView01Item03);
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
			Log.d("MyBaseAdapter", "旧的convertView,position=" + position);
		}
		viewHolder.textViewItem01.setText(String.valueOf(item.label));
		viewHolder.textViewItem02.setText(item.item2);
		viewHolder.textViewItem03.setText(item.item1);
		// 对ListView中第2个TextView配置OnClick事件
		viewHolder.textViewItem02.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Toast.makeText(context,
//						"[textViewItem1.setOnClickListener]点击了" + item.item2,
//						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context.getApplicationContext(), Activity03.class);
				intent.putExtra("Activity01", "数据来自Activity01");
				intent.putExtra("id", "1");
				((Activity) context).startActivityForResult(intent, REQUEST_CODE);
			}
		});
		// 对ListView中第3个TextView配置OnClick事件
		viewHolder.textViewItem03.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				Toast.makeText(context,
//						"[textViewItem2.setOnClickListener]点击了" + item.item1,
//						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context.getApplicationContext(), Activity02.class);
				intent.putExtra("Activity01", "数据来自Activity01");
				intent.putExtra("id", "1");
				((Activity) context).startActivityForResult(intent, REQUEST_CODE);
			}
		});

		return convertView;
	}

}
