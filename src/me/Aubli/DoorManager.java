package me.Aubli;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import me.Aubli.Door.CloseType;
import me.Aubli.Door.OpenType;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class DoorManager {
	
	private ArrayList<Door> doors = new ArrayList<Door>();
	private StarDoor plugin;
	private String doorPath;
	
	public DoorManager(){
		this.plugin = StarDoor.getInstance();
		this.doorPath = plugin.doorPath;
	}	

	public void shutdown(){
		
		for(int i=0;i<getDoors().length;i++){
			//closeDoor(getDoors()[i], null);
		}
		
		saveDoors();
	}
	
	public void startup(){
		loadDoors();
	}
	
	
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
		
		int tempX;
		int tempY;
		int tempZ;
		
		
		if(corner2.getBlockX()<corner1.getBlockX()){
			tempX = corner2.getBlockX();
			corner2.setX(corner1.getBlockX());
			corner1.setX(tempX);
		}								
		
		if(corner2.getBlockY()<corner1.getBlockY()){
			tempY = corner2.getBlockY();
			corner2.setY(corner1.getBlockY());
			corner1.setY(tempY);
		}
		
		if(corner2.getBlockZ()<corner1.getBlockZ()){
			tempZ = corner2.getBlockZ();
			corner2.setZ(corner1.getBlockZ());
			corner1.setZ(tempZ);
		}	
		
		try {
			Door door = new Door(corner1, corner2, getNewID(), doorPath, CloseType.BOTTOM, OpenType.TOP);
			return doors.add(door);
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeDoor(Door door){		
		return doors.remove(door);
	}
	
	public void openDoor(Door door, Player player){
		new DoorRunnable(door.getCorner1(), door.getCorner2(), CloseType.TOP, door.getBlockList(), player).runTaskTimer(plugin, 0, 10L);		
	}
	
	public void moveDoor(Door door, CloseType stat, Player player){
		new DoorRunnable(door.getCorner1(), door.getCorner2(), stat, door.getBlockList(), player).runTaskTimer(plugin, 0, 10L);		
	}
	
	public void closeDoor(Door door, Player player){
		new DoorRunnable(door.getCorner1(), door.getCorner2(), CloseType.BOTTOM, door.getBlockList(), player).runTaskTimer(plugin, 0, 2L);		
	}
	
	private void saveDoors(){
		for(int i=0;i<getDoors().length;i++){
			getDoors()[i].saveDoor();
		}
	}
	
	private void loadDoors(){
		doors.clear();

		if(new File(doorPath).listFiles().length>0){
			for(int i=0;i<new File(doorPath).listFiles().length;i++){
				Door door = new Door(new File(doorPath).listFiles()[i]);
				//door.update();
				doors.add(door);
			}
		}
	}
	
}
