package com.freedom.calculator;

import java.util.Stack;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.freedom.calculator.interfaces.ICalculator;
import com.freedom.calculator.model.CalModel;
import com.freedom.calculator.view.CaInputView;
import com.freedom.calculator.view.CaInputView.InputHappend;
import com.freedom.calculator.view.CaOutputView;

public class CalculatorActivity extends Activity  implements InputHappend{

	private CaInputView civ;
	private CaOutputView cov;
	private ICalculator calModel;
	//ͨ��һ���ַ�����������������ʱ����������һ�� eg."8"+"8"="88"
	private String number = "0";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		
		//��ʼ��CaInputView��
		//��Ϊ��CaInputView�У���������������
		//һ������Ҫ����Activity��ʵ�����Ǵ��������ɣ�������this
		//�ڶ���������Ŀ������֪ͨCalculatorActivity�û�����Ϊ
		//������Ӧ�����������ɣ��������ʾ����
		//��ΪCalculatorActivityû��ʵ�֡�ͨѶ�ӿڡ��������X������ʾ����
		civ = new CaInputView(this, this);
		cov = new CaOutputView(this);
		calModel = new CalModel();
		
	/*	
		//���ﹹ��һ������ջ������һ��popOpOffStack����
		Stack<String> test = new Stack<String>();
		test.push("2");
		test.push("+");
		test.push("1");
		test.push("X");
		test.push("2");
		test.push("-");
		test.push("3");
		double result = CalModel.popOpOffStack(test);
		
		//����CaOutputViewʵ������
		//����result��double���ͣ���Ҫת����ʾ
		cov.OutputData(String.valueOf(result));
	*/
	}

	@Override
	public void operandIn(String operand) {
		//����λΪ��0��������
		number = number.equals("0")?operand:number+operand;
		//���ص�����ִ��ʱ����CaOutputView����ʾ,����������ʱֻ��Ҫ��ʾ
		cov.OutputData(number);
	}

	@Override
	public void operateIn(String operate) {
		if(operate.equals("c")){
			calModel.reset();
			number = "0";
			cov.OutputData(number);
			return;
		}
		//���������ʱ�Ƚ����ۼơ���numberѹ��ջ�ٽ��Լ�ѹ��
		calModel.pushOperand(number);
		double result = calModel.pushOperate(operate);
		//���������������С���� 8.0 -> 8
		if(result % 1d == 0d){
			int tmp = Double.valueOf(result).intValue();
			cov.OutputData(String.valueOf(tmp));
		}
		else{				
			cov.OutputData(String.valueOf(result));
		}
		number = "0";
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

}
