package me.Aubli.StarDoor;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Door {
	
	public enum CloseType{
		LEFT,
		RIGHT,
		TOP,
		BOTTOM,
		MIDDLE,
		;
	}
	
	public enum OpenType{
		LEFT,
		RIGHT,
		TOP,
		BOTTOM,
		MIDDLE,
		;
	}
	
	public enum DoorStat{
		OPEN,
		CLOSE,
		;
	}
	
	private File doorFile;
	private FileConfiguration doorConfig;
	
	private int doorID;
	private Location corner1;
	private Location corner2;
	
	private DoorStat doorStat = DoorStat.OPEN;
	private CloseType closeType;
	private OpenType openType;
	
	public Door(Location corner1, Location corner2, int doorID, String doorPath, CloseType ct, OpenType ot) throws Exception{
		this.corner1 = corner1.clone();
		this.corner2 = corner2.clone();
		
		this.doorID = doorID;
		
		this.openType = ot;
		this.closeType = ct;
		
		doorStat = DoorStat.OPEN;
		
		this.doorFile = new File(doorPath + "/" + doorID + ".yml");
		
		if(doorFile.exists()){
			throw new Exception("doorID already exists!");
		}
		
		doorFile.createNewFile();
		
		doorConfig = YamlConfiguration.loadConfiguration(doorFile);
	
		saveDoor();
	}
	
	public Door(File doorFile){
		this.doorFile = doorFile;
		this.doorConfig = YamlConfiguration.loadConfiguration(this.doorFile);
		
		this.doorID = doorConfig.getInt("door.doorID");
		this.doorStat = DoorStat.valueOf(doorConfig.getString("door.stat"));
		
		this.openType = OpenType.valueOf(doorConfig.getString("door.OpenType"));
		this.closeType = CloseType.valueOf(doorConfig.getString("door.CloseType"));
		
		this.corner1 = new Location(Bukkit.getWorld(doorConfig.getString("door.Location.world")), doorConfig.getInt("door.Location.corner1.X"), doorConfig.getInt("door.Location.corner1.Y"), doorConfig.getInt("door.Location.corner1.Z"));
		this.corner2 = new Location(Bukkit.getWorld(doorConfig.getString("door.Location.world")), doorConfig.getInt("door.Location.corner2.X"), doorConfig.getInt("door.Location.corner2.Y"), doorConfig.getInt("door.Location.corner2.Z"));	
	}
	
	void saveDoor(){
		
		try {
			doorConfig.set("door.doorID", doorID);
			doorConfig.set("door.stat", doorStat.toString());
			
			doorConfig.set("door.CloseType", closeType.toString());
			doorConfig.set("door.OpenType", openType.toString());
			
			doorConfig.set("door.Location.world", corner1.getWorld().getName());
			doorConfig.set("door.Location.corner1.X", corner1.getBlockX());
			doorConfig.set("door.Location.corner1.Y", corner1.getBlockY());
			doorConfig.set("door.Location.corner1.Z", corner1.getBlockZ());
			
			doorConfig.set("door.Location.corner2.X", corner2.getBlockX());
			doorConfig.set("door.Location.corner2.Y", corner2.getBlockY());
			doorConfig.set("door.Location.corner2.Z", corner2.getBlockZ());
			
			doorConfig.save(doorFile);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public int getID(){
		return doorID;
	}
	
	public Location getCorner1(){
		return corner1;
	}
	
	public Location getCorner2(){
		return corner2;
	}
	
	public World getWorld(){
		return corner1.getWorld();
	}
	
	public OpenType getOpenType(){
		return openType;
	}
	
	public CloseType getCloseType(){
		return closeType;
	}
	
	public File getFile(){
		return doorFile;
	}
	
	public void setOpenType(OpenType ot){
		this.openType = ot;
	}
	
	public void setCloseType(CloseType ct){
		this.closeType = ct;
	}
	
	public void setDoorStat(DoorStat stat){
		this.doorStat = stat;
	}
	
	public DoorStat getDoorStat(){
		return doorStat;
	}
	
	public boolean isOpen(){
		return doorStat==DoorStat.OPEN;
	}
	
	public boolean isClosed(){
		return doorStat==DoorStat.CLOSE;
	}
	
	
	@Override
	public String toString(){
		return "{DOOR-" + doorID + " =[" + corner1.getWorld().getName() + "# " + corner1.getBlockX() + ":" + corner1.getBlockY() + ":" + corner1.getBlockZ() + " ; " + corner2.getBlockX() + ":" + corner2.getBlockY() + ":" + corner2.getBlockZ() + "] }";
	}	
	
}
