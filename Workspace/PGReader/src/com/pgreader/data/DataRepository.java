package com.pgreader.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgreader.data.map.Terrain;

/**
 * data repository for reading legacy PG data.
 */
public final class DataRepository {

	/**
	 * Utility class, private constructor.
	 */
	private DataRepository() { } 
	
	/**
	 * Unit lists.
	 */
	private static List<UnitEntry> sUnits = new ArrayList<UnitEntry>();
	
    /**
     * the unit icons information from the SHP file.
     */
    private static IconResources sTacIcons;
    
    /**
     * the flags information from the SHP file.
     */
    private static IconResources sFlags;
	
	/**
	 * map for accessing the unit from its id.
	 */
	private static Map<String, UnitEntry> sUnitsMap = new HashMap<String, UnitEntry>();

	/**
	 * add a unit into our unit list.
	 * @param unit unit to add
	 */
	public static void addItem(UnitEntry unit) {
		sUnits.add(unit);
		sUnitsMap.put(unit.getName(), unit);
	}

	/**
	 * @return the sUnitsMap map, key is unit id and value the unit itself
	 */
	public static Map<String, UnitEntry> getsUnitsMap() {
		return sUnitsMap;
	}

	/**
	 * @return the list of units
	 */
	public static List<UnitEntry> getsUnits() {
		return sUnits;
	}

	/**
	 * @return the sTacIcons
	 */
	public static IconResources getsTacIcons() {
		return sTacIcons;
	}

	/**
	 * @param tacIcons the sTacIcons to set
	 */
	public static void setsTacIcons(IconResources tacIcons) {
		DataRepository.sTacIcons = tacIcons;
	}
	
	/**
	 * Clear all resources currently in use.
	 */
	public static void clearResources() {
		DataRepository.sTacIcons = null;
		sUnits.clear();
		sUnitsMap.clear();
		sTerrains.clear();
	}

	/**
	 * @return the sFlags
	 */
	public static IconResources getFlags() {
		return sFlags;
	}

	/**
	 * @param flags the sFlags to set
	 */
	public static void setFlags(IconResources flags) {
		DataRepository.sFlags = flags;
	}
	
	
	/**
	 * @return the sTerrain
	 */
	public static List<Terrain> getsTerrains() {
		return sTerrains;
	}
	
	/**
	 * @return the sTerrain map
	 */
	public static Map<String, Terrain> getsTerrainsMap() {
		return sTerrainsMap;
	}
	
	
	/**
	 * Terrain list.
	 */
	private static List<Terrain> sTerrains = new ArrayList<Terrain>();
	
	/**
	 * map for accessing the terrain from its id.
	 */
	private static Map<String, Terrain> sTerrainsMap = new HashMap<String, Terrain>();
	
	/**
	 * add a terrain into our terrain list.
	 * @param terrain terrain to add
	 */
	public static void addTerrain(Terrain terrain) {
		sTerrains.add(terrain);
		sTerrainsMap.put(terrain.getId(), terrain);
	}
}
