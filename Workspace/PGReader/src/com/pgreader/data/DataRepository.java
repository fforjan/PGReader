package com.pgreader.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
