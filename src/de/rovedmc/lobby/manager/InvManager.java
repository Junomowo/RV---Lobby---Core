package de.rovedmc.lobby.manager;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.util.ItemBuilder;

public class InvManager {

	public static void animateHealth() {
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player all : Bukkit.getOnlinePlayers()){
					all.setHealth(1);
				}
				new BukkitRunnable() {
					
					@Override
					public void run() {
						for(Player all : Bukkit.getOnlinePlayers()){
							all.setHealth(2);
						}
						new BukkitRunnable() {
							
							@Override
							public void run() {
								for(Player all : Bukkit.getOnlinePlayers()){
									all.setHealth(3);
								}
								new BukkitRunnable() {
									
									@Override
									public void run() {
										for(Player all : Bukkit.getOnlinePlayers()){
											all.setHealth(4);
										}
										new BukkitRunnable() {
											
											@Override
											public void run() {
												for(Player all : Bukkit.getOnlinePlayers()){
													all.setHealth(5);
												}
												new BukkitRunnable() {
													
													@Override
													public void run() {
														for(Player all : Bukkit.getOnlinePlayers()){
															all.setHealth(6);
														}
														new BukkitRunnable() {
															
															@Override
															public void run() {
																for(Player all : Bukkit.getOnlinePlayers()){
																	all.setHealth(5);
																}
																new BukkitRunnable() {
																	
																	@Override
																	public void run() {
																		for(Player all : Bukkit.getOnlinePlayers()){
																			all.setHealth(4);
																		}
																		new BukkitRunnable() {
																			
																			@Override
																			public void run() {
																				for(Player all : Bukkit.getOnlinePlayers()){
																					all.setHealth(3);
																				}
																				new BukkitRunnable() {
																					
																					@Override
																					public void run() {
																						for(Player all : Bukkit.getOnlinePlayers()){
																							all.setHealth(2);
																						}
																						new BukkitRunnable() {
																							
																							@Override
																							public void run() {
																								for(Player all : Bukkit.getOnlinePlayers()){
																									all.setHealth(1);
																								}
																								animateHealth();
																							}
																						}.runTaskLater(Lobby.instance, 5);
																					}
																				}.runTaskLater(Lobby.instance, 5);
																			}
																		}.runTaskLater(Lobby.instance, 5);
																	}
																}.runTaskLater(Lobby.instance, 5);
															}
														}.runTaskLater(Lobby.instance, 5);
													}
												}.runTaskLater(Lobby.instance, 5);
											}
										}.runTaskLater(Lobby.instance, 5);
									}
								}.runTaskLater(Lobby.instance, 5);
							}
						}.runTaskLater(Lobby.instance, 5);
					}
				}.runTaskLater(Lobby.instance, 5);
			}
		}.runTaskLater(Lobby.instance, 5);
	}
	
	public static void setLobbyInventory(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		player.setMaxHealth(6);
		player.setHealth(6);
		player.setFoodLevel(20);
		player.getInventory().clear();
		player.setLevel(2020);
		
		player.getInventory().setItem(0, new ItemBuilder(Material.COMPASS)
				.setItemName("§cNavigator §8| §7rechtsklick")
				.setLore(Arrays.asList(
						"§7Zeige alle Warppunkte der Lobby an und ","§7gelange darüber zu den einzelnen Systemen!"))
				.getItemstack());
		
		HideManager.setHideItem(player);
		
		player.getInventory().setItem(4, new ItemBuilder(Material.CHEST)
				.setItemName("§bExtras §8| §7rechtsklick")
				.setLore(Arrays.asList(
						"§7Hier findest du alle gewonnen Extras ","§7die du in der Lobby nutzen kannst!"))
						.getItemstack());
		
		if (ExtraManager.UsedGadGet.get(player).isEmpty()) {
			player.getInventory().setItem(5, new ItemBuilder(Material.BARRIER)
					.setItemName("§cKein GadGet gewählt §8| §7rechtsklick")
					.setLore(Arrays.asList(
							"§7Bitte wähle in deinen Extras ein ","§7GadGet aus um es hier nutzen zu können!"))
							.getItemstack());
		}
		
		player.getInventory().setItem(7, new ItemBuilder(Material.NETHER_STAR)
				.setItemName("§eLobbys §8| §7rechtsklick")
				.setLore(Arrays.asList(
						"§7Hier findest du alle Lobbys die du ","§7benutzen kannst um dich zu bewegen!"))
						.getItemstack());
		
		player.getInventory().setItem(8, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
				.setItemName("§aProfil §8| §7rechtsklick").setSkullOwner(player.getName())
				.setLore(Arrays.asList(
						"§7Hier findest du alle Lobbys die du ","§7benutzen kannst um dich zu bewegen!"))
						.getItemstack());
		
		
	}
	
	public static void openNavigatorInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 6*9, "§cNavigator");
		inventory.setItem(0, new ItemBuilder(Material.JUKEBOX).setItemName("§aWerbung")
				.setLore(Arrays.asList("§7Teamspeak§8: §aRovedMC.de",
									   "§7Webseite§8: §aRovedMC.de",
						               "§7Shop§8: §7shop.RovedMC.de")).getItemstack());
		inventory.setItem(8, new ItemBuilder(Material.JUKEBOX).setItemName("§aWerbung")
				.setLore(Arrays.asList("§7Teamspeak§8: §aRovedMC.de",
									   "§7Webseite§8: §aRovedMC.de",
						               "§7Shop§8: §7shop.RovedMC.de")).getItemstack());
		inventory.setItem(45, new ItemBuilder(Material.JUKEBOX).setItemName("§aWerbung")
				.setLore(Arrays.asList("§7Teamspeak§8: §aRovedMC.de",
									   "§7Webseite§8: §aRovedMC.de",
						               "§7Shop§8: §7shop.RovedMC.de")).getItemstack());
		inventory.setItem(53, new ItemBuilder(Material.JUKEBOX).setItemName("§aWerbung")
				.setLore(Arrays.asList("§7Teamspeak§8: §aRovedMC.de",
									   "§7Webseite§8: §aRovedMC.de",
						               "§7Shop§8: §ashop.RovedMC.de")).getItemstack());
		inventory.setItem(29, new ItemBuilder(Material.SANDSTONE).setItemName("§eBuildFFA").getItemstack());
		inventory.setItem(21, new ItemBuilder(Material.MAGMA_CREAM).setItemName("§6Spawn").getItemstack());
		inventory.setItem(23, new ItemBuilder(Material.SKULL_ITEM).setItemName("§9Community").getItemstack());
		inventory.setItem(31, new ItemBuilder(Material.WOOD_AXE).setItemName("§9GunGame").getItemstack());
		player.openInventory(inventory);
	}
	
}
