package me.Aubli.StarDoor.Listeners;

import me.Aubli.StarDoor.SignManager;
import me.Aubli.StarDoor.StarDoor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
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
		
		if(event.getBlock().getState() instanceof Sign){
			Sign sign = (Sign) event.getBlock().getState(); 
			String signLine = ChatColor.stripColor(sign.getLine(0)) + " ";
			String messagePrefix = ChatColor.stripColor(plugin.messagePrefix);
			if(signLine.equals(messagePrefix)){
				if(SignManager.getManager().removeSign(event.getBlock().getLocation())){
					eventPlayer.sendMessage(plugin.messagePrefix + ChatColor.GREEN + "Schild entfernt!");
					return;
				}else{
					eventPlayer.sendMessage(plugin.messagePrefix + ChatColor.RED + "Es trat ein Fehler auf!");
					return;
				}
			}else{
				Bukkit.broadcastMessage("{" + signLine + "}");
				Bukkit.broadcastMessage("{" + messagePrefix + "}");
				return;
			}
		}
	}
	
	private StarDoor plugin;
}
