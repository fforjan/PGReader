package com.pgreader.data;

/**
 * define an entry.
 */
public class UnitEntry {
	
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
	 * @return the initiative
	 */
	public int getInit() {
		return mInit;
	}


	/**
	 * @param init the initiative to set
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
