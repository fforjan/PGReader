package com.panzergeneral;

import java.io.IOException;
import java.io.InputStream;

/**
 * define an entry.
 */
public class UnitEntry {

	/**
	 * unit entry size (in bytes) in the panzer general file.
	 */
	public static final int UNITENTRYSIZE = 50;
	/**
	 * name entry size (in bytes) in the panzer general file.
	 */
	public static final int NAMESIZE = 20;
	
	/**
	 * unit name.
	 */
	private String mName;
	
	/**
	 * unit class.
	 */
	private UnitClass mUnitClass;
	
	/**
	 * Soft attack.
	 */
	private  int  mAtkSoft;
	
	/**
	 * Hard attack.
	 */
	private int  mAtkHard;   
	
	/**
	 * Air attack.
	 */
	private  int  mAtkAir;
	
	/**
	 * Naval attack.
	 */
	private  int  mAtkNaval; 
	
	/**
	 * Ground defense.
	 */
	private  int  mDefGround; 
	
	/**
	 * Air defense.
	 */
	private  int  mDefAir;    
	 
	/**
	 * Close defense.
	 */
	private  int  mDefClose;
	
	/**
	 * target type.
	 */
	private  TargetType mTargetType;
	
	/** 
	 * TODO : Air attack flag.
	 */
	private  boolean  mAirAttackFlag;        
	
	/**
	 * initiative.
	 */
	private  int  mInit;   
	
	/**
	 * range.
	 */
	private  int  mRange;
	
	/**
	 * spotting distance.
	 */
	private  int  mSpot;      
	
	/** 
	 * TODO : Air ground flag.
	 */
	private  boolean  mAirGroundFlag;      
	
	/**
	 * Move type for the current unit.
	 */
	private  MoveType mMoveType; 
	
	/**
	 * move distance.
	 */
	private  int  mMove;
	
	/**
	 * fuel level.
	 */
	private  int  mFuel;
	
	/**
	 * Ammon.
	 */
	private  int  mAmmo;    
	
	/**
	 * unit cost.
	 */
	private  int  mCost;
	
	/**
	 * icon index.
	 */
	private  int  mIconIndex; 
	
	/**
	 * month of availability.
	 */
	private  int  mMonth;
	
	/**
	 * year of availability.
	 */
	private  int  mYear;    
	
	/**
	 * last year of production.
	 */
	private  int  mLastYear; 
	
	/**
	 * nation owning the unit.
	 */
	private  Nation  mNation;

	@Override 
	public String toString() {
		return mName;  
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
		read.mName = new String(name).trim();
		read.mNation = guessNationFromName(read.mName);	

		read.setUnitClass(UnitClass.from(stream.read())); //CLASS 20
		read.mAtkSoft = stream.read(); //SA 21
		read.mAtkHard = stream.read(); // HA    22
		read.mAtkAir = stream.read(); // AA    23
		read.mAtkNaval = stream.read(); // NA    24
		read.mDefClose = stream.read(); // GD    25
		read.mDefAir = stream.read(); // AD    26
		read.mDefClose = stream.read(); // CD    27
		read.mTargetType = com.panzergeneral.TargetType.from(stream.read()); // TT    28
		read.mAirAttackFlag = stream.read() != 0; // AAF   29
		stream.skip(1); // ???   30
		read.mInit = stream.read(); // INI   31
		read.mRange = stream.read(); // RNG   32
		read.mSpot = stream.read(); // SPT   33
		read.mAirGroundFlag = stream.read() != 0; // GAF   34
		read.mMoveType =  com.panzergeneral.MoveType.from(stream.read()); // MOV_TYPE 35
		read.mMove = stream.read(); // MOV   36
		read.mFuel = stream.read(); // FUEL  37
		read.mAmmo = stream.read(); // AMMO  38
		stream.skip(2); 	// ???   39, ???   40
		read.mCost = stream.read(); // COST  41
		read.setIconIndex(stream.read()); // BMP   42
		stream.skip(1 + 1 + 1); // ???   43,  ANI   44, ???   45
		read.mMonth = stream.read(); // MON   46
		read.mYear = stream.read(); // YR    47
		read.mLastYear = stream.read(); // LAST_YEAR 48
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
		switch (this.getUnitClass()) {
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
			this.mAtkAir = -this.mAtkAir;
			break;
		case AIR_DEFENSE:
			this.mAtkSoft = -this.mAtkSoft;
			this.mAtkHard = -this.mAtkHard;
			this.mAtkNaval = -this.mAtkNaval;
			break;
		case TACBOMBER:
		case LEVBOMBER:
			if (this.mAirAttackFlag) {
				this.mAtkAir = -this.mAtkAir;
			}
			break;
		default:
			break;
		}

	}


	/**
	 * @return the name
	 */
	public String getName() {
		return mName;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		mName = name;
	}


	/**
	 * @return the class
	 */
	public UnitClass getUnitClass() {
		return mUnitClass;
	}


	/**
	 * @param unitClass the class to set
	 */
	public void setUnitClass(UnitClass unitClass) {
		mUnitClass = unitClass;
	}


	/**
	 * @return the atkSoft
	 */
	public int getAtkSoft() {
		return mAtkSoft;
	}


	/**
	 * @param atkSoft the atkSoft to set
	 */
	public void setAtkSoft(int atkSoft) {
		this.mAtkSoft = atkSoft;
	}


	/**
	 * @return the atk_hard
	 */
	public int getAtkHard() {
		return mAtkHard;
	}


	/**
	 * @param atkHard the atk_hard to set
	 */
	public void setAtkHard(int atkHard) {
		this.mAtkHard = atkHard;
	}


	/**
	 * @return the atkAir
	 */
	public int getAtkAir() {
		return mAtkAir;
	}


	/**
	 * @param atkAir the atkAir to set
	 */
	public void setAtkAir(int atkAir) {
		this.mAtkAir = atkAir;
	}


	/**
	 * @return the atkNaval
	 */
	public int getAtkNaval() {
		return mAtkNaval;
	}


	/**
	 * @param atkNaval the atkNaval to set
	 */
	public void setAtkNaval(int atkNaval) {
		this.mAtkNaval = atkNaval;
	}


	/**
	 * @return the mDefGround
	 */
	public int getDefGround() {
		return mDefGround;
	}


	/**
	 * @param defGround the defGround to set
	 */
	public void setDefGround(int defGround) {
		this.mDefGround = defGround;
	}


	/**
	 * @return the defAir
	 */
	public int getDefAir() {
		return mDefAir;
	}


	/**
	 * @param defAir the defAir to set
	 */
	public void setDefAir(int defAir) {
		this.mDefAir = defAir;
	}


	/**
	 * @return the defClose
	 */
	public int getDefClose() {
		return mDefClose;
	}


	/**
	 * @param defClose the defClose to set
	 */
	public void setDefClose(int defClose) {
		this.mDefClose = defClose;
	}


	/**
	 * @return the targetType
	 */
	public TargetType getTargetType() {
		return mTargetType;
	}


	/**
	 * @param targetType the targetType to set
	 */
	public void setTargetType(TargetType targetType) {
		mTargetType = targetType;
	}


	/**
	 * @return the airAttackFlag
	 */
	public boolean isAirAttackFlag() {
		return mAirAttackFlag;
	}


	/**
	 * @param airAttackFlag the airAttackFlag to set
	 */
	public void setAirAttackFlag(boolean airAttackFlag) {
		this.mAirAttackFlag = airAttackFlag;
	}


	/**
	 * @return the init
	 */
	public int getInit() {
		return mInit;
	}


	/**
	 * @param init the init to set
	 */
	public void setInit(int init) {
		this.mInit = init;
	}


	/**
	 * @return the range
	 */
	public int getRange() {
		return mRange;
	}


	/**
	 * @param range the range to set
	 */
	public void setRange(int range) {
		this.mRange = range;
	}


	/**
	 * @return the spot
	 */
	public int getSpot() {
		return mSpot;
	}


	/**
	 * @param spot the spot to set
	 */
	public void setSpot(int spot) {
		this.mSpot = spot;
	}


	/**
	 * @return the airGroundFlag
	 */
	public boolean isAirGroundFlag() {
		return mAirGroundFlag;
	}


	/**
	 * @param airGroundFlag the airGroundFlag to set
	 */
	public void setAirGroundFlag(boolean airGroundFlag) {
		this.mAirGroundFlag = airGroundFlag;
	}


	/**
	 * @return the moveType
	 */
	public MoveType getMoveType() {
		return mMoveType;
	}


	/**
	 * @param moveType the moveType to set
	 */
	public void setMoveType(MoveType moveType) {
		mMoveType = moveType;
	}


	/**
	 * @return the move
	 */
	public int getMove() {
		return mMove;
	}


	/**
	 * @param move the move to set
	 */
	public void setMove(int move) {
		this.mMove = move;
	}


	/**
	 * @return the fuel
	 */
	public int getFuel() {
		return mFuel;
	}


	/**
	 * @param fuel the fuel to set
	 */
	public void setFuel(int fuel) {
		this.mFuel = fuel;
	}


	/**
	 * @return the ammo
	 */
	public int getAmmo() {
		return mAmmo;
	}


	/**
	 * @param ammo the ammo to set
	 */
	public void setAmmo(int ammo) {
		this.mAmmo = ammo;
	}


	/**
	 * @return the cost
	 */
	public int getCost() {
		return mCost;
	}


	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.mCost = cost;
	}


	/**
	 * @return the pic_id
	 */
	public int getIconIndex() {
		return mIconIndex;
	}


	/**
	 * @param iconIndex the pic_id to set
	 */
	public void setIconIndex(int iconIndex) {
		this.mIconIndex = iconIndex;
	}


	/**
	 * @return the month
	 */
	public int getMonth() {
		return mMonth;
	}


	/**
	 * @param month the month to set
	 */
	public void setMonth(int month) {
		this.mMonth = month;
	}


	/**
	 * @return the year
	 */
	public int getYear() {
		return mYear;
	}


	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.mYear = year;
	}


	/**
	 * @return the last_year
	 */
	public int getLastYear() {
		return mLastYear;
	}


	/**
	 * @param lastYear the last_year to set
	 */
	public void setLastYear(int lastYear) {
		this.mLastYear = lastYear;
	}


	/**
	 * @return the nation
	 */
	public Nation getNation() {
		return mNation;
	}


	/**
	 * @param nation the nation to set
	 */
	public void setNation(Nation nation) {
		this.mNation = nation;
	}
}
