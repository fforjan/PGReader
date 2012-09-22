package com.pgreader.legacy;

import java.util.EnumSet;
import java.util.Vector;

import com.pgreader.data.MoveCost;
import com.pgreader.data.MoveType;
import com.pgreader.data.map.Terrain;
import com.pgreader.data.map.TerrainImpact;
import com.pgreader.data.map.TerrainSet;
import com.pgreader.data.map.Weather;

/**
 * contains definition of all terrain.
 */
public final class StaticTerrain {

	/**
	 * utility class have a private constructor.
	 */
	private StaticTerrain() {
		
	}
	/**
	 * Terrain definition is hard coded at the moment.
	 */
	static void registerTerrain() {
		Vector<Terrain> terrains = new Vector<Terrain>();
		
		terrains.add(getClear());
		terrains.add(getRoad());
		terrains.add(getFields());
		terrains.add(getRough());
		terrains.add(getRiver());
		terrains.add(getForest());
		terrains.add(getFortification());
		terrains.add(getAirfield());
		terrains.add(getTown());
		terrains.add(getMountain());		
		terrains.add(getOcean());
		terrains.add(getSwamp());
		terrains.add(getDesert());
		terrains.add(getRoughDesert());
		terrains.add(getHarbor());
	}

	/**
	 * @return clear terrain
	 */
	private static Terrain getClear() {
		Terrain clear = new Terrain();
		
		clear.setId("c");
		clear.setTerrainName("clear");
		clear.setMinEntr(0);
		clear.setMaxEntr(5);
		clear.setMaxIni(99);
		clear.setSpotCost(Weather.FairDry, 1);
		clear.setSpotCost(Weather.OverCastDry, 1);
		clear.setSpotCost(Weather.RainingDry, 2);
		clear.setSpotCost(Weather.SnowingDry, 2);
		
		clear.setSpotCost(Weather.FairMud, 1);
		clear.setSpotCost(Weather.OverCastMud, 1);
		clear.setSpotCost(Weather.RainingMud, 2);
		clear.setSpotCost(Weather.SnowingMud, 2);
		
		clear.setSpotCost(Weather.FairIce, 1);
		clear.setSpotCost(Weather.OverCastIce, 1);
		clear.setSpotCost(Weather.RainingIce, 2);
		clear.setSpotCost(Weather.SnowingIce, 2);
		
		clear.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.One);
		clear.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.One);
		clear.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.Two);
		clear.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.One);	
		clear.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		clear.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		clear.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		clear.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.One);
				
		clear.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Two);
		clear.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Three);
		clear.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Three);
		clear.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		clear.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		clear.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		clear.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		clear.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Two);
		
		clear.setMoveCost(TerrainSet.Ice, MoveType.Tracked, MoveCost.One);
		clear.setMoveCost(TerrainSet.Ice, MoveType.HalfTracked, MoveCost.Two);
		clear.setMoveCost(TerrainSet.Ice, MoveType.Wheeled, MoveCost.Two);
		clear.setMoveCost(TerrainSet.Ice, MoveType.Leg, MoveCost.One);	
		clear.setMoveCost(TerrainSet.Ice, MoveType.Towed, MoveCost.All);
		clear.setMoveCost(TerrainSet.Ice, MoveType.Air, MoveCost.One);
		clear.setMoveCost(TerrainSet.Ice, MoveType.Naval, MoveCost.Cant);
		clear.setMoveCost(TerrainSet.Ice, MoveType.AllTerrain, MoveCost.Two);
		
		clear.setFlag(TerrainSet.Regular, EnumSet.noneOf(TerrainImpact.class));
		clear.setFlag(TerrainSet.Rain, EnumSet.noneOf(TerrainImpact.class));
		clear.setFlag(TerrainSet.Ice, EnumSet.noneOf(TerrainImpact.class));
		return clear;
	}
	
	/**
	 * @return clear terrain
	 */
	private static Terrain getRoad() {
		Terrain road = new Terrain();
		
		road.setId("r");
		road.setTerrainName("road");
		road.setMinEntr(0);
		road.setMaxEntr(5);
		road.setMaxIni(99);
		road.setSpotCost(Weather.FairDry, 1);
		road.setSpotCost(Weather.OverCastDry, 1);
		road.setSpotCost(Weather.RainingDry, 2);
		road.setSpotCost(Weather.SnowingDry, 2);
		
		road.setSpotCost(Weather.FairMud, 1);
		road.setSpotCost(Weather.OverCastMud, 1);
		road.setSpotCost(Weather.RainingMud, 2);
		road.setSpotCost(Weather.SnowingMud, 2);
		
		road.setSpotCost(Weather.FairIce, 1);
		road.setSpotCost(Weather.OverCastIce, 1);
		road.setSpotCost(Weather.RainingIce, 2);
		road.setSpotCost(Weather.SnowingIce, 2);
		
		road.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.One);
		road.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.One);
		road.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.One);
		road.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.One);	
		road.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		road.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		road.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		road.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.One);
				
		road.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		road.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		road.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Two);
		road.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		road.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		road.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		road.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		road.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.One);
		
		road.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		road.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		road.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Two);
		road.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		road.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		road.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		road.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		road.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.One);
		
		road.setFlag(TerrainSet.Regular, EnumSet.noneOf(TerrainImpact.class));
		road.setFlag(TerrainSet.Rain, EnumSet.noneOf(TerrainImpact.class));
		road.setFlag(TerrainSet.Ice, EnumSet.noneOf(TerrainImpact.class));
		return road;
	}
	
	/**
	 * @return clear terrain
	 */
	private static Terrain getFields() {
		Terrain fields = new Terrain();
		
		fields.setId("#");
		fields.setTerrainName("field");
		fields.setMinEntr(0);
		fields.setMaxEntr(5);
		fields.setMaxIni(3);
		fields.setSpotCost(Weather.FairDry, 1);
		fields.setSpotCost(Weather.OverCastDry, 1);
		fields.setSpotCost(Weather.RainingDry, 2);
		fields.setSpotCost(Weather.SnowingDry, 2);
		
		fields.setSpotCost(Weather.FairMud, 1);
		fields.setSpotCost(Weather.OverCastMud, 1);
		fields.setSpotCost(Weather.RainingMud, 2);
		fields.setSpotCost(Weather.SnowingMud, 2);
		
		fields.setSpotCost(Weather.FairIce, 1);
		fields.setSpotCost(Weather.OverCastIce, 1);
		fields.setSpotCost(Weather.RainingIce, 2);
		fields.setSpotCost(Weather.SnowingIce, 2);
		
		fields.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.Four);
		fields.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.All);
		fields.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.All);
		fields.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.Two);	
		fields.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		fields.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		fields.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		fields.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.Three);
				
		fields.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.All);
		fields.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.All);
		fields.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.All);
		fields.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		fields.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		fields.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		fields.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		fields.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.All);
		
		fields.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.All);
		fields.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.All);
		fields.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.All);
		fields.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		fields.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		fields.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		fields.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		fields.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Three);
		
		fields.setFlag(TerrainSet.Regular, EnumSet.noneOf(TerrainImpact.class));
		fields.setFlag(TerrainSet.Rain, EnumSet.noneOf(TerrainImpact.class));
		fields.setFlag(TerrainSet.Ice, EnumSet.noneOf(TerrainImpact.class));
		return fields;
	}
	
	/**
	 * @return clear terrain
	 */
	private static Terrain getRough() {
		Terrain rough = new Terrain();
		
		rough.setId("~");
		rough.setTerrainName("Rough");
		rough.setMinEntr(1);
		rough.setMaxEntr(6);
		rough.setMaxIni(5);
		rough.setSpotCost(Weather.FairDry, 1);
		rough.setSpotCost(Weather.OverCastDry, 1);
		rough.setSpotCost(Weather.RainingDry, 2);
		rough.setSpotCost(Weather.SnowingDry, 2);
		
		rough.setSpotCost(Weather.FairMud, 1);
		rough.setSpotCost(Weather.OverCastMud, 1);
		rough.setSpotCost(Weather.RainingMud, 2);
		rough.setSpotCost(Weather.SnowingMud, 2);
		
		rough.setSpotCost(Weather.FairIce, 1);
		rough.setSpotCost(Weather.OverCastIce, 1);
		rough.setSpotCost(Weather.RainingIce, 2);
		rough.setSpotCost(Weather.SnowingIce, 2);
		
		rough.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.Two);
		rough.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.Two);
		rough.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.Four);
		rough.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.Two);	
		rough.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		rough.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		rough.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		rough.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.Three);
				
		rough.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Three);
		rough.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Four);
		rough.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.All);
		rough.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		rough.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		rough.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		rough.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		rough.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.All);
		
		rough.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Two);
		rough.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Three);
		rough.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.All);
		rough.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Three);	
		rough.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		rough.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		rough.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		rough.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Three);
		
		rough.setFlag(TerrainSet.Regular, EnumSet.noneOf(TerrainImpact.class));
		rough.setFlag(TerrainSet.Rain, EnumSet.noneOf(TerrainImpact.class));
		rough.setFlag(TerrainSet.Ice, EnumSet.noneOf(TerrainImpact.class));
		return rough;
	}
	
	/**
	 * @return clear terrain
	 */
	private static Terrain getRiver() {
		Terrain river = new Terrain();
		
		river.setId("R");
		river.setTerrainName("River");
		river.setMinEntr(0);
		river.setMaxEntr(5);
		river.setMaxIni(99);
		river.setSpotCost(Weather.FairDry, 1);
		river.setSpotCost(Weather.OverCastDry, 1);
		river.setSpotCost(Weather.RainingDry, 2);
		river.setSpotCost(Weather.SnowingDry, 2);
		
		river.setSpotCost(Weather.FairMud, 1);
		river.setSpotCost(Weather.OverCastMud, 1);
		river.setSpotCost(Weather.RainingMud, 2);
		river.setSpotCost(Weather.SnowingMud, 2);
		
		river.setSpotCost(Weather.FairIce, 1);
		river.setSpotCost(Weather.OverCastIce, 1);
		river.setSpotCost(Weather.RainingIce, 2);
		river.setSpotCost(Weather.SnowingIce, 2);
		
		river.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.All);
		river.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.All);
		river.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.All);
		river.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.All);	
		river.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		river.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		river.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		river.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.All);
				
		river.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Cant);
		river.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Cant);
		river.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Cant);
		river.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.All);	
		river.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		river.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		river.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		river.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Cant);
		
		river.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Two);
		river.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Two);
		river.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Three);
		river.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		river.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		river.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		river.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		river.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Two);
		
		river.setFlag(TerrainSet.Regular, EnumSet.of(TerrainImpact.River));
		river.setFlag(TerrainSet.Rain, EnumSet.of(TerrainImpact.River));
		river.setFlag(TerrainSet.Ice, EnumSet.noneOf(TerrainImpact.class));
		return river;
	}
	
	/**
	 * @return clear terrain
	 */
	private static Terrain getForest() {
		Terrain forest = new Terrain();
		
		forest.setId("f");
		forest.setTerrainName("Forest");
		forest.setMinEntr(2);
		forest.setMaxEntr(7);
		forest.setMaxIni(3);
		forest.setSpotCost(Weather.FairDry, 2);
		forest.setSpotCost(Weather.OverCastDry, 2);
		forest.setSpotCost(Weather.RainingDry, 2);
		forest.setSpotCost(Weather.SnowingDry, 2);
		
		forest.setSpotCost(Weather.FairMud, 2);
		forest.setSpotCost(Weather.OverCastMud, 2);
		forest.setSpotCost(Weather.RainingMud, 2);
		forest.setSpotCost(Weather.SnowingMud, 2);
		
		forest.setSpotCost(Weather.FairIce, 2);
		forest.setSpotCost(Weather.OverCastIce, 2);
		forest.setSpotCost(Weather.RainingIce, 2);
		forest.setSpotCost(Weather.SnowingIce, 2);
		
		forest.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.Two);
		forest.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.Two);
		forest.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.Three);
		forest.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.Two);	
		forest.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		forest.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		forest.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		forest.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.Three);
				
		forest.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Three);
		forest.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Three);
		forest.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.All);
		forest.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		forest.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		forest.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		forest.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		forest.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Four);
		
		forest.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Two);
		forest.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Two);
		forest.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.All);
		forest.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		forest.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		forest.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		forest.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		forest.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Two);
		
		forest.setFlag(TerrainSet.Regular, EnumSet.of(TerrainImpact.InfCloseDef));
		forest.setFlag(TerrainSet.Rain, EnumSet.of(TerrainImpact.InfCloseDef));
		forest.setFlag(TerrainSet.Ice, EnumSet.of(TerrainImpact.InfCloseDef));
		return forest;
	}

	/**
	 * @return clear terrain
	 */
	private static Terrain getFortification() {
		Terrain fort = new Terrain();
		
		fort.setId("F");
		fort.setTerrainName("Fort");
		fort.setMinEntr(2);
		fort.setMaxEntr(7);
		fort.setMaxIni(3);
		fort.setSpotCost(Weather.FairDry, 1);
		fort.setSpotCost(Weather.OverCastDry, 1);
		fort.setSpotCost(Weather.RainingDry, 2);
		fort.setSpotCost(Weather.SnowingDry, 2);
		
		fort.setSpotCost(Weather.FairMud, 1);
		fort.setSpotCost(Weather.OverCastMud, 1);
		fort.setSpotCost(Weather.RainingMud, 2);
		fort.setSpotCost(Weather.SnowingMud, 2);
		
		fort.setSpotCost(Weather.FairIce, 1);
		fort.setSpotCost(Weather.OverCastIce, 1);
		fort.setSpotCost(Weather.RainingIce, 2);
		fort.setSpotCost(Weather.SnowingIce, 2);
		
		fort.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.One);
		fort.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.One);
		fort.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.Two);
		fort.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.One);	
		fort.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		fort.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		fort.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		fort.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.One);
				
		fort.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Two);
		fort.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Two);
		fort.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Four);
		fort.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		fort.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		fort.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		fort.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		fort.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.One);
		
		fort.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Two);
		fort.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Two);
		fort.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Four);
		fort.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		fort.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		fort.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		fort.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		fort.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Three);
		
		fort.setFlag(TerrainSet.Regular, EnumSet.of(TerrainImpact.InfCloseDef));
		fort.setFlag(TerrainSet.Rain, EnumSet.of(TerrainImpact.InfCloseDef));
		fort.setFlag(TerrainSet.Ice, EnumSet.of(TerrainImpact.InfCloseDef));
		return fort;
	}
	
	/**
	 * @return clear terrain
	 */
	private static Terrain getAirfield() {
		Terrain airfield = new Terrain();
		
		airfield.setId("a");
		airfield.setTerrainName("Airfield");
		airfield.setMinEntr(2);
		airfield.setMaxEntr(7);
		airfield.setMaxIni(99);
		airfield.setSpotCost(Weather.FairDry, 1);
		airfield.setSpotCost(Weather.OverCastDry, 1);
		airfield.setSpotCost(Weather.RainingDry, 2);
		airfield.setSpotCost(Weather.SnowingDry, 2);
		
		airfield.setSpotCost(Weather.FairMud, 1);
		airfield.setSpotCost(Weather.OverCastMud, 1);
		airfield.setSpotCost(Weather.RainingMud, 2);
		airfield.setSpotCost(Weather.SnowingMud, 2);
		
		airfield.setSpotCost(Weather.FairIce, 1);
		airfield.setSpotCost(Weather.OverCastIce, 1);
		airfield.setSpotCost(Weather.RainingIce, 2);
		airfield.setSpotCost(Weather.SnowingIce, 2);
		
		airfield.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.One);	
		airfield.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		airfield.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		airfield.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.One);
				
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Two);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.One);
		
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Two);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		airfield.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.One);
		
		airfield.setFlag(TerrainSet.Regular, 
				EnumSet.of(TerrainImpact.SupplyAir, TerrainImpact.SupplyGround));
		airfield.setFlag(TerrainSet.Rain, 
				EnumSet.of(TerrainImpact.SupplyAir, TerrainImpact.SupplyGround));
		airfield.setFlag(TerrainSet.Ice,
				EnumSet.of(TerrainImpact.SupplyAir, TerrainImpact.SupplyGround));
		return airfield;
	}
	
	/**
	 * @return clear terrain
	 */
	private static Terrain getTown() {
		Terrain town = new Terrain();
		
		town.setId("t");
		town.setTerrainName("Town");
		town.setMinEntr(3);
		town.setMaxEntr(8);
		town.setMaxIni(1);
		town.setSpotCost(Weather.FairDry, 2);
		town.setSpotCost(Weather.OverCastDry, 2);
		town.setSpotCost(Weather.RainingDry, 2);
		town.setSpotCost(Weather.SnowingDry, 2);
		
		town.setSpotCost(Weather.FairMud, 2);
		town.setSpotCost(Weather.OverCastMud, 2);
		town.setSpotCost(Weather.RainingMud, 2);
		town.setSpotCost(Weather.SnowingMud, 2);
		
		town.setSpotCost(Weather.FairIce, 2);
		town.setSpotCost(Weather.OverCastIce, 2);
		town.setSpotCost(Weather.RainingIce, 2);
		town.setSpotCost(Weather.SnowingIce, 2);
		
		town.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.One);
		town.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.One);
		town.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.One);
		town.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.One);	
		town.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		town.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		town.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		town.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.One);
				
		town.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		town.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		town.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.One);
		town.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		town.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		town.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		town.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		town.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.One);
		
		town.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		town.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		town.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Two);
		town.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		town.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		town.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		town.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		town.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.One);
		
		town.setFlag(TerrainSet.Regular, 
				EnumSet.of(TerrainImpact.InfCloseDef, TerrainImpact.SupplyGround));
		town.setFlag(TerrainSet.Rain, 
				EnumSet.of(TerrainImpact.InfCloseDef, TerrainImpact.SupplyGround));
		town.setFlag(TerrainSet.Ice, 
				EnumSet.of(TerrainImpact.InfCloseDef, TerrainImpact.SupplyGround));
		return town;
	}
	
	/**
	 * @return clear terrain
	 */
	private static Terrain getOcean() {
		Terrain ocean = new Terrain();
		
		ocean.setId("o");
		ocean.setTerrainName("Ocean");
		ocean.setMinEntr(0);
		ocean.setMaxEntr(0);
		ocean.setMaxIni(99);
		ocean.setSpotCost(Weather.FairDry, 1);
		ocean.setSpotCost(Weather.OverCastDry, 1);
		ocean.setSpotCost(Weather.RainingDry, 2);
		ocean.setSpotCost(Weather.SnowingDry, 2);
		
		ocean.setSpotCost(Weather.FairMud, 1);
		ocean.setSpotCost(Weather.OverCastMud, 1);
		ocean.setSpotCost(Weather.RainingMud, 2);
		ocean.setSpotCost(Weather.SnowingMud, 2);
		
		ocean.setSpotCost(Weather.FairIce, 1);
		ocean.setSpotCost(Weather.OverCastIce, 1);
		ocean.setSpotCost(Weather.RainingIce, 2);
		ocean.setSpotCost(Weather.SnowingIce, 2);
		
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.Cant);	
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.Cant);
				
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Cant);	
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Cant);
		
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Cant);	
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.Cant);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Cant);
		
		ocean.setFlag(TerrainSet.Regular, 
				EnumSet.of(TerrainImpact.InfCloseDef, TerrainImpact.SupplyGround));
		ocean.setFlag(TerrainSet.Rain, 
				EnumSet.of(TerrainImpact.InfCloseDef, TerrainImpact.SupplyGround));
		ocean.setFlag(TerrainSet.Ice, 
				EnumSet.of(TerrainImpact.InfCloseDef, TerrainImpact.SupplyGround));
		return ocean;
	}
	
	/**
	 * @return Mountain terrain
	 */
	private static Terrain getMountain() {
		Terrain moutain = new Terrain();
		
		moutain.setId("m");
		moutain.setTerrainName("Mountain");
		moutain.setMinEntr(1);
		moutain.setMaxEntr(6);
		moutain.setMaxIni(8);
		moutain.setSpotCost(Weather.FairDry, 2);
		moutain.setSpotCost(Weather.OverCastDry, 2);
		moutain.setSpotCost(Weather.RainingDry, 2);
		moutain.setSpotCost(Weather.SnowingDry, 2);
		
		moutain.setSpotCost(Weather.FairMud, 2);
		moutain.setSpotCost(Weather.OverCastMud, 2);
		moutain.setSpotCost(Weather.RainingMud, 2);
		moutain.setSpotCost(Weather.SnowingMud, 2);
		
		moutain.setSpotCost(Weather.FairIce, 2);
		moutain.setSpotCost(Weather.OverCastIce, 2);
		moutain.setSpotCost(Weather.RainingIce, 2);
		moutain.setSpotCost(Weather.SnowingIce, 2);
		
		moutain.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.Cant);	
		moutain.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		moutain.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.Cant);
				
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Cant);	
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Cant);
		
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Cant);	
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		moutain.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Cant);
		
		moutain.setFlag(TerrainSet.Regular, 
				EnumSet.noneOf(TerrainImpact.class));
		moutain.setFlag(TerrainSet.Rain, 
				EnumSet.noneOf(TerrainImpact.class));
		moutain.setFlag(TerrainSet.Ice, 
				EnumSet.noneOf(TerrainImpact.class));
		return moutain;
	}
	
	/**
	 * @return Swamp terrain
	 */
	private static Terrain getSwamp() {
		Terrain swamp = new Terrain();
		
		swamp.setId("s");
		swamp.setTerrainName("Swamp");
		swamp.setMinEntr(0);
		swamp.setMaxEntr(3);
		swamp.setMaxIni(4);
		swamp.setSpotCost(Weather.FairDry, 1);
		swamp.setSpotCost(Weather.OverCastDry, 1);
		swamp.setSpotCost(Weather.RainingDry, 2);
		swamp.setSpotCost(Weather.SnowingDry, 2);
		
		swamp.setSpotCost(Weather.FairMud, 1);
		swamp.setSpotCost(Weather.OverCastMud, 1);
		swamp.setSpotCost(Weather.RainingMud, 2);
		swamp.setSpotCost(Weather.SnowingMud, 2);
		
		swamp.setSpotCost(Weather.FairIce, 1);
		swamp.setSpotCost(Weather.OverCastIce, 1);
		swamp.setSpotCost(Weather.RainingIce, 2);
		swamp.setSpotCost(Weather.SnowingIce, 2);
		
		swamp.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.Four);
		swamp.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.Four);
		swamp.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.All);
		swamp.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.Two);	
		swamp.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.Cant);
		swamp.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		swamp.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		swamp.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.All);
				
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Cant);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Cant);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Cant);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.All);	
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.Cant);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Cant);
		
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Two);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Two);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Three);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.Cant);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		swamp.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Three);
		
		swamp.setFlag(TerrainSet.Regular, 
				EnumSet.of(TerrainImpact.Swamp));
		swamp.setFlag(TerrainSet.Rain, 
				EnumSet.of(TerrainImpact.Swamp));
		swamp.setFlag(TerrainSet.Ice, 
				EnumSet.noneOf(TerrainImpact.class));
		return swamp;
	}
	
	/**
	 * @return desert terrain
	 */
	private static Terrain getDesert() {
		Terrain desert = new Terrain();
		
		desert.setId("d");
		desert.setTerrainName("Desert");
		desert.setMinEntr(0);
		desert.setMaxEntr(5);
		desert.setMaxIni(99);
		desert.setSpotCost(Weather.FairDry, 1);
		desert.setSpotCost(Weather.OverCastDry, 1);
		desert.setSpotCost(Weather.RainingDry, 2);
		desert.setSpotCost(Weather.SnowingDry, 2);
		
		desert.setSpotCost(Weather.FairMud, 1);
		desert.setSpotCost(Weather.OverCastMud, 1);
		desert.setSpotCost(Weather.RainingMud, 2);
		desert.setSpotCost(Weather.SnowingMud, 2);
		
		desert.setSpotCost(Weather.FairIce, 1);
		desert.setSpotCost(Weather.OverCastIce, 1);
		desert.setSpotCost(Weather.RainingIce, 2);
		desert.setSpotCost(Weather.SnowingIce, 2);
		
		desert.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.One);
		desert.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.One);
		desert.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.Three);
		desert.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.Two);	
		desert.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		desert.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		desert.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		desert.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.Two);
				
		desert.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		desert.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		desert.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Three);
		desert.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		desert.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		desert.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		desert.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		desert.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Two);
		
		desert.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		desert.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		desert.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Three);
		desert.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		desert.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		desert.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		desert.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		desert.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Two);

		
		desert.setFlag(TerrainSet.Regular, 
				EnumSet.noneOf(TerrainImpact.class));
		desert.setFlag(TerrainSet.Rain, 
				EnumSet.noneOf(TerrainImpact.class));
		desert.setFlag(TerrainSet.Ice, 
				EnumSet.noneOf(TerrainImpact.class));
		return desert;
	}
	
	/**
	 * @return rough desert terrain
	 */
	private static Terrain getRoughDesert() {
		Terrain roughDesert = new Terrain();
		
		roughDesert.setId("D");
		roughDesert.setTerrainName("Rough Desert");
		roughDesert.setMinEntr(1);
		roughDesert.setMaxEntr(6);
		roughDesert.setMaxIni(99);
		roughDesert.setSpotCost(Weather.FairDry, 1);
		roughDesert.setSpotCost(Weather.OverCastDry, 1);
		roughDesert.setSpotCost(Weather.RainingDry, 2);
		roughDesert.setSpotCost(Weather.SnowingDry, 2);
		
		roughDesert.setSpotCost(Weather.FairMud, 1);
		roughDesert.setSpotCost(Weather.OverCastMud, 1);
		roughDesert.setSpotCost(Weather.RainingMud, 2);
		roughDesert.setSpotCost(Weather.SnowingMud, 2);
		
		roughDesert.setSpotCost(Weather.FairIce, 1);
		roughDesert.setSpotCost(Weather.OverCastIce, 1);
		roughDesert.setSpotCost(Weather.RainingIce, 2);
		roughDesert.setSpotCost(Weather.SnowingIce, 2);
		
		roughDesert.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.Two);
		roughDesert.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.Two);
		roughDesert.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.Four);
		roughDesert.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.Two);	
		roughDesert.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		roughDesert.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		roughDesert.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.Cant);
		roughDesert.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.Three);
				
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Two);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Two);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Four);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Three);
		
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.Two);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.Two);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Four);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.Two);	
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.Cant);
		roughDesert.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.Three);

		
		roughDesert.setFlag(TerrainSet.Regular, 
				EnumSet.noneOf(TerrainImpact.class));
		roughDesert.setFlag(TerrainSet.Rain, 
				EnumSet.noneOf(TerrainImpact.class));
		roughDesert.setFlag(TerrainSet.Ice, 
				EnumSet.noneOf(TerrainImpact.class));
		return roughDesert;
	}
	
	/**
	 * @return clear terrain
	 */
	private static Terrain getHarbor() {
		Terrain ocean = new Terrain();
		
		ocean.setId("h");
		ocean.setTerrainName("Harbor");
		ocean.setMinEntr(3);
		ocean.setMaxEntr(8);
		ocean.setMaxIni(5);
		ocean.setSpotCost(Weather.FairDry, 1);
		ocean.setSpotCost(Weather.OverCastDry, 1);
		ocean.setSpotCost(Weather.RainingDry, 2);
		ocean.setSpotCost(Weather.SnowingDry, 2);
		
		ocean.setSpotCost(Weather.FairMud, 1);
		ocean.setSpotCost(Weather.OverCastMud, 1);
		ocean.setSpotCost(Weather.RainingMud, 2);
		ocean.setSpotCost(Weather.SnowingMud, 2);
		
		ocean.setSpotCost(Weather.FairIce, 1);
		ocean.setSpotCost(Weather.OverCastIce, 1);
		ocean.setSpotCost(Weather.RainingIce, 2);
		ocean.setSpotCost(Weather.SnowingIce, 2);
		
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Tracked, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.HalfTracked, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Wheeled, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Leg, MoveCost.One);	
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Towed, MoveCost.All);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Air, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.Naval, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Regular, MoveType.AllTerrain, MoveCost.Cant);
				
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Two);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.One);
		
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Tracked, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.HalfTracked, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Wheeled, MoveCost.Two);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Leg, MoveCost.One);	
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Towed, MoveCost.All);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Air, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.Naval, MoveCost.One);
		ocean.setMoveCost(TerrainSet.Rain, MoveType.AllTerrain, MoveCost.One);
		
		ocean.setFlag(TerrainSet.Regular, 
				EnumSet.of(TerrainImpact.InfCloseDef, 
						TerrainImpact.SupplyGround, TerrainImpact.SupplyShip));
		ocean.setFlag(TerrainSet.Rain, 
				EnumSet.of(TerrainImpact.InfCloseDef, 
						TerrainImpact.SupplyGround, TerrainImpact.SupplyShip));
		ocean.setFlag(TerrainSet.Ice, 
				EnumSet.of(TerrainImpact.InfCloseDef, 
						TerrainImpact.SupplyGround, TerrainImpact.SupplyShip));
		return ocean;
	}
}
