package com.panzergeneral;

public class UnitEntry {
	
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
	  public  int  aaf;        /* air attack flag */
	  public  int  init;        
	  public  int  range;      
	  public  int  spot;       
	  public  int  agf;        /* air ground flag */
	  public  MoveType MoveType;  
	  public  int  move;       
	  public  int  fuel;       
	  public  int  ammo;       
	  public  int  cost;       
	  public  int  pic_id;    
	  public  int  month;      
	  public  int  year;       
	  public  int  last_year; 
	  public  int  nation;
	  
	  @Override 
	  public String toString() {
		return Name;  
	  }
}
