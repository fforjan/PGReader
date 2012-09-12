package com.pgreader.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.panzergeneral.MoveType;
import com.panzergeneral.TargetType;
import com.panzergeneral.UnitClass;
import com.panzergeneral.UnitEntry;

public class DummyContent {

    public static List<UnitEntry> Items = new ArrayList<UnitEntry>();
    public static Map<String, UnitEntry> ItemsMap = new HashMap<String, UnitEntry>();

    static {
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
}
