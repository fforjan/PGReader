package com.panzergeneral;

public enum TargetType {
	Soft,
	Hard,
	Air,
	Naval;
	
	private static final TargetType[] Values = TargetType.values();
	
	public static TargetType From(int index) {
		return Values[index];
	}
}
