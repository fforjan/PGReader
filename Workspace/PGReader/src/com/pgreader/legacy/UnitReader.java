package com.pgreader.legacy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import android.content.res.Resources;

import com.pgreader.R;
import com.pgreader.data.DataRepository;
import com.pgreader.data.Nation;
import com.pgreader.data.UnitClass;
import com.pgreader.data.UnitEntry;

/**
 * Utility class for reading the unit file from panzer general.
 */
public final class UnitReader {

	/**
	 * private constructor for utility class.
	 */
	private UnitReader() {
	}

	/**
	 * unit entry size (in bytes) in the panzer general file.
	 */
	public static final int UNITENTRYSIZE = 50;
	/**
	 * name entry size (in bytes) in the panzer general file.
	 */
	public static final int NAMESIZE = 20;

	/**
	 * Load PG unit entry from current position into the stream.
	 * DOS entry format (50 bytes):
	 * @param stream input stream for ready. 
	 * @return the UnitEntry 
	 * @throws IOException if any IO exception occurs when using the input stream
	 */
	public static UnitEntry readEntry(InputStream stream) throws IOException {
		UnitEntry read = new UnitEntry();

		//Name 0
		byte[] name = new byte[NAMESIZE];
		stream.read(name);
		read.setName(new String(name).trim());
		read.setNation(guessNationFromName(read.getName()));	

		read.setUnitClass(UnitClass.from(stream.read())); //CLASS 20
		read.setAtkSoft(stream.read()); //SA 21
		read.setAtkHard(stream.read()); // HA    22
		read.setAtkAir(stream.read()); // AA    23
		read.setAtkNaval(stream.read()); // NA    24
		read.setDefClose(stream.read()); // GD    25
		read.setDefAir(stream.read()); // AD    26
		read.setDefClose(stream.read()); // CD    27
		read.setTargetType(com.pgreader.data.TargetType.from(stream.read())); // TT    28
		read.setAirAttackFlag(stream.read() != 0); // AAF   29
		stream.skip(1); // ???   30
		read.setInit(stream.read()); // INI   31
		read.setRange(stream.read()); // RNG   32
		read.setSpot(stream.read()); // SPT   33
		read.setAirGroundFlag(stream.read() != 0); // GAF   34
		read.setMoveType(com.pgreader.data.MoveType.from(stream.read())); // MOV_TYPE 35
		read.setMove(stream.read()); // MOV   36
		read.setFuel(stream.read()); // FUEL  37
		read.setAmmo(stream.read()); // AMMO  38
		stream.skip(2); 	// ???   39, ???   40
		read.setCost(stream.read()); // COST  41
		read.setIconIndex(stream.read()); // BMP   42
		stream.skip(1 + 1 + 1); // ???   43,  ANI   44, ???   45
		read.setMonth(stream.read()); // MON   46
		read.setYear(stream.read()); // YR    47
		read.setLastYear(stream.read()); // LAST_YEAR 48
		stream.skip(1); // ???   49

		applyModification(read);
		return read;
	}

	/** Try to figure out nation from unit entry name. No field in PG
	 * unit entry seems to hold the nation index. Bytes 30, 39, 40, 43
	 * and 45 are just the same for all entries. 44 (what means ANI???)
	 * seems to group certain units somehow according to class but it is
	 * very jumpy and certainly not the nation id. Byte 49 varies
	 * less but seems to have some other meaning, too. Pictures are also
	 * not very sorted, so trying the unit name seems to be the easiest
	 * approach for now:
	 * Captions of non-german units start with 2-3 capital letters
	 * indicating either the nation (GB, US, IT, ...) or allied usage 
	 * (AF,AD,...). Check for the "big" nation ids and map to
	 * nation number. Generic allied units will be used by different 
	 * nations in scenarios but are not available for purchase (as it 
	 * seems that they equal some unit of the major nations).
	 * @param unitName unit name for guessing
	 * @return nation or -1 if no match. 
	 */
	private static Nation guessNationFromName(String unitName) {
		if (unitName.startsWith("PO")) { return Nation.Poland;  }
		if (unitName.startsWith("GB ")) { return Nation.GreatBritain; }
		if (unitName.startsWith("FR ")) { return Nation.France; }
		if (unitName.startsWith("NOR ")) { return Nation.Norway; }
		if (unitName.startsWith("LC ")) { 
			return Nation.Belgia; /* assign low country units to belgia */
		}
		if (unitName.startsWith("ST ")) { return Nation.Sovjetunion; }
		if (unitName.startsWith("IT ")) { return Nation.Italy; }
		if (unitName.startsWith("Bulgarian ")) { return Nation.Bulgaria; }
		if (unitName.startsWith("Hungarian ")) { return Nation.Hungary; }
		if (unitName.startsWith("Rumanian ")) { return Nation.Rumania; }
		if (unitName.startsWith("Greek ")) { return Nation.Greece; }
		if (unitName.startsWith("Yugoslav ")) { return Nation.Yugoslavia; }
		if (unitName.startsWith("US ")) { return Nation.USA; }
		if (unitName.startsWith("Finn")) { 
			return Nation.Finnland; /* codes for Finnland ... */
		}
		if (unitName.startsWith("FIN"))  {
			return Nation.Finnland; 
			/* ... not used in pg but some other campaigns */
		}
		if (unitName.startsWith("FFR ")) { return Nation.France; }
		if (unitName.startsWith("FPO ")) { return Nation.Poland; }
		if (unitName.startsWith("Katyusha ")) { return Nation.Sovjetunion; }

		//by default its Germany
		// TODO if(unitName.startsWith("AF ", -1 },
		// TODO if(unitName.startsWith("AD ", -1 },

		return  Nation.Germany;

	}

	/**
	 * load units icons.
	 * @param resources resource
	 * @param callback callback for updating our progress.
	 * @throws IOException if any error occurs while reading
	 */
	public static void loadIcons(Resources resources, LegacyReader.ProgressStatus callback) 
			throws IOException {
		InputStream tacIconsStream = resources.openRawResource(R.raw.tacicons);  
    	DataRepository.setsTacIcons(ShpReader.load(tacIconsStream, callback));
	}
	
	/**
	 * load our units data from our resource bundle.
	 * @param resources resource
	 * @param callback callback for updating our progress.
	 * @throws IOException if any error occurs while reading
	 */
	public static void loadUnits(Resources resources, LegacyReader.ProgressStatus callback) 
			throws IOException {

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
		int current = 0;
		stream.skip(UNITENTRYSIZE);

		while (++current < count) {
			callback.setSecondaryStatus(((float) current) / count);
			DataRepository.addItem(readEntry(stream));
		}


	}

	/**
	 * apply modification, mostly on air unit to set a negative air attack for defense only.
	 * @param unit the unit to modify, inline modification
	 */
	private static void applyModification(UnitEntry unit) {
		/* adjust attack values according to unit class (add - for defense only) */
		switch (unit.getUnitClass()) {
		case INFANTRY:
		case TANK:
		case RECON:
		case ANTI_TANK:
		case ARTILLERY:
		case FORT:
		case SUBMARINE:
		case DESTROYER:
		case CAPITAL:
		case CARRIER:  
			unit.setAtkAir(-unit.getAtkAir());
			break;
		case AIR_DEFENSE:
			unit.setAtkSoft(-unit.getAtkSoft());
			unit.setAtkHard(-unit.getAtkHard());
			unit.setAtkNaval(-unit.getAtkNaval());
			break;
		case TACBOMBER:
		case LEVBOMBER:
			if (unit.isAirAttackFlag()) {
				unit.setAtkAir(-unit.getAtkAir());
			}
			break;
		default:
			break;
		}

	}
}
