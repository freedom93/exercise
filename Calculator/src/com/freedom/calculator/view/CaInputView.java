package com.freedom.calculator.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CaInputView {
	
	//定义“通讯协议”，即接口
	public interface InputHappend{
		
		//当用户输入运算数时，通知你它输入“openrand”这个数字
		public void operandIn(String operand);
		
		//当用户输入操作符时，通知你它输入“openrate”这个操作符
		public void operateIn(String operate);
	}
	
	private List<Button> operands;
	private List<Button> operates;
	
	private CaInputView.InputHappend dlg;
	
	public CaInputView(Activity ac, CaInputView.InputHappend delegate){
		dlg = delegate;
		operands = new ArrayList<Button>();
		operates = new ArrayList<Button>();
		
		//通过Resource类的getIdentifier方法获取相应资源
				Resources res = ac.getResources();
				for(int i=0; i<=10; i++){
					if(i<=8){
						int id_operate = res.getIdentifier("Operate"+i, "id", ac.getPackageName());
						Button btn_operate = (Button)ac.findViewById(id_operate);
						Log.d("CalculatorActivity:", btn_operate.getText().toString());
						operates.add(btn_operate);
						
					}
					int id_operand = res.getIdentifier("Operand"+i, "id", ac.getPackageName());
					Button btn_operand = (Button)ac.findViewById(id_operand);
					Log.d("CalculatorActivity:", btn_operand.getText().toString());
					operands.add(btn_operand);
				}
				
				//为操作用户添加事件，当用户触发时，通知dlg，发生operandIn
				for(Button btn: operands){
					btn.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							Button cli_btn = (Button) v;
							String text = cli_btn.getText().toString();
							dlg.operandIn(text);
							
						}
					});
				}
				
				//为操作用户添加事件，当用户触发时，通知dlg，发生operateIn
				for(Button btn: operates){
					btn.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							Button cli_btn = (Button) v;
							String text = cli_btn.getText().toString();
							dlg.operateIn(text);
							
						}
					});
				}
	}

}
