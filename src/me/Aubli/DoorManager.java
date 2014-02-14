package me.Aubli;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;

public class DoorManager {
	
	private ArrayList<Door> doors = new ArrayList<Door>();
	private String doorPath = StarDoor.doorPath;
	
	
	
	
	private int getNewID(){
		
		int ID = 1;
		
		File doorFolder = new File(doorPath);
		int[] doors = new int[doorFolder.listFiles().length];
		
		for(int i=0;i<doors.length;i++){
			doors[i] = Integer.parseInt(doorFolder.listFiles()[i].toString().split(".y")[0].split("doors/")[1]);
		}		
		
		if(doors.length>0){
			Arrays.sort(doors);
			
			for(int k=0;k<doors.length;k++){
				if(doors[k]!=k+1){
					ID=k+1;
					break;
				}
				
				if(k+1==doors.length){
					ID=doors.length+1;
					break;
				}
			}
		}else{
			ID = 1;
		}
		return ID;
	}
	
	public Door[] getDoors(){
		Door[] doorArray = new Door[doors.size()];
		
		for(int i=0;i<doorArray.length;i++){
			doorArray[i] = doors.get(i);
		}
		
		return doorArray;
	}
	
	public Door getDoor(int doorID){
		
		for(int i=0;i<getDoors().length;i++){
			if(getDoors()[i].getID()==doorID){
				return getDoors()[i];
			}
		}
		
		return null;
	}
	
	public boolean addDoor(Location corner1, Location corner2){
		try {
			Door door = new Door(corner1, corner2, getNewID(), doorPath);
			doors.add(door);
			return true;
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeDoor(){
		
		return false;
	}
	
	public void saveDoors(){
		for(int i=0;i<getDoors().length;i++){
			getDoors()[i].saveDoor();
		}
	}
	
	public void loadDoors(){
		doors.clear();
		
		for(int i=0;i<new File(doorPath).listFiles().length;i++){
			Door door = new Door(new File(doorPath).listFiles()[i]);
			doors.add(door);
		}
		
	}
}
