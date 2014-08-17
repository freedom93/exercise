package com.freedom.calculator.model;

import java.util.Stack;

import com.freedom.calculator.interfaces.ICalculator;

public class CalModel implements ICalculator{
	
	//计算器算法
	public static double popOpOffStack(Stack<String> stack){
		
		double result = 0;
		//从栈中获取运算数，由于运算数以字符串的形式存储
		//在用做计算时需转型为可计算类型，这里是double
		double operand = Double.valueOf(stack.pop());
		//从栈中获取元素假如栈已空，则直接返回operand
		if(stack.isEmpty()){
			return operand;
		}
		
		//继续从栈中获取操作符，根据操作符类型继续递归调用
		String operate = stack.pop();
		if(operate.equals("+")){
			result = CalModel.popOpOffStack(stack)+operand;
		}
		else if(operate.equals("-")){
			result = CalModel.popOpOffStack(stack)-operand;
		}
		else if(operate.equals("x")){
			result = CalModel.popOpOffStack(stack)*operand;
		}
		else if(operate.equals("÷")){
			result = CalModel.popOpOffStack(stack)/operand;
		}
		else if(operate.equals("%")){
			result = CalModel.popOpOffStack(stack)%operand;
		}
		else if(operate.equals("±")){
			operand = 0 - operand;
			result = CalModel.popOpOffStack(stack)+operand;
		}
		return result;
	}
	
	//记录输入的运算数和操作符的栈
	private Stack<String> dataStack = new Stack<String>();
	//是否在输入操作符、对连续输入操作符的情况视为操作符的替换
	private boolean isOperate = false;

	@Override
	public void pushOperand(String operand) {
		//当输入运算数时直接压入stack，不会触发计算
		dataStack.add(operand);
		isOperate = false;
	}

	@Override
	public double pushOperate(String operate) {
		//当操作符是“+、-、x、÷、%”时，输入继续
		//所以copy一份当前栈的数据作为参数传入进行计算并返回
		//当操作符是“=”号时，则直接使用当前栈作为参数 
		//因为“=”号意味之后需要重新输入
		double result = 0;
		if(isOperate){
			//如果当前是一个操作符，则将他替换
			dataStack.pop();
		}
		
		if(operate.equals("=")){
			result = CalModel.popOpOffStack(dataStack);
		}
		else{
			Stack<String> tmpStack = (Stack<String>)dataStack.clone();
			result = CalModel.popOpOffStack(tmpStack);
			//计算完前面结果把输入的运算符压入栈
			dataStack.add(operate);
			isOperate = true;
		}
		return result;
	}

	@Override
	public void reset() {
		dataStack.removeAllElements();
		isOperate = false;
		
	}

}
