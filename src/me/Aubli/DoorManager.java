package me.Aubli;

import java.util.ArrayList;

import org.bukkit.Location;

public class DoorManager {
	
	private ArrayList<Door> doors = new ArrayList<Door>();
	private String doorPath = StarDoor.doorPath;
	
	
	
	
	public Door[] getDoors(){
		Door[] doorArray = new Door[doors.size()];
		
		for(int i=0;i<doorArray.length;i++){
			doorArray[i] = doors.get(i);
		}
		
		return doorArray;
	}
	
	public Door getDoor(){
		return null;
	}
	
	public boolean addDoor(Location corner1, Location corner2){
		try {
			Door door = new Door(corner1, corner2, 1, doorPath);
			doors.add(door);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeDoor(){
		
		return false;
	}
	
}
