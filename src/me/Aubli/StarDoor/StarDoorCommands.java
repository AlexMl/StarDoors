package me.Aubli.StarDoor;

import me.Aubli.StarDoor.Door.DoorStat;

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
				if(args[0].equalsIgnoreCase("tool")){
					if(playerSender.hasPermission("sd.tool")){
						playerSender.getInventory().addItem(plugin.tool);					
						playerSender.sendMessage("Markier die Tür mit links- und rechtsclick!");
						return true;
					}else{
						playerSender.sendMessage(ChatColor.DARK_RED + "You don't have permissions for that command!");
						return true;
					}
				}else if(args[0].equalsIgnoreCase("save")){
					if(playerSender.hasPermission("sd.save")){
						plugin.dm.save();
						playerSender.sendMessage(plugin.messagePrefix + ChatColor.GREEN + "Türen gespeichert!");
						return true;
					}else{
						playerSender.sendMessage(ChatColor.DARK_RED + "You don't have permissions for that command!");
						return true;
					}
				}else if(args[0].equalsIgnoreCase("reload")){
					if(playerSender.hasPermission("sd.reload")){
						plugin.dm.reload();
						playerSender.sendMessage(plugin.messagePrefix + ChatColor.GREEN + "Türen neu eingelesen!");
						return true;
					}else{
						playerSender.sendMessage(ChatColor.DARK_RED + "You don't have permissions for that command!");
						return true;
					}
				}else{
					printCommands(playerSender);
					return true;
				}
			}else if(args.length==2){
				if(args[0].equalsIgnoreCase("remove")){
					if(playerSender.hasPermission("sd.remove")){
						plugin.dm.removeDoor(plugin.dm.getDoor(Integer.parseInt(args[1])));
						playerSender.sendMessage(plugin.messagePrefix + ChatColor.GREEN + "Tür gelöscht!");
						return true;
					}else{
						playerSender.sendMessage(ChatColor.DARK_RED + "You don't have permissions for that command!");
						return true;
					}
				}else if(args[0].equalsIgnoreCase("close")){
					plugin.dm.moveDoor(plugin.dm.getDoor(Integer.parseInt(args[1])), playerSender, DoorStat.CLOSE);
					return true;
				}else if(args[0].equalsIgnoreCase("open")){
					plugin.dm.moveDoor(plugin.dm.getDoor(Integer.parseInt(args[1])), playerSender, DoorStat.OPEN);
					return true;
				}else{
					printCommands(playerSender);
					return true;
				}
			}else if(args.length==2){
				
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
			playerSender.sendMessage(ChatColor.YELLOW + "|" + ChatColor.DARK_GRAY + " sd save");
			playerSender.sendMessage(ChatColor.YELLOW + "|" + ChatColor.DARK_GRAY + " sd reload");
			playerSender.sendMessage(ChatColor.YELLOW + "|" + ChatColor.DARK_GRAY + " sd remove [doorID]");
			playerSender.sendMessage(ChatColor.YELLOW + "|" + ChatColor.DARK_GRAY + " sd close [doorID]");
			playerSender.sendMessage(ChatColor.YELLOW + "|" + ChatColor.DARK_GRAY + " sd open [doorID]");
		}else{
			playerSender.sendMessage(ChatColor.DARK_RED + "You don't have permissions for that command!");
			return;
		}
	}
	
	private StarDoor plugin;
}
