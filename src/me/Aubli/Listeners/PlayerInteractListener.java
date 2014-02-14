package me.Aubli.Listeners;

import java.util.HashMap;

import me.Aubli.StarDoor;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
	public PlayerInteractListener(StarDoor plugin){
		this.plugin = plugin;
	}
	
	private HashMap<String, Location> doorLocs = new HashMap<String, Location>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		Player eventPlayer = event.getPlayer();
		
		if(event.getItem()!=null){
			if(event.getItem().equals(plugin.tool)){
				if(eventPlayer.hasPermission("sd.tool")){
					if(event.getAction()==Action.RIGHT_CLICK_BLOCK){
						doorLocs.put("right", event.getClickedBlock().getLocation());
						eventPlayer.sendMessage(plugin.messagePrefix + ChatColor.GREEN + "Rechter Klick gespeichert!");
					}
					if(event.getAction()==Action.LEFT_CLICK_BLOCK){
						doorLocs.put("left", event.getClickedBlock().getLocation());
						eventPlayer.sendMessage(plugin.messagePrefix + ChatColor.GREEN + "Link Klick gespeichert!");
					}
					
					if(doorLocs.containsKey("left") && doorLocs.containsKey("right")){						
						StarDoor.getInstance().dm.addDoor(doorLocs.get("left"), doorLocs.get("right"));
						doorLocs.clear();
						return;
					}
				}
			}
		}
	}
	
	private StarDoor plugin;
}