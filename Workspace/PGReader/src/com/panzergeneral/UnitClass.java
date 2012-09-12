package com.panzergeneral;

public enum UnitClass {
	INFANTRY,
	TANK,
	RECON,
	ANTI_TANK,
	ARTILLERY,
	ANTI_AIRCRAFT,
	AIR_DEFENSE,
	FORT, 
	FIGHTER,
	TACBOMBER,
	LEVBOMBER, 
	SUBMARINE,
	DESTROYER, 
	CAPITAL,
	CARRIER, 
	LAND_TRANS, 
	AIR_TRANS, 
	SEA_TRANS,
	UNIT_CLASS_COUNT;
	
	private static final UnitClass[] Values = UnitClass.values();
	
	public static UnitClass From(int index) {
		if(index >= Values.length)
		{
			// if something is weird, back to the initial class
			index = 0;
		}
		
		return Values[index];
	}
}
