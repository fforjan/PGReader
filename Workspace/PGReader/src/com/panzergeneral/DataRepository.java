package com.panzergeneral;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgreader.R;

import android.content.res.Resources;

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
	 * map for accessing the unit from its id.
	 */
	private static Map<String, UnitEntry> sUnitsMap = new HashMap<String, UnitEntry>();

	/**
	 * add a unit into our unit list.
	 * @param unit unit to add
	 */
	private static void addItem(UnitEntry unit) {
		sUnits.add(unit);
		sUnitsMap.put(unit.getName(), unit);
	}

	/**
	 * load our equipment data from our resource bundle.
	 * @param resources resource
	 * @throws IOException if any error occurs while reading
	 */
	public static void loadUnits(Resources resources) throws IOException {
		if (sUnits.isEmpty()) {
			InputStream stream = resources.openRawResource(R.raw.panzequp);

			byte[] buffer = new byte[2];
			stream.read(buffer);

			ByteBuffer countBuffer = ByteBuffer.wrap(buffer);
			countBuffer.order(ByteOrder.LITTLE_ENDIAN);
			/* DOS format:
			 * count ( 2 bytes )
			 * entries ( 50 bytes each ) 
			 */
			int count = countBuffer.getShort();
			System.err.println(String.format("There is %d units to read", count));

			//first entry is reserved
			count--;
			stream.skip(UnitEntry.UNITENTRYSIZE);

			while (count-- > 0) {
				addItem(UnitEntry.readEntry(stream));
			}

		}
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
}
