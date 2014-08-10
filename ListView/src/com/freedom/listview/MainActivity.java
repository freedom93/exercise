package com.freedom.listview;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnScrollListener{
	
	private ListView listview;
	private ArrayList<HashMap<String, String>> array ;
	private Button btn;
	private ProgressBar progressBar;
	private TextView text;
	private TextView update;
	private TextView add;
	private EditText addEdit;
	
	 // ListView底部View
	 private View moreView;
	 private Handler handler;
	 
	 // 设置一个最大的数据条数，超过即不再加载
	 private int MaxDateNum = 5000;
	 // 最后可见条目的索引
	 private int lastVisibleIndex;
	 private  ListViewAdpter adapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ints();
		handler = new Handler();
		array = new ArrayList<HashMap<String,String>>();
		add();//初始化加载10个Item 
	    adapter = new ListViewAdpter(this, array);
		listview.addFooterView(moreView);//加载listview底部布局
		listview.setAdapter(adapter);
		listview.setOnScrollListener(this);
		
		//item的增删改查
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {
				LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			    View addView = inflater.inflate(R.layout.add, null);
				add =(TextView) addView.findViewById(R.id.add);
				final View addView_item = inflater.inflate(R.layout.add_item, null);
				addEdit = (EditText)addView_item.findViewById(R.id.editadd1);
				final Dialog dialog = new AlertDialog.Builder(MainActivity.this)
				.setMessage("选择")
				.setNegativeButton("修改", new  DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
						View myView = inflater.inflate(R.layout.chose, null);
						
						update = (TextView) myView.findViewById(R.id.Update);
						String s = array.get(position).get("name");
						update.setText(s);	
						Dialog dialog1 = new AlertDialog.Builder(MainActivity.this)
						.setMessage("确定修改")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								String ss = update.getText().toString();
								array.get(position).put("name", ss);
								adapter.notifyDataSetChanged();
															
							}
						})
						
						.setNegativeButton("取消", new OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								
								
							}
						}).setView(myView).create();
						
						dialog1.show();
						
					}
				}).setPositiveButton("删除", new  DialogInterface.OnClickListener() {
					
					
					public void onClick(DialogInterface dialog, int which) {
						
						Toast.makeText(MainActivity.this, "hehe", 500).show();
						array.remove(position);
							 adapter.notifyDataSetChanged();
			
					}
				}).setView(addView).create();
				dialog.show();
				add.setOnClickListener(new View.OnClickListener() {
					
					public void onClick(View v) {
						Dialog addDialog = new AlertDialog.Builder(MainActivity.this).
								setMessage("确认添加").
								setPositiveButton("确认", new OnClickListener() {
									
									public void onClick(DialogInterface dialog, int which) {
										  int i = array.size();
										  HashMap<String, String> map = new HashMap<String, String>();
										  String kk = addEdit.getText().toString();
										    map.put("name", "添加的："+kk);
										    array.add(map);
										    adapter.notifyDataSetChanged();
										
									}
								}).setNegativeButton("取消", new OnClickListener() {									
									public void onClick(DialogInterface dialog, int which) {
										
									}
								}).setView(addView_item).create();
						addDialog.show();
								
						
					 dialog.cancel();
						
					}
				});
				return false;
			}
		});
	}
	
	private void ints() {
		listview = (ListView) findViewById(R.id.listview);
		// 实例化底部布局
		moreView = getLayoutInflater().inflate(R.layout.moredata, null);
		btn = (Button) moreView.findViewById(R.id.bt_load);
		progressBar = (ProgressBar) moreView.findViewById(R.id.progressBar);
		text =(TextView) moreView. findViewById(R.id.bt_text);
		
	}


	private void add() {
	   for (int i = 0; i < 10; i++) {
		   HashMap<String, String> map = new HashMap<String, String>();
		   map.put("name", "name"+i);
		   array.add(map);
	  }
		
  }
	
	 private void loadMoreDate() {
		 int count = adapter.getCount();
		
		  if (count + 5 < MaxDateNum) {
		   // 每次加载5条
		   for (int i = count; i < count + 5; i++)   {
			    HashMap<String, String> map = new HashMap<String, String>();
			    map.put("name", "Item"+i);
			    array.add(map);
		   }
		  } else{
		   // 数据已经不足5条
		   for (int i = count; i < MaxDateNum; i++){
			    HashMap<String, String> map = new HashMap<String, String>();
			    map.put("name", "Item"+i);
			   array.add(map);
		   }
	     }
	 }

	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		 // 计算最后可见条目的索引
		  lastVisibleIndex = firstVisibleItem + visibleItemCount - 1;
		  // 所有的条目已经和最大条数相等，则移除底部的View
		  if (totalItemCount == MaxDateNum + 1){
		   listview.removeFooterView(moreView);
		   Toast.makeText(this, "数据全部加载完成，没有更多数据！", Toast.LENGTH_LONG).show();
		  }
		
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		 // 滑到底部后自动加载，判断listview已经停止滚动并且最后可视的条目等于adapter的条目
		  if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && lastVisibleIndex == adapter.getCount())
		  {
		   // 当滑到底部时自动加载
		    progressBar.setVisibility(View.VISIBLE);
		    text.setVisibility(View.VISIBLE);
		    btn.setVisibility(View.GONE);
		    handler.postDelayed(new Runnable() {
		    public void run() {
		    loadMoreDate();
		    btn.setVisibility(View.VISIBLE);
		    progressBar.setVisibility(View.GONE);
		    adapter.notifyDataSetChanged();
		    }
		    }, 2000);
		  }	
	}
}
