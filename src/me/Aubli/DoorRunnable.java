package me.Aubli;

import java.util.ArrayList;

import me.Aubli.Door.CloseType;
import me.Aubli.Door.OpenType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DoorRunnable extends BukkitRunnable{
	
	private StarDoor plugin = StarDoor.getInstance();
	private Location c1;
	private Location c2;
	private CloseType ct;
	private OpenType ot;
	private Material doorMaterial;
	private ArrayList<Block> doorBlocks;
	private Player player;
	
	private int y;
	
	public DoorRunnable(Location corner1, Location corner2, CloseType ct, OpenType ot, ArrayList<Block> doorBlocks, Player player){
		this.c1 = corner1;
		this.c2 = corner2;
		this.ct = ct;
		this.ot = ot;
		this.doorBlocks = doorBlocks;
		this.player = player;
		doorMaterial = plugin.doorMaterial;
	}
	
	@Override
	public void run() {
		
		if(ct.equals(CloseType.TOP)){
			
			for(;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
				if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
					for(int x=0;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;x++){
						if(c2.clone().subtract(x, y, 0).getBlock().getType()==doorMaterial){
							c2.clone().subtract(x, y, 0).getBlock().setType(Material.AIR);
							Bukkit.broadcastMessage(ChatColor.GREEN + c2.clone().subtract(x, y, 0).getBlock().toString());
						}						
					}
					
					if(player!=null){
						player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
					}
					
					y++;
					return;
				}
				
				if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
					for(int z=0;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;z++){
						if(c2.clone().subtract(0, y, z).getBlock().getType()==doorMaterial){
							c2.clone().subtract(0, y, z).getBlock().setType(Material.AIR);
							Bukkit.broadcastMessage(ChatColor.RED + c2.clone().subtract(0, y, z).getBlock().toString());
						}						
					}
					
					if(player!=null){
						player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
					}
					
					y++;
					return;
				}
			}
			new DoorRunnable(c1, c2, CloseType.BOTTOM, ot, doorBlocks, player).runTaskTimer(plugin, 5*20L, 10L);
			this.cancel();			
		}
		
		if(ct.equals(CloseType.BOTTOM)){			
				
			for(;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
				if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
					for(int x=0;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;x++){
						if(c1.clone().add(x, y, 0).getBlock().getType()==Material.AIR){
							c1.clone().add(x, y, 0).getBlock().setType(doorMaterial);
							Bukkit.broadcastMessage(ChatColor.GREEN + c1.clone().add(x, y, 0).getBlock().toString());
						}						
					}
					
					if(player!=null){
						player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
					}
					
					y++;
					return;
				}
				
				if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
					for(int z=0;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;z++){
						if(c1.clone().add(0, y, z).getBlock().getType()==Material.AIR){
							c1.clone().add(0, y, z).getBlock().setType(doorMaterial);
							Bukkit.broadcastMessage(ChatColor.RED + c1.clone().add(0, y, z).getBlock().toString());
						}						
					}
					
					if(player!=null){
						player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
					}
					
					y++;
					return;
				}
			}
			this.cancel();
		}		
	}
}
