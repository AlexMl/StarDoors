package me.Aubli.Listeners;

import me.Aubli.StarDoor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
	public BlockBreakListener(StarDoor plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event){
		Player eventPlayer = event.getPlayer();
		
		if(eventPlayer.getItemInHand()!=null){
			if(eventPlayer.getItemInHand().equals(plugin.tool)){
				if(eventPlayer.hasPermission("sd.tool")){
					event.setCancelled(true);
					return;
				}
			}
		}
	}
	
	private StarDoor plugin;
}
