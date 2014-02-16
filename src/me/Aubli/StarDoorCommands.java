package me.Aubli;

import me.Aubli.Door.CloseType;
import me.Aubli.Door.OpenType;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StarDoorCommands implements CommandExecutor{
	public StarDoorCommands(StarDoor plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
		
		if(!(sender instanceof Player)){
			sender.sendMessage("Only Players can execute StarDoor Commands!");
			return true;
		}
		
		Player playerSender = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("sd")){
			if(args.length==1){
				if(playerSender.hasPermission("sd.tool")){
					playerSender.getInventory().addItem(plugin.tool);					
					playerSender.sendMessage("Markier die Tür mit links- und rechtsclick!");
					return true;
				}else{
					playerSender.sendMessage(ChatColor.DARK_RED + "You don't have permissions for that command!");
					return true;
				}
			}else if(args.length==3){
				if(args[0].equalsIgnoreCase("close")){
					plugin.dm.moveDoor(plugin.dm.getDoor(Integer.parseInt(args[1])), CloseType.valueOf(args[2]), null, playerSender);
					return true;
				}
				if(args[0].equalsIgnoreCase("open")){
					plugin.dm.moveDoor(plugin.dm.getDoor(Integer.parseInt(args[1])), null, OpenType.valueOf(args[2]), playerSender);
					return true;
				}
			}else{
				printCommands(playerSender);
				return true;
			}
		}
		
		
		return true;
	}
	
	private void printCommands(Player playerSender){
		
		if(playerSender.hasPermission("sd")){
			String pluginVersion = plugin.getDescription().getVersion();
			String pluginName = plugin.getDescription().getName();
			
			playerSender.sendMessage(ChatColor.YELLOW + "|---------- " + pluginName + " v" + pluginVersion + " ----------|");
			playerSender.sendMessage(ChatColor.YELLOW + "|" + ChatColor.DARK_GRAY + " sd tool");
			playerSender.sendMessage(ChatColor.YELLOW + "|" + ChatColor.DARK_GRAY + " sd close [doorID] [CloseType]");
			playerSender.sendMessage(ChatColor.YELLOW + "|" + ChatColor.DARK_GRAY + " sd open [doorID] [OpenType]");
		}else{
			playerSender.sendMessage(ChatColor.DARK_RED + "You don't have permissions for that command!");
			return;
		}
	}
	
	private StarDoor plugin;
}
