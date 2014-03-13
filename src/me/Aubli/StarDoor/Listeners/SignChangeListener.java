package me.Aubli.StarDoor.Listeners;

import me.Aubli.StarDoor.SignManager;
import me.Aubli.StarDoor.StarDoor;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent event){
		
		if(event.getLine(0).equalsIgnoreCase("[stardoor]")){
			if(!event.getLine(1).isEmpty()){
				
				int doorID = Integer.parseInt(event.getLine(1));
				
				event.setLine(0, "[" + ChatColor.GOLD + "StarDoor" + ChatColor.RESET + "]");
				event.setLine(1, "");
				event.setLine(2, "");
				event.setLine(3, "Tür: " + doorID);
				
				boolean success = SignManager.getManager().saveSign(event.getBlock().getLocation(), doorID);
				if(success){
					event.getPlayer().sendMessage(StarDoor.getInstance().messagePrefix + ChatColor.GREEN + "Schild wurde gespeichert!");
					return;
				}else{
					event.getPlayer().sendMessage(StarDoor.getInstance().messagePrefix + ChatColor.RED + "Schild konnte nicht gespeichert werden!");
					return;
				}
			}
		}
		
		
	}

}
