package com.freedom.calculator.view;

import com.freedom.calculator.R;

import android.app.Activity;
import android.widget.TextView;

public class CaOutputView {
	
	private TextView tv;
	// ��CaOutputView�У�����Ҫ֪ͨActivity������ʲô����ʾ�������
	// ����ֻ��ҪActivity�ṩ��������ȡTextView����������ʾ�������
	public CaOutputView(Activity ac){
		tv = (TextView)ac.findViewById(R.id.OutputText);
	}
	//����CaOutputViewӵ�еķ���
	//������Ľ����ʾ����
	//����ʵ���ǵ���TextView�ķ���
	//��Ȼ�����Ե��е㡰���ࡱ�����ǵ�Activity�๦����չ��临��ʱ�������ķ�װ�ͺ�������
	public void OutputData(String str){
		//�����������Լ������Ķ������������ʾ������д��� ��
		tv.setText(str);
	}

	//���Է�����չ����ͬ�ķ�����ֻ��ı��Լ��ڲ��߼�����MVC�ĺô�֮һ
}
