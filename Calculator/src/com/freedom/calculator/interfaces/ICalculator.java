package com.freedom.calculator.interfaces;

//���ﶨ�������ģ�͵Ľӿ�Լ��
public interface ICalculator {
	
	//���ܲ���������
	public void pushOperand(String operand);
	
	//�������������
	public double pushOperate(String operate);
	
	//��������
	public void reset();
	
	
}
