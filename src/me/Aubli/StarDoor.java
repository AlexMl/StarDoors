package me.Aubli;

import java.util.logging.Logger;

import me.Aubli.Listeners.PlayerInteractListener;

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
	
	public String messagePrefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "StarDoors" + ChatColor.DARK_GRAY + "] " + ChatColor.RESET;
	public static String doorPath;
	
	public boolean enable = false;
	
	public static final DoorManager dm = new DoorManager();
	
	public static StarDoor instance;
	
	@Override
	public void onDisable(){
		
		if(enable){
			dm.saveDoors();
			logger.info("[StarDoors] Plugin disabled!");
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
		dm.loadDoors();
		
		logger.info("[StarDoors] Plugin enabled!");
	}
	
	private void registerCommands(){
		getCommand("sd").setExecutor(new StarDoorCommands(this));
	}
	
	private void registerListeners(){
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new PlayerInteractListener(this), this);
	}

	private void loadConfig(){
		
		this.getConfig().addDefault("config.settings.enable", true);

		this.getConfig().options().copyDefaults(true);
		saveConfig();
		
		enable = getConfig().getBoolean("config.settings.enable");
		doorPath = getDataFolder().getPath() + "/doors/";
		
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
