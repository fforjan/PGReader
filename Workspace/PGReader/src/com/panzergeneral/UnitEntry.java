package com.panzergeneral;

import java.io.IOException;
import java.io.InputStream;

public class UnitEntry {

	/**
	 * unit entry size (in bytes) in the pg file.
	 */
	public static final int UNITENTRYSIZE = 50;
	/**
	 * name entry size (in bytes) in the pg file.
	 */
	public static final int NAMESIZE = 20;
	
	public String Name;   
	public UnitClass Class;
	public  int  atk_soft;   
	public  int  atk_hard;   
	public  int  atk_air;    
	public  int  atk_naval;  
	public  int  def_ground; 
	public  int  def_air;    
	public  int  def_close;  
	public  TargetType TargetType;
	public  boolean  aaf;        /* air attack flag */
	public  int  init;        
	public  int  range;      
	public  int  spot;       
	public  boolean  agf;        /* air ground flag */
	public  MoveType MoveType;  
	public  int  move;       
	public  int  fuel;       
	public  int  ammo;       
	public  int  cost;       
	public  int  pic_id;    
	public  int  month;      
	public  int  year;       
	public  int  last_year; 
	public  Nation  nation;

	@Override 
	public String toString() {
		return Name;  
	}


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
		read.Name = new String(name).trim();
		read.nation = guessNationFromName(read.Name);	

		read.Class = UnitClass.From(stream.read()); //CLASS 20
		read.atk_soft = stream.read(); //SA 21
		read.atk_hard = stream.read(); // HA    22
		read.atk_air = stream.read(); // AA    23
		read.atk_naval = stream.read(); // NA    24
		read.def_close = stream.read(); // GD    25
		read.def_air = stream.read(); // AD    26
		read.def_close = stream.read(); // CD    27
		read.TargetType = com.panzergeneral.TargetType.from(stream.read()); // TT    28
		read.aaf = stream.read() != 0; // AAF   29
		stream.skip(1); // ???   30
		read.init = stream.read(); // INI   31
		read.range = stream.read(); // RNG   32
		read.spot = stream.read(); // SPT   33
		read.agf = stream.read() != 0; // GAF   34
		read.MoveType =  com.panzergeneral.MoveType.From(stream.read()); // MOV_TYPE 35
		read.move = stream.read(); // MOV   36
		read.fuel = stream.read(); // FUEL  37
		read.ammo = stream.read(); // AMMO  38
		stream.skip(2); 	// ???   39, ???   40
		read.cost = stream.read(); // COST  41
		read.pic_id = stream.read(); // BMP   42
		stream.skip(1 + 1 + 1); // ???   43,  ANI   44, ???   45
		read.month = stream.read(); // MON   46
		read.year = stream.read(); // YR    47
		read.last_year = stream.read(); // LAST_YEAR 48
		stream.skip(1); // ???   49

		read.applyModification();
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
	 * do modification, mostly on air unit to set a negative air attack for defense only.
	 */
	private void applyModification() {
		/* adjust attack values according to unit class (add - for defense only) */
		switch (this.Class) {
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
			this.atk_air = -this.atk_air;
			break;
		case AIR_DEFENSE:
			this.atk_soft = -this.atk_soft;
			this.atk_hard = -this.atk_hard;
			this.atk_naval = -this.atk_naval;
			break;
		case TACBOMBER:
		case LEVBOMBER:
			if (this.aaf) {
				this.atk_air = -this.atk_air;
			}
			break;
		default:
			break;
		}

	}
}
