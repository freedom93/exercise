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
	
	 // ListView�ײ�View
	 private View moreView;
	 private Handler handler;
	 
	 // ����һ�������������������������ټ���
	 private int MaxDateNum = 5000;
	 // ���ɼ���Ŀ������
	 private int lastVisibleIndex;
	 private  ListViewAdpter adapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ints();
		handler = new Handler();
		array = new ArrayList<HashMap<String,String>>();
		add();//��ʼ������10��Item 
	    adapter = new ListViewAdpter(this, array);
		listview.addFooterView(moreView);//����listview�ײ�����
		listview.setAdapter(adapter);
		listview.setOnScrollListener(this);
		
		//item����ɾ�Ĳ�
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {
				LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
			    View addView = inflater.inflate(R.layout.add, null);
				add =(TextView) addView.findViewById(R.id.add);
				final View addView_item = inflater.inflate(R.layout.add_item, null);
				addEdit = (EditText)addView_item.findViewById(R.id.editadd1);
				final Dialog dialog = new AlertDialog.Builder(MainActivity.this)
				.setMessage("ѡ��")
				.setNegativeButton("�޸�", new  DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
						View myView = inflater.inflate(R.layout.chose, null);
						
						update = (TextView) myView.findViewById(R.id.Update);
						String s = array.get(position).get("name");
						update.setText(s);	
						Dialog dialog1 = new AlertDialog.Builder(MainActivity.this)
						.setMessage("ȷ���޸�")
						.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								String ss = update.getText().toString();
								array.get(position).put("name", ss);
								adapter.notifyDataSetChanged();
															
							}
						})
						
						.setNegativeButton("ȡ��", new OnClickListener() {
							
							public void onClick(DialogInterface dialog, int which) {
								
								
							}
						}).setView(myView).create();
						
						dialog1.show();
						
					}
				}).setPositiveButton("ɾ��", new  DialogInterface.OnClickListener() {
					
					
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
								setMessage("ȷ�����").
								setPositiveButton("ȷ��", new OnClickListener() {
									
									public void onClick(DialogInterface dialog, int which) {
										  int i = array.size();
										  HashMap<String, String> map = new HashMap<String, String>();
										  String kk = addEdit.getText().toString();
										    map.put("name", "��ӵģ�"+kk);
										    array.add(map);
										    adapter.notifyDataSetChanged();
										
									}
								}).setNegativeButton("ȡ��", new OnClickListener() {									
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
		// ʵ�����ײ�����
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
		   // ÿ�μ���5��
		   for (int i = count; i < count + 5; i++)   {
			    HashMap<String, String> map = new HashMap<String, String>();
			    map.put("name", "Item"+i);
			    array.add(map);
		   }
		  } else{
		   // �����Ѿ�����5��
		   for (int i = count; i < MaxDateNum; i++){
			    HashMap<String, String> map = new HashMap<String, String>();
			    map.put("name", "Item"+i);
			   array.add(map);
		   }
	     }
	 }

	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		 // �������ɼ���Ŀ������
		  lastVisibleIndex = firstVisibleItem + visibleItemCount - 1;
		  // ���е���Ŀ�Ѿ������������ȣ����Ƴ��ײ���View
		  if (totalItemCount == MaxDateNum + 1){
		   listview.removeFooterView(moreView);
		   Toast.makeText(this, "����ȫ��������ɣ�û�и������ݣ�", Toast.LENGTH_LONG).show();
		  }
		
	}

	public void onScrollStateChanged(AbsListView view, int scrollState) {
		 // �����ײ����Զ����أ��ж�listview�Ѿ�ֹͣ�������������ӵ���Ŀ����adapter����Ŀ
		  if (scrollState == OnScrollListener.SCROLL_STATE_IDLE && lastVisibleIndex == adapter.getCount())
		  {
		   // �������ײ�ʱ�Զ�����
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
