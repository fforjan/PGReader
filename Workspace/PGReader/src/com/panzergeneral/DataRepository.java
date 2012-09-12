package com.panzergeneral;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pgreader.BuildConfig;
import com.pgreader.R;

import android.content.res.Resources;

public class DataRepository {

	 public static List<UnitEntry> Items = new ArrayList<UnitEntry>();
	 public static Map<String, UnitEntry> ItemsMap = new HashMap<String, UnitEntry>();

	 public static void DummyInit() {

		 UnitEntry tmp = new UnitEntry();
		 tmp.Name = "Unit 1";
		 tmp.Class = UnitClass.INFANTRY;
		 tmp.TargetType = TargetType.Soft;
		 tmp.MoveType = MoveType.Leg;
		 addItem(tmp);

		 tmp = new UnitEntry();
		 tmp.Name = "Unit 2";
		 tmp.Class = UnitClass.FIGHTER;
		 tmp.TargetType = TargetType.Air;
		 tmp.MoveType = MoveType.Air;
		 addItem(tmp);

		 tmp = new UnitEntry();
		 tmp.Name = "Unit 3";
		 tmp.Class = UnitClass.TANK;
		 tmp.TargetType = TargetType.Hard;
		 tmp.MoveType = MoveType.Tracked;
		 addItem(tmp);
	 }

	 private static void addItem(UnitEntry item) {
		 Items.add(item);
		 ItemsMap.put(item.Name, item);
	 }
	
	public static void Load(Resources resources) {
		if(Items.isEmpty())
		{
			try {
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
				stream.skip(50);
				
				while (count-- > 0) {
					addItem(UnitEntry.ReadEntry(stream));
				}
			} catch (Resources.NotFoundException ex) {
				DummyInit();
			} catch (IOException e) {
				DummyInit();
			}
		}
	}
}
