package me.Aubli;

import java.io.File;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Door {
	
	private File doorFile;
	private FileConfiguration doorConfig;
	
	private int doorID;
	private Location corner1;
	private Location corner2;
	
	
	public Door(Location corner1, Location corner2, int doorID, String doorPath) throws Exception{
		this.corner1 = corner1;
		this.corner2 = corner2;
		
		this.doorID = doorID;
		
		this.doorFile = new File(doorPath + doorID + ".yml");
		
		if(doorFile.exists()){
			throw new Exception("doorID already exists!");
		}
		doorConfig = YamlConfiguration.loadConfiguration(doorFile);
		
	}
	
	
}
