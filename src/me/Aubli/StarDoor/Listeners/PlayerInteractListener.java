package me.Aubli.StarDoor.Listeners;

import java.util.HashMap;

import me.Aubli.StarDoor.Door;
import me.Aubli.StarDoor.StarDoor;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Sign;
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
						event.setCancelled(true);
					}
					if(event.getAction()==Action.LEFT_CLICK_BLOCK){
						doorLocs.put("left", event.getClickedBlock().getLocation());
						eventPlayer.sendMessage(plugin.messagePrefix + ChatColor.GREEN + "Link Klick gespeichert!");
						event.setCancelled(true);
					}
					
					if(doorLocs.containsKey("left") && doorLocs.containsKey("right")){						
						StarDoor.getInstance().dm.addDoor(doorLocs.get("left"), doorLocs.get("right"));
						doorLocs.clear();
						eventPlayer.sendMessage(plugin.messagePrefix + ChatColor.GREEN + "Tür wurde hinzugefügt!");
						return;
					}
				}
			}
		}
		
		if(event.getClickedBlock().getState() instanceof Sign){
			Sign sign = (Sign) event.getClickedBlock().getState();
			String signLine = ChatColor.stripColor(sign.getLine(0)) + " ";
			String messagePrefix = ChatColor.stripColor(plugin.messagePrefix);
			if(signLine.equals(messagePrefix)){
				int doorID = Integer.parseInt(sign.getLine(3).split("r: ")[1]);
				Door door = plugin.dm.getDoor(doorID);
				
				if(door!=null){
					plugin.dm.moveDoor(door, eventPlayer, !door.isOpen());
					return;
				}
			}		
			
		}
	}
	
	private StarDoor plugin;
}