package com.pgreader.data; 

/** 
 * Enum for the unit class.
 */
public enum UnitClass {
	/**
	 * Infantry.
	 */
	INFANTRY,
	/**
	 * Tank.
	 */
	TANK,
	/**
	 * Recon.
	 */
	RECON,
	/**
	 * Anti-tank.
	 */
	ANTI_TANK,
	/**
	 * Artillery.
	 */
	ARTILLERY,
	/**
	 * Anti Aircraft.
	 */
	ANTI_AIRCRAFT,
	/**
	 * Air defense.
	 */
	AIR_DEFENSE,
	/**
	 * Fort.
	 */
	FORT, 
	/**
	 * Fighter.
	 */
	FIGHTER,
	/**
	 * Tactical bomber.
	 */
	TACBOMBER,
	/**
	 * Level bomber.
	 */
	LEVBOMBER, 
	/**
	 * Submarine.
	 */
	SUBMARINE,
	/**
	 * Destroyer.
	 */
	DESTROYER, 
	/**
	 * Capital.
	 */
	CAPITAL,
	/**
	 * Carrier.
	 */
	CARRIER, 
	/**
	 * Land transport.
	 */
	LAND_TRANS, 
	/**
	 * air transport.
	 */
	AIR_TRANS, 
	/**
	 * sea transport.
	 */
	SEA_TRANS;
	
	/**
	 * unit class values.
	 */
	private static final UnitClass[] VALUES = UnitClass.values();
	
	/**
	 * retrieve a unit class from its index.
	 * @param index index
	 * @return the matching unit class
	 */
	public static UnitClass from(int index) {	
		return VALUES[index];
	}
}
