package de.rovedmc.lobby.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.base.Ticker;

import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.util.ItemBuilder;

public class ChestOpeningManager {

	  public static File file = new File("plugins//Lobby//chestopening.yml");
	  public static FileConfiguration cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
	  
	  public static HashMap<Player, Inventory> PlayerChestOpeningInventory = new HashMap<Player, Inventory>();
	  
	  public static void save() {
	    try {
	      cfg.save(file);
	    } catch (IOException e) {
	      e.printStackTrace();
	    } 
	  }
	  
	  public static void createAndLoadFile() {
	    if (file.exists()) {
	      try {
	        cfg.load(file);
	      } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
	        e.printStackTrace();
	      } 
	    } else {
	      try {
	        file.createNewFile();
	      } catch (IOException e) {
	        e.printStackTrace();
	      } 
	      //==========================================================================================
	      List<ItemStack> list1 = new ArrayList<ItemStack>();
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e1000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e2000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e3000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e4000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e5000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e7000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e8000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e9000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e10000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e2000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e3000 Coins").getItemstack());
	      list1.add(new ItemBuilder(Material.GOLD_INGOT).setItemName("§e5000 Coins").getItemstack());
	      cfg.set("ItemList.Coins", list1);
	      //==========================================================================================
	      List<ItemStack> list2 = new ArrayList<ItemStack>();
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b1 Ticket").getItemstack());
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b2 Tickets").getItemstack());
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b3 Tickets").getItemstack());
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b5 Tickets").getItemstack());
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b6 Tickets").getItemstack());
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b7 Tickets").getItemstack());
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b8 Tickets").getItemstack());
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b9 Tickets").getItemstack());
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b10 Tickets").getItemstack());
	      list2.add(new ItemBuilder(Material.PAPER).setItemName("§b20 Tickets").getItemstack());
	      cfg.set("ItemList.Tickets", list2);
	      //==========================================================================================
	      List<ItemStack> list3 = new ArrayList<ItemStack>();
	      list3.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
	    		  .setSkullOwner("Minimichecker").setItemName("§5Minimichecker").getItemstack());
	      list3.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
	    		  .setSkullOwner("GermanLetsPlay").setItemName("§5GermanLetsPlay").getItemstack());
	      list3.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
	    		  .setSkullOwner("Paluten").setItemName("§5Paluten").getItemstack());
	      list3.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
	    		  .setSkullOwner("Logo").setItemName("§5Logo").getItemstack());
	      list2.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
	    		  .setSkullOwner("Poishii").setItemName("§5Poishii").getItemstack());
	      list2.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
	    		  .setSkullOwner("ByQuadrix").setItemName("§5ByQuadrix").getItemstack());
	      list2.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
	    		  .setSkullOwner("Izzi").setItemName("§5Izzi").getItemstack());
	      list2.add(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
	    		  .setSkullOwner("HerrBergmann").setItemName("§5HerrBergmann").getItemstack());
	      cfg.set("ItemList.Heads", list3);
	      //==========================================================================================
	      List<ItemStack> list4 = new ArrayList<ItemStack>();
	      list4.add(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.BLUE)
	    		  .setItemName("§9Blaue Schuhe").getItemstack());
	      list4.add(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.RED)
	    		  .setItemName("§4Rote Schuhe").getItemstack());
	      list4.add(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.YELLOW)
	    		  .setItemName("§eGelbe Schuhe").getItemstack());
	      list4.add(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.GREEN)
	    		  .setItemName("§9Grüne Schuhe").getItemstack());
	      list4.add(new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.AQUA)
	    		  .setItemName("§bAqua Schuhe").getItemstack());
	      cfg.set("ItemList.Boots", list4);
	      //==========================================================================================
	      List<ItemStack> list5 = new ArrayList<ItemStack>();
	      list5.add(new ItemBuilder(Material.STRING).setItemName("§4Love Effect").getItemstack());
	      list5.add(new ItemBuilder(Material.STRING).setItemName("§aMusic Effect").getItemstack());
	      cfg.set("ItemList.Effects", list5);
	      //==========================================================================================
	      save();
	    } 
	  }
	  
	  public static List<ItemStack> getList(String What){
		  List<?> unknownlist = cfg.getList("ItemList." + What);
		  List<ItemStack> list = new ArrayList<ItemStack>();
		  for (int i = 0; i < unknownlist.size(); i++) {
			list.add((ItemStack) unknownlist.get(i));
		  }
		  return list;
	  }  
	  
	  public static void openMenuInventory(Player player) {
		  String UUID = player.getUniqueId().toString();
		  Inventory inventory = Bukkit.createInventory(player, 6*9, "§aChestOpening §7Menü");
		  int Chests = ChestManager.getChests(UUID);
		  for (int i = 0; i < Chests; i++) {
			inventory.setItem(i, new ItemBuilder(Material.CHEST).setItemName("§aKiste").getItemstack());
		  }
		  if (Chests == 0) {
			  inventory.setItem(0, new ItemBuilder(Material.CHEST, 0).setItemName("§cKeine Kiste vorhanden").getItemstack());
		  }
		  int GlassSubID = Integer.valueOf(SettingsManager.getSetting(UUID, "GlassItem"));
		  inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID)
				  .setItemName(" ").getItemstack());
		  inventory.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID)
				  .setItemName(" ").getItemstack());
		  inventory.setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID)
				  .setItemName(" ").getItemstack());
		  inventory.setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID)
				  .setItemName(" ").getItemstack());
		  inventory.setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID)
				  .setItemName(" ").getItemstack());
		  inventory.setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID)
				  .setItemName(" ").getItemstack());
		  inventory.setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID)
				  .setItemName(" ").getItemstack());
		  inventory.setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID)
				  .setItemName(" ").getItemstack());
		  inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID)
				  .setItemName(" ").getItemstack());
		  inventory.setItem(49, new ItemBuilder(Material.CHEST).setItemName("§aKiste kaufen").getItemstack());
		  player.openInventory(inventory);
	  }
	  
	  public static ItemStack getRandomItem() {
		  int Random1 = getRandom(1, 5);
		  List<ItemStack> stacks = new ArrayList<ItemStack>();
		  if (Random1 == 1) {
			  stacks = getList("Coins");
		  }
		  if (Random1 == 2) {
			  stacks = getList("Tickets");
		  }
		  if (Random1 == 3) {
			  stacks = getList("Heads");
		  }
		  if (Random1 == 4) {
			  stacks = getList("Boots");
		  }
		  if (Random1 == 5) {
			  stacks = getList("Effects");
		  }
		  int Random2 = getRandom(0, stacks.size() -1);
		  return CraftItemStack.asCraftCopy(stacks.get(Random2));
	  }
	  
	  public static int getRandom(int lower, int upper) {
	        Random random = new Random();
	        return random.nextInt((upper - lower) + 1) + lower;
	    }
	  
	  public static void playChestopening(Player player) {
		  Inventory inventory = Bukkit.createInventory(player, 3*9, "§aChestOpening §eOpening");
		  PlayerChestOpeningInventory.put(player, inventory);
		  player.openInventory(inventory);
		  ItemStack stack1 = getRandomItem(); System.out.println(stack1);
		  ItemStack stack2 = getRandomItem(); System.out.println(stack2);
		  ItemStack stack3 = getRandomItem(); System.out.println(stack3);
		  ItemStack stack4 = getRandomItem(); System.out.println(stack4);
		  ItemStack stack5 = getRandomItem(); System.out.println(stack5);
		  ItemStack stack6 = getRandomItem(); System.out.println(stack6);
		  ItemStack stack7 = getRandomItem(); System.out.println(stack7);
		  ItemStack stack8 = getRandomItem(); System.out.println(stack8);
		  ItemStack stack9 = getRandomItem(); System.out.println(stack9);
		  inventory.setItem(4, new ItemBuilder(Material.JUKEBOX).setItemName("§aDein Gewinn").getItemstack());
		  inventory.setItem(22, new ItemBuilder(Material.JUKEBOX).setItemName("§aDein Gewinn").getItemstack());
		  
		  inventory.setItem(9, stack1);
		  inventory.setItem(10, stack2);
		  inventory.setItem(11, stack3);
		  inventory.setItem(12, stack4);
		  inventory.setItem(13, stack5);
		  inventory.setItem(14, stack6);
		  inventory.setItem(15, stack7);
		  inventory.setItem(16, stack8);
		  inventory.setItem(17, stack9);
		  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
		  new BukkitRunnable() {
			
			@Override
			public void run() {
				inventory.setItem(10, stack1);
				  inventory.setItem(11, stack2);
				  inventory.setItem(12, stack3);
				  inventory.setItem(13, stack4);
				  inventory.setItem(14, stack5);
				  inventory.setItem(15, stack6);
				  inventory.setItem(16, stack7);
				  inventory.setItem(17, stack8);
				  inventory.setItem(9, stack9);
				  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
				  new BukkitRunnable() {
						
						@Override
						public void run() {
							inventory.setItem(11, stack1);
							  inventory.setItem(12, stack2);
							  inventory.setItem(13, stack3);
							  inventory.setItem(14, stack4);
							  inventory.setItem(15, stack5);
							  inventory.setItem(16, stack6);
							  inventory.setItem(17, stack7);
							  inventory.setItem(9, stack8);
							  inventory.setItem(10, stack9);
							  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
							  new BukkitRunnable() {
									
									@Override
									public void run() {
										inventory.setItem(12, stack1);
										  inventory.setItem(13, stack2);
										  inventory.setItem(14, stack3);
										  inventory.setItem(15, stack4);
										  inventory.setItem(16, stack5);
										  inventory.setItem(17, stack6);
										  inventory.setItem(9, stack7);
										  inventory.setItem(10, stack8);
										  inventory.setItem(11, stack9);
										  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
										  new BukkitRunnable() {
												
												@Override
												public void run() {
													inventory.setItem(13, stack1);
													  inventory.setItem(14, stack2);
													  inventory.setItem(15, stack3);
													  inventory.setItem(16, stack4);
													  inventory.setItem(17, stack5);
													  inventory.setItem(9, stack6);
													  inventory.setItem(10, stack7);
													  inventory.setItem(11, stack8);
													  inventory.setItem(12, stack9);
													  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
													  new BukkitRunnable() {
															
															@Override
															public void run() {
																inventory.setItem(14, stack1);
																  inventory.setItem(15, stack2);
																  inventory.setItem(16, stack3);
																  inventory.setItem(17, stack4);
																  inventory.setItem(9, stack5);
																  inventory.setItem(10, stack6);
																  inventory.setItem(11, stack7);
																  inventory.setItem(12, stack8);
																  inventory.setItem(13, stack9);
																  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																  new BukkitRunnable() {
																		
																		@Override
																		public void run() {
																			inventory.setItem(15, stack1);
																			  inventory.setItem(16, stack2);
																			  inventory.setItem(17, stack3);
																			  inventory.setItem(9, stack4);
																			  inventory.setItem(10, stack5);
																			  inventory.setItem(11, stack6);
																			  inventory.setItem(12, stack7);
																			  inventory.setItem(13, stack8);
																			  inventory.setItem(14, stack9);
																			  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																			  new BukkitRunnable() {
																					
																					@Override
																					public void run() {
																						inventory.setItem(16, stack1);
																						  inventory.setItem(17, stack2);
																						  inventory.setItem(9, stack3);
																						  inventory.setItem(10, stack4);
																						  inventory.setItem(11, stack5);
																						  inventory.setItem(12, stack6);
																						  inventory.setItem(13, stack7);
																						  inventory.setItem(14, stack8);
																						  inventory.setItem(15, stack9);
																						  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																						  new BukkitRunnable() {
																								
																								@Override
																								public void run() {
																									inventory.setItem(17, stack1);
																									  inventory.setItem(9, stack2);
																									  inventory.setItem(10, stack3);
																									  inventory.setItem(11, stack4);
																									  inventory.setItem(12, stack5);
																									  inventory.setItem(13, stack6);
																									  inventory.setItem(14, stack7);
																									  inventory.setItem(15, stack8);
																									  inventory.setItem(16, stack9);
																									  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																									  new BukkitRunnable() {
																											
																											@Override
																											public void run() {
																												inventory.setItem(10, stack1);
																												  inventory.setItem(11, stack2);
																												  inventory.setItem(12, stack3);
																												  inventory.setItem(13, stack4);
																												  inventory.setItem(14, stack5);
																												  inventory.setItem(15, stack6);
																												  inventory.setItem(16, stack7);
																												  inventory.setItem(17, stack8);
																												  inventory.setItem(9, stack9);
																												  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																												  new BukkitRunnable() {
																														
																														@Override
																														public void run() {
																															inventory.setItem(11, stack1);
																															  inventory.setItem(12, stack2);
																															  inventory.setItem(13, stack3);
																															  inventory.setItem(14, stack4);
																															  inventory.setItem(15, stack5);
																															  inventory.setItem(16, stack6);
																															  inventory.setItem(17, stack7);
																															  inventory.setItem(9, stack8);
																															  inventory.setItem(10, stack9);
																															  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																															  new BukkitRunnable() {
																																	
																																	@Override
																																	public void run() {
																																		inventory.setItem(12, stack1);
																																		  inventory.setItem(13, stack2);
																																		  inventory.setItem(14, stack3);
																																		  inventory.setItem(15, stack4);
																																		  inventory.setItem(16, stack5);
																																		  inventory.setItem(17, stack6);
																																		  inventory.setItem(9, stack7);
																																		  inventory.setItem(10, stack8);
																																		  inventory.setItem(11, stack9);
																																		  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																		  new BukkitRunnable() {
																																				
																																				@Override
																																				public void run() {
																																					inventory.setItem(13, stack1);
																																					  inventory.setItem(14, stack2);
																																					  inventory.setItem(15, stack3);
																																					  inventory.setItem(16, stack4);
																																					  inventory.setItem(17, stack5);
																																					  inventory.setItem(9, stack6);
																																					  inventory.setItem(10, stack7);
																																					  inventory.setItem(11, stack8);
																																					  inventory.setItem(12, stack9);
																																					  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																					  new BukkitRunnable() {
																																							
																																							@Override
																																							public void run() {
																																								inventory.setItem(14, stack1);
																																								  inventory.setItem(15, stack2);
																																								  inventory.setItem(16, stack3);
																																								  inventory.setItem(17, stack4);
																																								  inventory.setItem(9, stack5);
																																								  inventory.setItem(10, stack6);
																																								  inventory.setItem(11, stack7);
																																								  inventory.setItem(12, stack8);
																																								  inventory.setItem(13, stack9);
																																								  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																								  new BukkitRunnable() {
																																										
																																										@Override
																																										public void run() {
																																											inventory.setItem(15, stack1);
																																											  inventory.setItem(16, stack2);
																																											  inventory.setItem(17, stack3);
																																											  inventory.setItem(9, stack4);
																																											  inventory.setItem(10, stack5);
																																											  inventory.setItem(11, stack6);
																																											  inventory.setItem(12, stack7);
																																											  inventory.setItem(13, stack8);
																																											  inventory.setItem(14, stack9);
																																											  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																											  new BukkitRunnable() {
																																													
																																													@Override
																																													public void run() {
																																														inventory.setItem(16, stack1);
																																														  inventory.setItem(17, stack2);
																																														  inventory.setItem(9, stack3);
																																														  inventory.setItem(10, stack4);
																																														  inventory.setItem(11, stack5);
																																														  inventory.setItem(12, stack6);
																																														  inventory.setItem(13, stack7);
																																														  inventory.setItem(14, stack8);
																																														  inventory.setItem(15, stack9);
																																														  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																														  new BukkitRunnable() {
																																																
																																																@Override
																																																public void run() {
																																																	inventory.setItem(17, stack1);
																																																	  inventory.setItem(9, stack2);
																																																	  inventory.setItem(10, stack3);
																																																	  inventory.setItem(11, stack4);
																																																	  inventory.setItem(12, stack5);
																																																	  inventory.setItem(13, stack6);
																																																	  inventory.setItem(14, stack7);
																																																	  inventory.setItem(15, stack8);
																																																	  inventory.setItem(16, stack9);
																																																	  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																																	  new BukkitRunnable() {
																																																			
																																																			@Override
																																																			public void run() {
																																																				inventory.setItem(10, stack1);
																																																				  inventory.setItem(11, stack2);
																																																				  inventory.setItem(12, stack3);
																																																				  inventory.setItem(13, stack4);
																																																				  inventory.setItem(14, stack5);
																																																				  inventory.setItem(15, stack6);
																																																				  inventory.setItem(16, stack7);
																																																				  inventory.setItem(17, stack8);
																																																				  inventory.setItem(9, stack9);
																																																				  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																																				  new BukkitRunnable() {
																																																						
																																																						@Override
																																																						public void run() {
																																																							inventory.setItem(11, stack1);
																																																							  inventory.setItem(12, stack2);
																																																							  inventory.setItem(13, stack3);
																																																							  inventory.setItem(14, stack4);
																																																							  inventory.setItem(15, stack5);
																																																							  inventory.setItem(16, stack6);
																																																							  inventory.setItem(17, stack7);
																																																							  inventory.setItem(9, stack8);
																																																							  inventory.setItem(10, stack9);
																																																							  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																																							  new BukkitRunnable() {
																																																									
																																																									@Override
																																																									public void run() {
																																																										inventory.setItem(12, stack1);
																																																										  inventory.setItem(13, stack2);
																																																										  inventory.setItem(14, stack3);
																																																										  inventory.setItem(15, stack4);
																																																										  inventory.setItem(16, stack5);
																																																										  inventory.setItem(17, stack6);
																																																										  inventory.setItem(9, stack7);
																																																										  inventory.setItem(10, stack8);
																																																										  inventory.setItem(11, stack9);
																																																										  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																																										  new BukkitRunnable() {
																																																												
																																																												@Override
																																																												public void run() {
																																																													inventory.setItem(13, stack1);
																																																													  inventory.setItem(14, stack2);
																																																													  inventory.setItem(15, stack3);
																																																													  inventory.setItem(16, stack4);
																																																													  inventory.setItem(17, stack5);
																																																													  inventory.setItem(9, stack6);
																																																													  inventory.setItem(10, stack7);
																																																													  inventory.setItem(11, stack8);
																																																													  inventory.setItem(12, stack9);
																																																													  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																																													  new BukkitRunnable() {
																																																															
																																																															@Override
																																																															public void run() {
																																																																inventory.setItem(14, stack1);
																																																																  inventory.setItem(15, stack2);
																																																																  inventory.setItem(16, stack3);
																																																																  inventory.setItem(17, stack4);
																																																																  inventory.setItem(9, stack5);
																																																																  inventory.setItem(10, stack6);
																																																																  inventory.setItem(11, stack7);
																																																																  inventory.setItem(12, stack8);
																																																																  inventory.setItem(13, stack9);
																																																																  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																																																  new BukkitRunnable() {
																																																																		
																																																																		@Override
																																																																		public void run() {
																																																																			inventory.setItem(15, stack1);
																																																																			  inventory.setItem(16, stack2);
																																																																			  inventory.setItem(17, stack3);
																																																																			  inventory.setItem(9, stack4);
																																																																			  inventory.setItem(10, stack5);
																																																																			  inventory.setItem(11, stack6);
																																																																			  inventory.setItem(12, stack7);
																																																																			  inventory.setItem(13, stack8);
																																																																			  inventory.setItem(14, stack9);
																																																																			  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																																																			  new BukkitRunnable() {
																																																																					
																																																																					@Override
																																																																					public void run() {
																																																																						inventory.setItem(16, stack1);
																																																																						  inventory.setItem(17, stack2);
																																																																						  inventory.setItem(9, stack3);
																																																																						  inventory.setItem(10, stack4);
																																																																						  inventory.setItem(11, stack5);
																																																																						  inventory.setItem(12, stack6);
																																																																						  inventory.setItem(13, stack7);
																																																																						  inventory.setItem(14, stack8);
																																																																						  inventory.setItem(15, stack9);
																																																																						  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																																																						  new BukkitRunnable() {
																																																																								
																																																																								@Override
																																																																								public void run() {
																																																																									inventory.setItem(17, stack1);
																																																																									  inventory.setItem(9, stack2);
																																																																									  inventory.setItem(10, stack3);
																																																																									  inventory.setItem(11, stack4);
																																																																									  inventory.setItem(12, stack5);
																																																																									  inventory.setItem(13, stack6);
																																																																									  inventory.setItem(14, stack7);
																																																																									  inventory.setItem(15, stack8);
																																																																									  inventory.setItem(16, stack9);
																																																																									  player.playSound(player.getLocation(), Sound.CLICK, 3, 3);
																																																																									  new BukkitRunnable() {
																																																																											
																																																																											@Override
																																																																											public void run() {
																																																																												inventory.setItem(9, stack1);
																																																																												  inventory.setItem(10, stack2);
																																																																												  inventory.setItem(11, stack3);
																																																																												  inventory.setItem(12, stack4);
																																																																												  inventory.setItem(13, stack5);
																																																																												  inventory.setItem(14, stack6);
																																																																												  inventory.setItem(15, stack7);
																																																																												  inventory.setItem(16, stack8);
																																																																												  inventory.setItem(17, stack9);
																																																																												  player.playSound(player.getLocation(), Sound.LEVEL_UP, 3, 3);
																																																																												  playWinn(player, stack5);
																																																																												  new BukkitRunnable() {
																																																																														
																																																																														@Override
																																																																														public void run() {
																																																																															  PlayerChestOpeningInventory.remove(player);
																																																																															  player.closeInventory();
																																																																															  
																																																																														}
																																																																													}.runTaskLater(Lobby.instance, 20);
																																																																												  
																																																																											}
																																																																										}.runTaskLater(Lobby.instance, 20);
																																																																								}
																																																																							}.runTaskLater(Lobby.instance, 20);//==============
																																																																					}
																																																																				}.runTaskLater(Lobby.instance, 20);
																																																																		}
																																																																	}.runTaskLater(Lobby.instance, 20);
																																																															}
																																																														}.runTaskLater(Lobby.instance, 20);
																																																												}
																																																											}.runTaskLater(Lobby.instance, 20);
																																																									}
																																																								}.runTaskLater(Lobby.instance, 20);
																																																						}
																																																					}.runTaskLater(Lobby.instance, 20);
																																																			}
																																																		}.runTaskLater(Lobby.instance, 20);
																																																}
																																															}.runTaskLater(Lobby.instance, 10);//==============
																																													}
																																												}.runTaskLater(Lobby.instance, 10);
																																										}
																																									}.runTaskLater(Lobby.instance, 10);
																																							}
																																						}.runTaskLater(Lobby.instance, 10);
																																				}
																																			}.runTaskLater(Lobby.instance, 10);
																																	}
																																}.runTaskLater(Lobby.instance, 10);
																														}
																													}.runTaskLater(Lobby.instance, 10);
																											}
																										}.runTaskLater(Lobby.instance, 10);
																								}
																							}.runTaskLater(Lobby.instance, 5);//==============
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
	
	  public static void playWinn(Player player, ItemStack itemStack) {
		  if (itemStack.getType() == Material.GOLD_INGOT) {
			  int Amount = Integer.valueOf(itemStack.getItemMeta().getDisplayName().replace("§e", "").replace(" Coins", ""));
			  player.sendMessage(Lobby.Prefix + "§7Du hast §e" + Amount + " Coins §7gewonnen!");
			  CoinsManager.updateCoins(player.getUniqueId().toString(), CoinsManager.getCoins(player.getUniqueId().toString()) + Amount);
		  } else if (itemStack.getType() == Material.PAPER){
			  int Amount = Integer.valueOf(itemStack.getItemMeta().getDisplayName().replace("§b", "").replace(" Tickets", ""));
			  player.sendMessage(Lobby.Prefix + "§7Du hast §b" + Amount + " Tickets §7gewonnen!");
			  
		  } else if (itemStack.getType() == Material.SKULL_ITEM) {
			  String Win = itemStack.getItemMeta().getDisplayName().replace("§5", "").replace("§4", "");
			  player.sendMessage(Lobby.Prefix + "§7Du hast §5" + Win + " §7gewonnen!");
			  ExtraManager.addExtra(player.getUniqueId().toString(), "Heads", Win);
		  } else if (itemStack.getType() == Material.LEATHER_BOOTS) {
			  String Win = itemStack.getItemMeta().getDisplayName().replace("§5", "").replace("§4", "").replace("§a", "").replace("§b", "").replace("§e", "")
					  .replace("§d", "").replace("§1", "").replace("§2", "").replace("§3", "").replace("§6", "").replace("§7", "").replace("§9", "").replace(" Schuhe", "");
			  player.sendMessage(Lobby.Prefix + "§7Du hast §5" + itemStack.getItemMeta().getDisplayName() + " §7gewonnen!");
			  ExtraManager.addExtra(player.getUniqueId().toString(), "Boots", Win);
		  } else if (itemStack.getType() == Material.LEATHER_BOOTS) {
			  String Win = itemStack.getItemMeta().getDisplayName().replace("§5", "").replace("§4", "").replace("§a", "").replace("§b", "").replace("§e", "")
					  .replace("§d", "").replace("§1", "").replace("§2", "").replace("§3", "").replace("§6", "").replace("§7", "").replace("§9", "").replace(" Effect", "");
			  player.sendMessage(Lobby.Prefix + "§7Du hast §5" + itemStack.getItemMeta().getDisplayName() + " §7gewonnen!");
			  ExtraManager.addExtra(player.getUniqueId().toString(), "Effects", Win);
		  }
	  }
	  
}
