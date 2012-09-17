package com.pgreader.data.map;

import java.util.EnumSet;

import com.pgreader.data.Ground;

/**
 *
 */
public enum Weather {
	
	/**
	 * FairDry.
	 */
	FairDry,
	/**
	 * OverCastDry.
	 */
	OverCastDry,
	/**
	 * RainingDry.
	 */
	RainingDry,
	/**
	 * SnowingDry.
	 */
	SnowingDry,
	/**
	 * FairMud.
	 */
	FairMud,
	/**
	 * OverCastMud.
	 */
	OverCastMud,
	/**
	 * RainingMud.
	 */
	RainingMud,
	/**
	 * SnowingMud.
	 */
	SnowingMud,
	/**
	 * FairIce.
	 */
	FairIce,
	/**
	 * OverCastIce.
	 */
	OverCastIce,
	/**
	 * RainingIce.
	 */
	RainingIce,
	/**
	 * SnowingIce.
	 */
	SnowingIce;
	
	/**
	 * Impact of weather on unit.
	 */
	enum WeatherImpact {
		/**
		 * No impact.
		 */
		None,
		/** 
		 * air unit cannot attack.
		 */
		NoAirAttack,
		/**
		 * Bad sight.
		 */
		BadSight,
		/**
		 * double the cost of any actions.
		 */
		DoubleCost
	}
	
	/**
	 * Get ground info for weather.
	 * @param weather weather
	 * @return ground info
	 */
	public static Ground getGround(Weather weather) {
		switch(weather) {
			case FairDry:
			case OverCastDry:
			case RainingDry:
			case SnowingDry:
				return Ground.Dry;
			case FairMud:
			case OverCastMud:
			case RainingMud:
			case SnowingMud:
				return Ground.Mud;
			case FairIce:
			case OverCastIce:
			case RainingIce:
			case SnowingIce:
				return Ground.Ice;
		default:
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Get impacts for a specific weather.
	 * @param weather current weather
	 * @return set of impact
	 */
	public EnumSet<WeatherImpact> getImpact(Weather weather) {
		switch(weather) {
		case FairDry:
		case OverCastDry:
			return EnumSet.of(WeatherImpact.None);
		case RainingDry:
		case SnowingDry:
			return EnumSet.of(WeatherImpact.NoAirAttack, WeatherImpact.BadSight);
		case FairMud:
		case OverCastMud:
			return EnumSet.of(WeatherImpact.None);
		case RainingMud:
		case SnowingMud:
			return EnumSet.of(WeatherImpact.NoAirAttack, WeatherImpact.BadSight);
		case FairIce:
		case OverCastIce:
			return EnumSet.of(WeatherImpact.DoubleCost);
		case RainingIce:
		case SnowingIce:
			return EnumSet.of(WeatherImpact.DoubleCost,
					WeatherImpact.NoAirAttack, WeatherImpact.BadSight);
		default:
			throw new IllegalArgumentException();
		}	
	}
	
	/**
	 * get set to use with associated weather.
	 * @param weather current weather
	 * @return associated set
	 */
	public TerrainSet getSet(Weather weather) {
		switch(weather) {
			case FairDry:
			case OverCastDry:
			case RainingDry:
			case SnowingDry:
				return TerrainSet.Regular;
			case FairMud:
			case OverCastMud:
			case RainingMud:
			case SnowingMud:
				return TerrainSet.Rain;
			case FairIce:
			case OverCastIce:
			case RainingIce:
			case SnowingIce:
				return TerrainSet.Ice;
			default:
				throw new IllegalArgumentException();
		}
	}
}
