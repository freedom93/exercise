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
	//通过一个字符串在连续输入数字时将数字连在一起 eg."8"+"8"="88"
	private String number = "0";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		
		//初始化CaInputView类
		//因为在CaInputView中，定义了两个参数
		//一个是需要传入Activity的实例，那传入自身即可，所以用this
		//第二个参数，目的是想通知CalculatorActivity用户的行为
		//所以理应传入它自身即可，这里会提示错误
		//因为CalculatorActivity没有实现“通讯接口”，点击红X出现提示错误
		civ = new CaInputView(this, this);
		cov = new CaOutputView(this);
		calModel = new CalModel();
		
	/*	
		//这里构造一个测试栈来测试一下popOpOffStack函数
		Stack<String> test = new Stack<String>();
		test.push("2");
		test.push("+");
		test.push("1");
		test.push("X");
		test.push("2");
		test.push("-");
		test.push("3");
		double result = CalModel.popOpOffStack(test);
		
		//利用CaOutputView实例输入
		//由于result是double类型，需要转型显示
		cov.OutputData(String.valueOf(result));
	*/
	}

	@Override
	public void operandIn(String operand) {
		//对首位为“0”作处理
		number = number.equals("0")?operand:number+operand;
		//当回调函数执行时，在CaOutputView中显示,输入运算数时只需要显示
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
		//输入操作符时先将“累计”的number压入栈再将自己压入
		calModel.pushOperand(number);
		double result = calModel.pushOperate(operate);
		//如果是整数则消除小数点 8.0 -> 8
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
