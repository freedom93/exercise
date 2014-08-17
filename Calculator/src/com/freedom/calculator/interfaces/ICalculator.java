package com.freedom.calculator.interfaces;

//这里定义计算器模型的接口约束
public interface ICalculator {
	
	//接受操作符输入
	public void pushOperand(String operand);
	
	//接受运算符输入
	public double pushOperate(String operate);
	
	//重置清零
	public void reset();
	
	
}
