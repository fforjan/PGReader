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
	  
	  
		/*
		Load PG unit entry from file position.
		DOS entry format (50 bytes):
		*/
		public static UnitEntry ReadEntry(InputStream stream) throws IOException {
			UnitEntry read = new UnitEntry();
			
			//Name 0
			byte[] name = new byte[20];
			stream.read(name);
			read.Name = new String(name).trim();
			read.nation = GuessNationFromName(read.Name);
			
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
			read.aaf = stream.read() != 0;
			
			// ???   30
			stream.skip(1);
			
			// INI   31
			read.init = stream.read();
			
			// RNG   32
			read.range = stream.read();
			
			// SPT   33
			read.spot = stream.read();
			
			// GAF   34
			read.agf = stream.read() != 0;
			
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
			
			 ApplyModification(read);
		
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
		 * Return index in global nations or -1 if no match. */
		private static Nation GuessNationFromName(String unitName)
		{
			if(unitName.startsWith("PO")) return Nation.Poland;
			if(unitName.startsWith("GB ")) return Nation.GreatBritain;
			if(unitName.startsWith("FR ")) return Nation.France;
			if(unitName.startsWith("NOR ")) return Nation.Norway;
			if(unitName.startsWith("LC ")) return Nation.Belgia; /* assign low country units to belgia */
			if(unitName.startsWith("ST ")) return Nation.Sovjetunion;
			if(unitName.startsWith("IT ")) return Nation.Italy;
			if(unitName.startsWith("Bulgarian ")) return Nation.Bulgaria;
			if(unitName.startsWith("Hungarian ")) return Nation.Hungary;
			if(unitName.startsWith("Rumanian ")) return Nation.Rumania;
			if(unitName.startsWith("Greek ")) return Nation.Greece;
			if(unitName.startsWith("Yugoslav ")) return Nation.Yugoslavia;
			if(unitName.startsWith("US ")) return Nation.USA;
			if(unitName.startsWith("Finn")) return Nation.Finnland; /* codes for Finnland ... */
			if(unitName.startsWith("FIN")) return Nation.Finnland; /* ... not used in pg but some other campaigns */
			if(unitName.startsWith("FFR ")) return Nation.France;
			if(unitName.startsWith("FPO ")) return Nation.Poland;
			if(unitName.startsWith("Katyusha ")) return Nation.Sovjetunion;
			
			//by default its Germany
			// TODO if(unitName.startsWith("AF ", -1 },
			// TODO if(unitName.startsWith("AD ", -1 },
			
			return  Nation.Germany;
		
		}
		
		private static void ApplyModification(UnitEntry unit)
		{
			/* adjust attack values according to unit class (add - for defense only) */
	        switch ( unit.Class ) {
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
	                unit.atk_air = -unit.atk_air;
	                break;
	            case AIR_DEFENSE:
	            	unit.atk_soft = -unit.atk_soft;
	            	unit.atk_hard = -unit.atk_hard;
	            	unit.atk_naval = -unit.atk_naval;
	                break;
	            case TACBOMBER:
	            case LEVBOMBER:
	                if ( unit.aaf )
	                	unit.atk_air = -unit.atk_air;
	                break;
	        }
			
		}
}
