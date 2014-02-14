package me.Aubli;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class DoorUpdateRunnable extends BukkitRunnable{

	private Location c1;
	private Location c2;
	private Door door;
	private ArrayList<Block> doorBlocks;
	
	public DoorUpdateRunnable(Door door){
		this.door = door;
		this.c1 = door.getCorner1();
		this.c2 = door.getCorner2();
		this.doorBlocks = new ArrayList<Block>();		
	}
	
	@Override
	public void run() {
		
		for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
			if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
				for(int x=0;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;x++){
					doorBlocks.add(c1.clone().add(x, y, 0).getBlock());
					Bukkit.broadcastMessage(ChatColor.GREEN + c1.clone().add(x, y, 0).getBlock().toString());
				}
			}
			
			if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
				for(int z=0;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;z++){
					doorBlocks.add(c1.clone().add(0, y, z).getBlock());
					Bukkit.broadcastMessage(ChatColor.RED + c1.clone().add(0, y, z).getBlock().toString());
				}
			}
		}
		
		door.setDoorBlocks(doorBlocks);
		this.cancel();
	}

}
