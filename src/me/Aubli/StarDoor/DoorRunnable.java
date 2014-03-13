package me.Aubli.StarDoor;

import me.Aubli.StarDoor.Door.CloseType;
import me.Aubli.StarDoor.Door.DoorStat;
import me.Aubli.StarDoor.Door.OpenType;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DoorRunnable extends BukkitRunnable{
	
	private StarDoor plugin = StarDoor.getInstance();
	private Location c1;
	private Location c2;
	private DoorStat futureStat;
	private CloseType ct;
	private OpenType ot;
	private Material doorMaterial;	
	private Player player;
	
	private boolean blockChange = false;
	
	private int x;
	private int y;
	private int z;
	
	public DoorRunnable(Door door, DoorStat stat, Player player){
		this.c1 = door.getCorner1();
		this.c2 = door.getCorner2();
		this.ct = door.getCloseType();
		this.ot = door.getOpenType();		
		this.futureStat = stat;		
		
		this.player = player;
		doorMaterial = plugin.doorMaterial;
	}
	
	@Override
	public void run() {
		
		if(futureStat==DoorStat.OPEN){ //Door opens
			if(ot.equals(OpenType.TOP)){			
				for(;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
					if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
						for(int x=0;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;x++){
							if(c2.clone().subtract(x, y, 0).getBlock().getType()==doorMaterial){
								c2.clone().subtract(x, y, 0).getBlock().setType(Material.AIR);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c2.clone().subtract(x, y, 0).getBlock().toString());
							}						
						}
						
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
								blockChange=false;
							}
						}	
						
						y++;
						return;
					}
					
					if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
						for(int z=0;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;z++){
							if(c2.clone().subtract(0, y, z).getBlock().getType()==doorMaterial){
								c2.clone().subtract(0, y, z).getBlock().setType(Material.AIR);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.RED + c2.clone().subtract(0, y, z).getBlock().toString());
							}						
						}
						
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
								blockChange=false;
							}
						}
						
						y++;
						return;
					}
				}
				//new DoorRunnable(door, futureStat, player).runTaskTimer(plugin, 5*20L, 10L);
				this.cancel();			
			}else if(ot.equals(OpenType.BOTTOM)){			
				for(;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
					if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
						for(int x=0;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;x++){
							if(c1.clone().add(x, y, 0).getBlock().getType()==doorMaterial){
								c1.clone().add(x, y, 0).getBlock().setType(Material.AIR);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c1.clone().add(x, y, 0).getBlock().toString());
							}						
						}
						
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
								blockChange=false;
							}
						}
						
						y++;
						return;
					}
					
					if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
						for(int z=0;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;z++){
							if(c1.clone().add(0, y, z).getBlock().getType()==doorMaterial){
								c1.clone().add(0, y, z).getBlock().setType(Material.AIR);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.RED + c1.clone().add(0, y, z).getBlock().toString());
							}						
						}
						
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
								blockChange=false;
							}
						}
						
						y++;
						return;
					}
				}
				//new DoorRunnable(door, futureStat, player).runTaskTimer(plugin, 5*20L, 10L);
				this.cancel();			
			}else if(ot.equals(OpenType.RIGHT)){			
				if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
					for(;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;){
						for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
							if(c1.clone().add(x, y, 0).getBlock().getType()==doorMaterial){
								c1.clone().add(x, y, 0).getBlock().setType(Material.AIR);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c1.clone().add(x, y, 0).getBlock().toString());
							}
						}					
	
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
								blockChange=false;
							}
						}
						
						x++;
						return;
					}
				}
				
				if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
					for(;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;){
						for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
							if(c1.clone().add(0, y, z).getBlock().getType()==doorMaterial){
								c1.clone().add(0, y, z).getBlock().setType(Material.AIR);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c1.clone().add(0, y, z).getBlock().toString());
							}
						}					
	
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
								blockChange=false;
							}
						}
						
						z++;
						return;
					}
				}
				//new DoorRunnable(door, futureStat, player).runTaskTimer(plugin, 5*20L, 10L);
				this.cancel();			
			}else if(ot.equals(OpenType.LEFT)){			
				if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
					for(;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;){
						for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
							if(c2.clone().subtract(x, y, 0).getBlock().getType()==doorMaterial){
								c2.clone().subtract(x, y, 0).getBlock().setType(Material.AIR);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c2.clone().subtract(x, y, 0).getBlock().toString());
							}
						}					
	
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
								blockChange=false;
							}
						}
						
						x++;
						return;
					}
				}
				
				if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
					for(;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;){
						for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
							if(c2.clone().subtract(0, y, z).getBlock().getType()==doorMaterial){
								c2.clone().subtract(0, y, z).getBlock().setType(Material.AIR);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c2.clone().subtract(0, y, z).getBlock().toString());
							}
						}					
	
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
								blockChange=false;
							}
						}
						
						z++;
						return;
					}
				}
				//	new DoorRunnable(door, futureStat, player).runTaskTimer(plugin, 5*20L, 10L);
				this.cancel();	
			}else if(ot.equals(OpenType.MIDDLE)){
				
				if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
				
					int xLength = Math.abs(c2.getBlockX()-c1.getBlockX());
					
					if(xLength%2==1){
						Location tempLoc = c1.clone();
						tempLoc.add(xLength/2, 0, 0);
					
						for(;x<(Math.abs(c2.getBlockX()-c1.getBlockX()))/2;){
							for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
								if(tempLoc.clone().add(x+1, y, 0).getBlock().getType()==doorMaterial){
									tempLoc.clone().add(x+1, y, 0).getBlock().setType(Material.AIR);
									blockChange = true;
									Bukkit.broadcastMessage(ChatColor.BLUE + tempLoc.clone().add(x+1, y, 0).getBlock().toString());
								}
								if(tempLoc.clone().subtract(x, -y, 0).getBlock().getType()==doorMaterial){
									tempLoc.clone().subtract(x, -y, 0).getBlock().setType(Material.AIR);
									blockChange = true;
									Bukkit.broadcastMessage(ChatColor.GOLD + tempLoc.clone().subtract(x, -y, 0).getBlock().toString());
								}
							}
							
							if(player!=null){
								if(blockChange==true){
									player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
									blockChange=false;
								}
							}
							
							x++;
							return;
						}
					}else{
						Location tempLoc = c1.clone();
						tempLoc.add((xLength-1)/2, 0, 0);
					
						for(;x<(Math.abs(c2.getBlockX()-c1.getBlockX()))/2;){
							for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
								if(x==0){
									if(tempLoc.clone().add(x+1, y, 0).getBlock().getType()==doorMaterial){
										tempLoc.clone().add(x+1, y, 0).getBlock().setType(Material.AIR);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.GREEN + tempLoc.clone().add(x+1, y, 0).getBlock().toString());
									}
								}else{
									if(tempLoc.clone().add(x+1, y, 0).getBlock().getType()==doorMaterial){
										tempLoc.clone().add(x+1, y, 0).getBlock().setType(Material.AIR);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.BLUE + tempLoc.clone().add(x+1, y, 0).getBlock().toString());
									}
									if(tempLoc.clone().subtract(x-1, -y, 0).getBlock().getType()==doorMaterial){
										tempLoc.clone().subtract(x-1, -y, 0).getBlock().setType(Material.AIR);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.GOLD + tempLoc.clone().subtract(x-1, -y, 0).getBlock().toString());
									}
								}
							}
							
							if(player!=null){
								if(blockChange==true){
									player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
									blockChange=false;
								}
							}
							
							x++;
							return;
						}
					}
				}else if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
					int zLength = Math.abs(c2.getBlockZ()-c1.getBlockZ());
					
					if(zLength%2==1){
						Location tempLoc = c1.clone();
						tempLoc.add(0, 0, zLength/2);
					
						for(;z<(Math.abs(c2.getBlockZ()-c1.getBlockZ()))/2;){
							for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
								if(tempLoc.clone().add(0, y, z+1).getBlock().getType()==doorMaterial){
									tempLoc.clone().add(0, y, z+1).getBlock().setType(Material.AIR);
									blockChange = true;
									Bukkit.broadcastMessage(ChatColor.BLUE + tempLoc.clone().add(0, y, z+1).getBlock().toString());
								}
								if(tempLoc.clone().subtract(0, -y, z).getBlock().getType()==doorMaterial){
									tempLoc.clone().subtract(0, -y, z).getBlock().setType(Material.AIR);
									blockChange = true;
									Bukkit.broadcastMessage(ChatColor.GOLD + tempLoc.clone().subtract(0, -y, z).getBlock().toString());
								}
							}
							
							if(player!=null){
								if(blockChange==true){
									player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
									blockChange=false;
								}
							}
							
							z++;
							return;
						}
					}else{
						Location tempLoc = c1.clone();
						tempLoc.add(0, 0, (zLength+1)/2);
						
						for(;z<(Math.abs(c2.getBlockZ()-c1.getBlockZ()))/2;){
							for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
								if(z==0){
									if(tempLoc.clone().add(0, y, z).getBlock().getType()==doorMaterial){
										tempLoc.clone().add(0, y, z).getBlock().setType(Material.AIR);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.RED + tempLoc.clone().add(0, y, z).getBlock().toString());
									}
								}else{
									if(tempLoc.clone().add(0, y, z).getBlock().getType()==doorMaterial){
										tempLoc.clone().add(0, y, z).getBlock().setType(Material.AIR);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.BLUE + tempLoc.clone().add(0, y, z).getBlock().toString());
									}
									if(tempLoc.clone().subtract(0, -y, z).getBlock().getType()==doorMaterial){
										tempLoc.clone().subtract(0, -y, z).getBlock().setType(Material.AIR);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.GOLD + tempLoc.clone().subtract(0, -y, z).getBlock().toString());
									}
								}
							}
							
							if(player!=null){
								if(blockChange==true){
									player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
									blockChange=false;
								}
							}
							
							z++;
							return;
						}
					}
				}
				
				//new DoorRunnable(door, futureStat, player).runTaskTimer(plugin, 5*20L, 10L);
				this.cancel();	
			}else{
				this.cancel();
			}
		}else if(futureStat==DoorStat.CLOSE){ //door close
		
			if(ct.equals(CloseType.TOP)){			
				for(;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
					if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
						for(int x=0;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;x++){
							if(c2.clone().subtract(x, y, 0).getBlock().getType()==Material.AIR){
								c2.clone().subtract(x, y, 0).getBlock().setType(doorMaterial);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c2.clone().subtract(x, y, 0).getBlock().toString());
							}						
						}
						
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
								blockChange=false;
							}
						}
						
						y++;
						return;
					}
					
					if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
						for(int z=0;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;z++){
							if(c2.clone().subtract(0, y, z).getBlock().getType()==Material.AIR){
								c2.clone().subtract(0, y, z).getBlock().setType(doorMaterial);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.RED + c2.clone().subtract(0, y, z).getBlock().toString());
							}						
						}
						
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
								blockChange=false;
							}
						}
						
						y++;
						return;
					}
				}
				this.cancel();			
			}else if(ct.equals(CloseType.BOTTOM)){			
				for(;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
					if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
						for(int x=0;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;x++){
							if(c1.clone().add(x, y, 0).getBlock().getType()==Material.AIR){
								c1.clone().add(x, y, 0).getBlock().setType(doorMaterial);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c1.clone().add(x, y, 0).getBlock().toString());
							}						
						}
						
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
								blockChange=false;
							}
						}
						
						y++;
						return;
					}
					
					if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
						for(int z=0;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;z++){
							if(c1.clone().add(0, y, z).getBlock().getType()==Material.AIR){
								c1.clone().add(0, y, z).getBlock().setType(doorMaterial);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.RED + c1.clone().add(0, y, z).getBlock().toString());
							}						
						}
						
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
								blockChange=false;
							}
						}
						
						y++;
						return;
					}
				}
				this.cancel();			
			}else if(ct.equals(CloseType.RIGHT)){			
				if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
					for(;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;){
						for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
							if(c1.clone().add(x, y, 0).getBlock().getType()==Material.AIR){
								c1.clone().add(x, y, 0).getBlock().setType(doorMaterial);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c1.clone().add(x, y, 0).getBlock().toString());
							}
						}					
	
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
								blockChange=false;
							}
						}
						
						x++;
						return;
					}
				}
				
				if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
					for(;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;){
						for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
							if(c1.clone().add(0, y, z).getBlock().getType()==Material.AIR){
								c1.clone().add(0, y, z).getBlock().setType(doorMaterial);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c1.clone().add(0, y, z).getBlock().toString());
							}
						}					
	
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
								blockChange=false;
							}
						}
						
						z++;
						return;
					}
				}
				this.cancel();			
			}else if(ct.equals(CloseType.LEFT)){			
				if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
					for(;x<Math.abs(c2.getBlockX()-c1.getBlockX())+1;){
						for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
							if(c2.clone().subtract(x, y, 0).getBlock().getType()==Material.AIR){
								c2.clone().subtract(x, y, 0).getBlock().setType(doorMaterial);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c2.clone().subtract(x, y, 0).getBlock().toString());
							}
						}					
	
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
								blockChange=false;
							}
						}
						
						x++;
						return;
					}
				}
				
				if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
					for(;z<Math.abs(c2.getBlockZ()-c1.getBlockZ())+1;){
						for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
							if(c2.clone().subtract(0, y, z).getBlock().getType()==Material.AIR){
								c2.clone().subtract(0, y, z).getBlock().setType(doorMaterial);
								blockChange = true;
								Bukkit.broadcastMessage(ChatColor.GREEN + c2.clone().subtract(0, y, z).getBlock().toString());
							}
						}					
	
						if(player!=null){
							if(blockChange==true){
								player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
								blockChange=false;
							}
						}
						
						z++;
						return;
					}
				}
				this.cancel();	
			}else if(ct.equals(CloseType.MIDDLE)){				
				if(Math.abs(c2.getBlockX()-c1.getBlockX())>0){
				
					int xLength = Math.abs(c2.getBlockX()-c1.getBlockX());
					
					if(xLength%2==1){
						
						for(;x<(Math.abs(c2.getBlockX()-c1.getBlockX()))/2;){
							for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
								if(c1.clone().add(x+1, y, 0).getBlock().getType()==Material.AIR){
									c1.clone().add(x+1, y, 0).getBlock().setType(doorMaterial);
									blockChange = true;
									Bukkit.broadcastMessage(ChatColor.BLUE + c1.clone().add(x+1, y, 0).getBlock().toString());								
								}								
								if(c2.clone().subtract(x+1, y, 0).getBlock().getType()==Material.AIR){
									c2.clone().subtract(x+1, y, 0).getBlock().setType(doorMaterial);
									blockChange = true;
									Bukkit.broadcastMessage(ChatColor.GOLD + c2.clone().subtract(x+1, y, 0).getBlock().toString());
								}								
							}
							
							if(player!=null){
								if(blockChange==true){
									player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
									blockChange=false;
								}
							}
							
							x++;
							return;
						}
					}else{
						Location tempLoc = c1.clone();
						tempLoc.add((xLength+1)/2, 0, 0);
						
						for(;x<Math.abs(c2.getBlockX()-c1.getBlockX())/2;){
							for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
								if(x==(Math.abs(c2.getBlockX()-c1.getBlockX())/2)-1){
									if(tempLoc.clone().add(0, y, 0).getBlock().getType()==Material.AIR){
										tempLoc.clone().add(0, y, 0).getBlock().setType(doorMaterial);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.GREEN + tempLoc.clone().add(0, y, 0).getBlock().toString());
									}
								}else{
									if(c1.clone().add(x+1, y, 0).getBlock().getType()==Material.AIR){
										c1.clone().add(x+1, y, 0).getBlock().setType(doorMaterial);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.BLUE + c1.clone().add(x+1, y, 0).getBlock().toString());								
									}								
									if(c2.clone().subtract(x+1, y, 0).getBlock().getType()==Material.AIR){
										c2.clone().subtract(x+1, y, 0).getBlock().setType(doorMaterial);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.GOLD + c2.clone().subtract(x+1, y, 0).getBlock().toString());
									}
								}
							}
							
							if(player!=null){
								if(blockChange==true){
									player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
									blockChange=false;
								}
							}
							
							x++;
							return;
						}
					}
				}else if(Math.abs(c2.getBlockZ()-c1.getBlockZ())>0){
					int zLength = Math.abs(c2.getBlockZ()-c1.getBlockZ());
					
					if(zLength%2==1){
						Location tempLoc = c1.clone();
						tempLoc.add(0, 0, zLength/2);
					
						for(;z<(Math.abs(c2.getBlockZ()-c1.getBlockZ()))/2;){
							for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
								if(c1.clone().add(0, y, z+1).getBlock().getType()==Material.AIR){
									c1.clone().add(0, y, z+1).getBlock().setType(doorMaterial);
									blockChange = true;
									Bukkit.broadcastMessage(ChatColor.BLUE + c1.clone().add(0, y, z+1).getBlock().toString());								
								}								
								if(c2.clone().subtract(0, y, z+1).getBlock().getType()==Material.AIR){
									c2.clone().subtract(0, y, z+1).getBlock().setType(doorMaterial);
									blockChange = true;
									Bukkit.broadcastMessage(ChatColor.GOLD + c2.clone().subtract(0, y, z+1).getBlock().toString());
								}								
							}
							
							if(player!=null){
								if(blockChange==true){
									player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 80, 0);
									blockChange=false;
								}
							}
							
							z++;
							return;
						}
					}else{
						Location tempLoc = c1.clone();
						tempLoc.add(0, 0, ((zLength+1)/2));
						
						for(;z<(Math.abs(c2.getBlockZ()-c1.getBlockZ()))/2;){
							for(int y=0;y<Math.abs(c2.getBlockY()-c1.getBlockY()+1);y++){
								if(z==(Math.abs(c2.getBlockZ()-c1.getBlockZ())/2)-1){
									if(tempLoc.clone().add(0, y, 0).getBlock().getType()==Material.AIR){
										tempLoc.clone().add(0, y, 0).getBlock().setType(doorMaterial);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.GREEN + tempLoc.clone().add(0, y, 0).getBlock().toString());
									}
								}else{
									if(c1.clone().add(0, y, z+1).getBlock().getType()==Material.AIR){
										c1.clone().add(0, y, z+1).getBlock().setType(doorMaterial);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.BLUE + c1.clone().add(0, y, z+1).getBlock().toString());								
									}								
									if(c2.clone().subtract(0, y, z+1).getBlock().getType()==Material.AIR){
										c2.clone().subtract(0, y, z+1).getBlock().setType(doorMaterial);
										blockChange = true;
										Bukkit.broadcastMessage(ChatColor.GOLD + c2.clone().subtract(0, y, z+1).getBlock().toString());
									}
								}
							}
							
							if(player!=null){
								if(blockChange==true){
									player.playSound(player.getLocation(), Sound.PISTON_RETRACT, 80, 0);
									blockChange=false;
								}
							}
							
							z++;
							return;
						}
					}
				}				
				this.cancel();	
			}else{
				this.cancel();
			}	
		}
		this.cancel();
	}
}
