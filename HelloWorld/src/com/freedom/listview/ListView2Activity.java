package com.freedom.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.freedom.helloworld.R;
import com.freedom.helper.Item;
import com.freedom.helper.MyBaseAdapter;

public class ListView2Activity extends Activity {
	
	//����Person
	List<Item> itemlist ;
	ListAdapter adapter = null;
	ListView listView01 = null;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //��ȡ���
        listView01 = (ListView)findViewById(R.id.listView01);
        
        //��ʾListView
        initListAllitem();
        showByMyBaseAdapter();
        
        //��ʼ��ListView���¼�
       // initListView01Event();
        
        OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int position, long arg3) {
				setTitle("��ѡ�е���:  "+parent.getItemAtPosition(position).toString());
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				setTitle("");
				
			}
			
		};
		listView01.setOnItemSelectedListener(itemSelectedListener);
    }
	
	public void initListAllitem(){
		itemlist = new ArrayList<Item>();
		for(int i = 1; i <= 20; i++){
			itemlist.add(new Item(i,"item"+i+".1","item"+i+".2"));
		}
	}
	
	public void showByMyBaseAdapter(){
		adapter = new MyBaseAdapter(this, itemlist);
		listView01.setAdapter(adapter);
	}

	
	

	/**
	 * ��ʼ��listView01���¼�.
	 */
	public void initListView01Event(){
		
		//ListView��item����¼�
		listView01.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), 
						"[OnItemClickListener]����ˣ�"+itemlist.get(position).item1, 
						Toast.LENGTH_SHORT).show();
			}
		});
		
		//ListView��item��������¼�
		listView01.setOnItemLongClickListener(new OnItemLongClickListener(){

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), 
						"[OnItemLongClickListener]����ˣ�"+itemlist.get(position).item1, 
						Toast.LENGTH_SHORT).show();
				return false;
			}
			
		});
		
		//ListView�ļ���ѡ���¼�(ֱ�Ӵ�����Ļѡ�в��ἤ��)
		listView01.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Toast.makeText(getApplicationContext(), 
						"[OnItemSelectedListener:onItemSelected]����ˣ�"
+itemlist.get(position).item1, 
						Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Toast.makeText(getApplicationContext(), 
						"[OnItemSelectedListener:onNothingSelected]�����", 
						Toast.LENGTH_SHORT).show();
			}
			
		});
	}
}