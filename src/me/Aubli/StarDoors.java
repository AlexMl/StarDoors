package me.Aubli;

import org.bukkit.plugin.java.JavaPlugin;

public class StarDoors extends JavaPlugin{
	
	@Override
	public void onDisable(){
		
	}
	
	@Override
	public void onEnable(){
		
		loadConfig();
		registerListeners();
		registerCommands();
		
	}
	
	
	private void registerCommands(){
		
	}
	
	private void registerListeners(){
		
	}

	private void loadConfig(){
		
	}
}
