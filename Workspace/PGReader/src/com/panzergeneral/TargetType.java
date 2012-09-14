package com.panzergeneral;

/** 
 * enum for target type.
 */
public enum TargetType {
	/**
	 * soft target.
	 */
	Soft,
	/** 
	 * hard target.
	 */
	Hard,
	/**
	 * air target.
	 */
	Air,
	/** 
	 * naval target.
	 */
	Naval;
	
	/**
	 * array of values.
	 */
	private static final TargetType[] VALUES = TargetType.values();
	
	/**
	 * retrieve a TargetType from its index.
	 * @param index index value
	 * @return the target type
	 */
	public static TargetType from(int index) {
		return VALUES[index];
	}
}
