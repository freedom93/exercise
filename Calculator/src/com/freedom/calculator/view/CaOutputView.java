package com.freedom.calculator.view;

import com.freedom.calculator.R;

import android.app.Activity;
import android.widget.TextView;

public class CaOutputView {
	
	private TextView tv;
	// 在CaOutputView中，不需要通知Activity发生了什么，显示结果即可
	// 所以只需要Activity提供方法，获取TextView对象用于显示结果即可
	public CaOutputView(Activity ac){
		tv = (TextView)ac.findViewById(R.id.OutputText);
	}
	//定义CaOutputView拥有的方法
	//将输入的结果显示出来
	//函数实质是调用TextView的方法
	//虽然这样显得有点“多余”，但是当Activity类功能扩展或变复杂时，这样的封装就很有意义
	public void OutputData(String str){
		//这里可以添加自己想做的东西，例如对显示结果进行处理 等
		tv.setText(str);
	}

	//可以方便扩展出不同的方法，只需改变自己内部逻辑——MVC的好处之一
}
