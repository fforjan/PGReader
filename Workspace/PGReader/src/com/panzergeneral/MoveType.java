package com.panzergeneral;

public enum MoveType {
	Tracked,     
	HalfTracked,
	Wheeled,    
	Leg,        
	Towed,      
	Air,        
	Naval,      
	AllTerrain;
	
	private static final MoveType[] Values = MoveType.values();
	
	public static MoveType From(int index) {
		return Values[index];
	}
}
