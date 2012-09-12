package com.panzergeneral;

import java.io.IOException;
import java.io.InputStream;

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
	  
	  
		/*
		Load PG unit entry from file position.
		DOS entry format (50 bytes):
		 NAME   0
		 CLASS 20
		 SA    21
		 HA    22
		 AA    23
		 NA    24
		 GD    25
		 AD    26
		 CD    27
		 TT    28
		 AAF   29
		 ???   30
		 INI   31
		 RNG   32
		 SPT   33
		 GAF   34    
		 MOV_TYPE 35
		 MOV   36
		 FUEL  37
		 AMMO  38
		 ???   39
		 ???   40
		 COST  41    
		 BMP   42
		 ???   43
		 ANI   44
		 ???   45
		 MON   46
		 YR    47
		 LAST_YEAR 48 
		 ???   49
		*/
		public static UnitEntry ReadEntry(InputStream stream) throws IOException {
			UnitEntry read = new UnitEntry();
			
			//Name 0
			byte[] name = new byte[20];
			stream.read(name);
			read.Name = new String(name);
			
			//CLASS 20
			read.Class = UnitClass.From(stream.read());
		
			//SA 21
			read.atk_soft = stream.read();
			
			// HA    22
			read.atk_hard = stream.read();
			
			// AA    23
			read.atk_air = stream.read();
			
			// NA    24
			read.atk_naval = stream.read();
			
			// GD    25
			read.def_close = stream.read();
			
			// AD    26
			read.def_air = stream.read();
			
			// CD    27
			read.def_close = stream.read();
			
			// TT    28
			read.TargetType = com.panzergeneral.TargetType.From(stream.read());
			
			// AAF   29
			read.aaf = stream.read();
			
			// ???   30
			stream.skip(1);
			
			// INI   31
			read.init = stream.read();
			
			// RNG   32
			read.range = stream.read();
			
			// SPT   33
			read.spot = stream.read();
			
			// GAF   34
			read.agf = stream.read();
			
			// MOV_TYPE 35
			read.MoveType =  com.panzergeneral.MoveType.From(stream.read());
			
			// MOV   36
			read.move = stream.read();
			
			// FUEL  37
			read.fuel = stream.read();
			
			// AMMO  38
			read.ammo = stream.read();
			
			// ???   39
			// ???   40
			stream.skip(2);
			
			// COST  41
			read.cost = stream.read();
			
			// BMP   42
			read.pic_id = stream.read();
			
			// ???   43
			// ANI   44
			// ???   45
			stream.skip(3);
			
			// MON   46
			read.month = stream.read();
			
			// YR    47
			read.year = stream.read();
			
			// LAST_YEAR 48
			read.last_year = stream.read();
			
			// ???   49
			stream.skip(1);
			
			return read;
		}
}
