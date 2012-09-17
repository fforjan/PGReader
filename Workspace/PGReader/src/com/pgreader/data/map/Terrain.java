package com.pgreader.data.map;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Terrain information.
 */
public class Terrain {

	/**
	 * terrain id.
	 */
	private String mId;

	/**
	 * name in terrain set.
	 */
	private String mTerrainName;
	
	/**
	 * minimum entrenchment.
	 */
    private int mMinEntr;
    
    /**
     * maximum entrenchment.
     */
    private int mMaxEntr;
    
    /**
     * maximum initiative.
     */
    private int mMaxIni;
    
    /**
     * spot cost.
     */
    private Map<Weather, Integer> mSpotCosts = new HashMap<Weather, Integer>();
    
    /**
     * movement cost.
     */
    private Map<TerrainSet, String> mMoveCosts = new HashMap<TerrainSet, String>();
    
    /**
     * Flags per terrain set.
     */
    private Map<TerrainSet, EnumSet<TerrainImpact>> mFlags = 
    		new HashMap<TerrainSet, EnumSet<TerrainImpact>>();

	/**
	 * @return the id
	 */
	public String getId() {
		return mId;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.mId = id;
	}

	/**
	 * @return the terrainName
	 */
	public String getTerrainName() {
		return mTerrainName;
	}

	/**
	 * @param terrainName the terrainSet to set
	 */
	public void setTerrainName(String terrainName) {
		this.mTerrainName = terrainName;
	}

	/**
	 * @return the minEntr
	 */
	public int getMinEntr() {
		return mMinEntr;
	}

	/**
	 * @param minEntr the minEntr to set
	 */
	public void setMinEntr(int minEntr) {
		this.mMinEntr = minEntr;
	}

	/**
	 * @return the maxEntr
	 */
	public int getMaxEntr() {
		return mMaxEntr;
	}

	/**
	 * @param maxEntr the maxEntr to set
	 */
	public void setMaxEntr(int maxEntr) {
		this.mMaxEntr = maxEntr;
	}

	/**
	 * @return the maxIni
	 */
	public int getMaxIni() {
		return mMaxIni;
	}

	/**
	 * @param maxIni the maxIni to set
	 */
	public void setMaxIni(int maxIni) {
		this.mMaxIni = maxIni;
	}

	/**
	 * @return the spotCosts
	 */
	public Map<Weather, Integer> getSpotCosts() {
		return mSpotCosts;
	}

	/**
	 * Return the spot cost for a specific weather.
	 * @param weather targeted weather
	 * @return spot cost associated to the weather for this Terrain
	 */
	public Integer getSpotCost(Weather weather) {
		return mSpotCosts.get(weather);
	}
	
	/**
	 * @param spotCosts the spotCosts to set
	 */
	public void setSpotCosts(Map<Weather, Integer> spotCosts) {
		this.mSpotCosts = spotCosts;
	}
	
	/**
	 * Set the spot cost for a specific weather.
	 * @param weather targeted weather
	 * @param spotCost spot cost associated to the weather for this Terrain
	 */
	public void setSpotCost(Weather weather, Integer spotCost) {
		mSpotCosts.put(weather, spotCost);
	}
	

	/**
	 * @return the move costs
	 */
	public Map<TerrainSet, String> getMoveCosts() {
		return mMoveCosts;
	}
	
	/**
	 * Return the move cost for a specific terrain set.
	 * @param terrain targeted terrain
	 * @return move cost associated to the terrain set for this Terrain
	 */
	public String getMoveCost(TerrainSet terrain) {
		return mMoveCosts.get(terrain);
	}
	
	/**
	 * @param moveCosts the move costs to set
	 */
	public void setMoveCosts(Map<TerrainSet, String> moveCosts) {
		this.mMoveCosts = moveCosts;
	}
	
	/**
	 * Set the move cost for a specific terrain set.
	 * @param terrain targeted terrain
	 * @param moveCost move cost associated to the terrain set for this Terrain
	 */
	public void setMoveCost(TerrainSet terrain, String moveCost) {
		mMoveCosts.put(terrain, moveCost);
	}

	/**
	 * @return the flags
	 */
	public Map<TerrainSet, EnumSet<TerrainImpact>> getFlags() {
		return mFlags;
	}
	
	/**
	 * Get the flag for a specific terrain set.
	 * @param terrainSet targeted terrain set
	 * @return flag associated to the terrain set
	 */
	public  EnumSet<TerrainImpact> getFlag(TerrainSet terrainSet) {
		return mFlags.get(terrainSet);
	}

	/**
	 * @param flags the flags to set
	 */
	public void setFlags(Map<TerrainSet, EnumSet<TerrainImpact>> flags) {
		this.mFlags = flags;
	}
	
	/**
	 * Set the flag for a specific terrain set.
	 * @param terrainSet targeted terrain set
	 * @param flag flag associated to the terrain set
	 */
	public void setFlag(TerrainSet terrainSet, EnumSet<TerrainImpact> flag) {
		mFlags.put(terrainSet, flag);
	}

}