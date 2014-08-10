package com.freedom.listview;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdpter extends BaseAdapter {

	private Context context;
	private ArrayList<HashMap<String, String>> array;
	private LayoutInflater inflater;

	public ListViewAdpter(Context context,ArrayList<HashMap<String, String>> array) {
		this.context = context;
		this.array = array;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {

		return array.size();
	}

	public Object getItem(int position) {

		return array.get(position);
	}

	public long getItemId(int position) {

		return position;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new Holder();
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.text = (TextView) convertView.findViewById(R.id.text);
			holder.delete1 = (TextView) convertView.findViewById(R.id.delete1);
			holder.update1 = (TextView) convertView.findViewById(R.id.update1);
			holder.add1 = (TextView) convertView.findViewById(R.id.add1);
			holder.query = (TextView) convertView.findViewById(R.id.query);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.text.setText(array.get(position).get("name"));// 删除
		holder.delete1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				array.remove(position);
				ListViewAdpter.this.notifyDataSetChanged();

			}
		});

		holder.update1.setOnClickListener(new View.OnClickListener() {// 修改
			public void onClick(View v) {
				LayoutInflater inflater = LayoutInflater.from(context);
				final View myView = inflater.inflate(R.layout.update1,null);

				Dialog dialog = new AlertDialog.Builder(context)
							 .setMessage("确定修改吗")
						     .setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int which) {
									EditText editdelet1 = (EditText) myView.findViewById(R.id.editupdate1);
									String ss = editdelet1.getText().toString();
									array.get(position).put("name",ss);
									ListViewAdpter.this.notifyDataSetChanged();
								}
							}).setView(myView).create();
						dialog.show();
			}
		});

		holder.add1.setOnClickListener(new View.OnClickListener() {// 添加
			public void onClick(View v) {
				LayoutInflater inflater = LayoutInflater.from(context);
				final View myView = inflater.inflate(R.layout.update1,null);
				TextView textadd = (TextView) myView.findViewById(R.id.textupdate1);
				textadd.setText("添加");
				Dialog dialog = new AlertDialog.Builder(context)
								.setMessage("确定添加吗")
								.setPositiveButton("确定",new DialogInterface.OnClickListener() {
									public void onClick(
											DialogInterface dialog,
											int which) {EditText editdelet1 = (EditText) myView.findViewById(R.id.editupdate1);
											String ss = editdelet1.getText().toString();
											HashMap<String, String> map = new HashMap<String, String>();
											map.put("name", ss);
											array.add(position + 1, map);
											ListViewAdpter.this.notifyDataSetChanged();
									}
								}).setView(myView).create();
				dialog.show();
			}
	});

	holder.query.setOnClickListener(new View.OnClickListener() {// 精确查询，模糊查询没有搞定
		public void onClick(View v) {
		LayoutInflater inflater = LayoutInflater.from(context);
	    final View myView = inflater.inflate(R.layout.update1,null);
		TextView textadd = (TextView) myView.findViewById(R.id.textupdate1);
		textadd.setText("查询");
		Dialog dialog = new AlertDialog.Builder(context)
						.setMessage("确定查询吗")
						.setPositiveButton("确定",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int which) {
							              EditText editdelet1 = (EditText) myView.findViewById(R.id.editupdate1);
												String ss = editdelet1.getText().toString();
												for (int i = 0; i < array.size(); i++) {
													if (ss.equals(array.get(i).get("name"))) {
														array.clear();
														HashMap<String, String> map = new HashMap<String, String>();
														map.put("name", ss);
														array.add(map);
														ListViewAdpter.this.notifyDataSetChanged();
											}
										}
									ListViewAdpter.this.notifyDataSetChanged();
								}
						}).setView(myView).create();
				dialog.show();

			}
		});

		return convertView;
	}

	class Holder {
		ImageView image;
		TextView text;
		TextView delete1;
		TextView update1;
		TextView add1;
		TextView query;
	}
}
