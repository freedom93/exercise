package com.freedom.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.freedom.helloworld.R;
import com.freedom.helper.DBDao;
import com.freedom.helper.Item;
import com.freedom.helper.MyBaseAdapter;

public class DbActivity extends Activity {
	
	List<Item> itemlist ;
	MyBaseAdapter adapter = null;
	ListView listView01 = null;
	DBDao dbDao;
	Button add;
	Button modify;
	Button query;
	Button delete;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db);
        //��ȡ���
        listView01 = (ListView)findViewById(R.id.listView01);
        //��ʾListView
        initListAllitem();
        showByMyBaseAdapter();
        add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int count = (int)(dbDao.getCount()+1);				
				Item item = new Item();
				item.setLabel(count);
				item.setItem2("item" + count + ".1");
				item.setItem1("item" + count +".2");
				dbDao.save(item);
				itemlist.add(item);
//				updateByMyBaseAdapter();
				adapter.notifyDataSetChanged();
				Log.i("dbInfo",
						"�ɹ������һ������" + item.item1);
			}
		});
        modify = (Button)findViewById(R.id.modify);
        modify.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int count = 3;				
				Item item = dbDao.find(count);
				item.setItem2("iMod.1");
				item.setItem1("iMod.2");
				dbDao.update(item);
				Item itemMod = itemlist.get(count-1);
				itemMod.setItem2("iMod" + count + ".1");
				itemMod.setItem1("iMod" + count +".2");
				updateByMyBaseAdapter();
//				adapter.notifyDataSetChanged();
				Log.i("dbInfo",
						"�ɹ��޸���һ������" + itemMod.item1);
			}
		});
        query = (Button)findViewById(R.id.query);
        query.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int count = 3;				
				Item item = dbDao.find(count);
				updateByMyBaseAdapter();
//				adapter.notifyDataSetChanged();
				Log.i("dbInfo",
						"�ɹ���ѯ�˵�" + count + "������: "+item.getItem1());
			}
		});
        delete = (Button)findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int count = (int)(dbDao.getCount());				
				dbDao.delete(count-1);
				itemlist.remove(count-1);
				updateByMyBaseAdapter();
//				adapter.notifyDataSetChanged();
				Log.i("dbInfo",
						"�ɹ�ɾ���˵�" + count + "������");
			}
		});
        
        
    }
	
	
	public void initListAllitem(){
		itemlist = new ArrayList<Item>();
		for(int i = 1; i <= 3; i++){
			dbDao=new DBDao(this);
	        Item item = new Item();
	        item.setLabel(i);
	        item.setItem2("item"+i+".1");
	        item.setItem1("item"+i+".2");
	        dbDao.save(item);
			itemlist.add(item);
		}
		
	}
	
	public void showByMyBaseAdapter(){
		adapter = new MyBaseAdapter(this, itemlist);
		listView01.setAdapter(adapter);
	}
	public void updateByMyBaseAdapter(){
		adapter = new MyBaseAdapter(this, itemlist);
		listView01.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

}