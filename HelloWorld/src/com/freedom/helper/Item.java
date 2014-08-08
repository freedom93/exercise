package com.freedom.helper;

public class Item {
	
	public int label;
	public String item1;
	public String item2;
	
	public Item(){
		
	}
	public Item(int label, String item1, String item2) {
		super();
		this.label = label;
		this.item1 = item1;
		this.item2 = item2;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}
	
	
}
