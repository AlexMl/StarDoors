package me.Aubli.StarDoor;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import me.Aubli.StarDoor.Listeners.BlockBreakListener;
import me.Aubli.StarDoor.Listeners.PlayerInteractListener;
import me.Aubli.StarDoor.Listeners.SignChangeListener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StarDoor extends JavaPlugin{
	
	public final Logger logger = Bukkit.getLogger();
	
	public ItemStack tool;
	public Material doorMaterial;
	
	public String messagePrefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "StarDoor" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
	public String doorPath = "";
	
	public boolean enable = false;
	
	public DoorManager dm;
	
	public static StarDoor instance;
	
	@Override
	public void onDisable(){
		
		if(enable){
			dm.shutdown();
			logger.info("[StarDoor] Plugin disabled!");
		}
	}
	
	@Override
	public void onEnable(){
		
		loadConfig();
		registerListeners();
		registerCommands();
		
		if(enable==false){
			Bukkit.getPluginManager().disablePlugin(this);
		}
		dm = new DoorManager();
		dm.startup();		
		
		new SignManager();
		
		logger.info("[StarDoor] Plugin enabled!");
	}
	
	private void registerCommands(){
		getCommand("sd").setExecutor(new StarDoorCommands(this));
	}
	
	private void registerListeners(){
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new PlayerInteractListener(this), this);
		pm.registerEvents(new BlockBreakListener(this), this);
		pm.registerEvents(new SignChangeListener(), this);
	}

	private void loadConfig(){

		this.getConfig().addDefault("config.settings.enable", true);
		this.getConfig().addDefault("config.material.door", Material.QUARTZ.name());
		this.getConfig().options().copyDefaults(true);
		saveConfig();
		
		doorMaterial = Material.getMaterial(getConfig().getString("config.material.door"));
		enable = getConfig().getBoolean("config.settings.enable");
		doorPath = getDataFolder().getPath() + "/doors";
		instance = this;
		
		if(!new File(doorPath).exists()){
			new File(doorPath).mkdirs();			
		}
		
		if(!new File(this.getDataFolder().getPath() + "/signs.yml").exists()){
			try {
				new File(this.getDataFolder().getPath() + "/signs.yml").createNewFile();
			} catch (IOException e) {			
				e.printStackTrace();
			}			
		}
		
		tool = new ItemStack(Material.STICK);		
		ItemMeta toolMeta = tool.getItemMeta();		
		toolMeta.setDisplayName(messagePrefix + "Tool");	
		toolMeta.addEnchant(Enchantment.DURABILITY, 5, true);
		tool.setItemMeta(toolMeta);		
	}
	
	public static StarDoor getInstance(){
		return instance;
	}
}
