package com.pgreader.data;

/**
 * move type for a unit.
 */
public enum MoveType {
	/**
	 * Tracked unit.
	 */
	Tracked,     
	/**
	 * Half tracked unit.
	 */
	HalfTracked,
	/**
	 * Wheeled unit.
	 */
	Wheeled,    
	/**
	 * using legs.
	 */
	Leg,        
	/**
	 * Towed.
	 */
	Towed,      
	/**
	 * Air unit.
	 */
	Air,       
	/**
	 * Naval unit.
	 */
	Naval,      
	/**
	 * All Terrain unit.
	 */
	AllTerrain;
	
	/**
	 * Move type values.
	 */
	private static final MoveType[] VALUES = MoveType.values();
	
	/**
	 * retrieve a move type from its index.
	 * @param index index
	 * @return the matching move type
	 */
	public static MoveType from(int index) {
		return VALUES[index];
	}
}
