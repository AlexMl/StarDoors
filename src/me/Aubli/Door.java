package me.Aubli;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Door {
	
	public enum CloseType{
		LEFT,
		RIGHT,
		TOP,
		BOTTOM,
		;
	}
	
	public enum OpenType{
		LEFT,
		RIGHT,
		TOP,
		BOTTOM,
		;
	}
	
	
	private File doorFile;
	private FileConfiguration doorConfig;
	
	private int doorID;
	private Location corner1;
	private Location corner2;
	
	private ArrayList<Block> doorBlocks;
	
	private CloseType closeType;
	private OpenType openType;
	
	public Door(Location corner1, Location corner2, int doorID, String doorPath, CloseType ct, OpenType ot) throws Exception{
		this.corner1 = corner1;
		this.corner2 = corner2;
		
		this.doorID = doorID;
		
		this.openType = ot;
		this.closeType = ct;
		
		this.doorFile = new File(doorPath + "/" + doorID + ".yml");
		
		if(doorFile.exists()){
			throw new Exception("doorID already exists!");
		}
		doorConfig = YamlConfiguration.loadConfiguration(doorFile);
		update();
		saveDoor();
	}
	
	public Door(File doorFile){
		this.doorFile = doorFile;
		this.doorConfig = YamlConfiguration.loadConfiguration(this.doorFile);
		
		this.doorID = doorConfig.getInt("door.doorID");
		
		this.corner1 = new Location(Bukkit.getWorld(doorConfig.getString("door.Location.world")), doorConfig.getInt("door.Location.corner1.X"), doorConfig.getInt("door.Location.corner1.Y"), doorConfig.getInt("door.Location.corner1.Z"));
		this.corner2 = new Location(Bukkit.getWorld(doorConfig.getString("door.Location.world")), doorConfig.getInt("door.Location.corner2.X"), doorConfig.getInt("door.Location.corner2.Y"), doorConfig.getInt("door.Location.corner2.Z"));
	}
	
	void saveDoor(){
		
		try {
			doorFile.createNewFile();
			
			doorConfig.set("door.doorID", doorID);
			
			doorConfig.set("door.CloseType", closeType);
			doorConfig.set("door.OpenType", openType);
			
			doorConfig.set("door.Location.world", corner1.getWorld().getName());
			doorConfig.set("door.Location.corner1.X", corner1.getBlockX());
			doorConfig.set("door.Location.corner1.Y", corner1.getBlockY());
			doorConfig.set("door.Location.corner1.Z", corner1.getBlockZ());
			
			doorConfig.set("door.Location.corner2.X", corner2.getBlockX());
			doorConfig.set("door.Location.corner2.Y", corner2.getBlockY());
			doorConfig.set("door.Location.corner2.Z", corner2.getBlockZ());
			
			if(doorBlocks!=null){
				doorConfig.set("door.Blocks", doorBlocks.toString());
			}
			
			doorConfig.save(doorFile);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void setDoorBlocks(ArrayList<Block> blocks){
		this.doorBlocks = blocks;
		saveDoor();
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
	
	public ArrayList<Block> getBlockList(){
		return doorBlocks;
	}
	
	public OpenType getOpenType(){
		return openType;
	}
	
	public CloseType getCloseType(){
		return closeType;
	}
	
	public void update(){
		new DoorUpdateRunnable(this).runTask(StarDoor.getInstance());
	}
	
	@Override
	public String toString(){
		return "{DOOR-" + doorID + " =[" + corner1.getWorld().getName() + "# " + corner1.getBlockX() + ":" + corner1.getBlockY() + ":" + corner1.getBlockZ() + " ; " + corner2.getBlockX() + ":" + corner2.getBlockY() + ":" + corner2.getBlockZ() + "] }";
	}
	
	public void setOpenType(OpenType ot){
		this.openType = ot;
	}
	
	public void setCloseType(CloseType ct){
		this.closeType = ct;
	}
}
