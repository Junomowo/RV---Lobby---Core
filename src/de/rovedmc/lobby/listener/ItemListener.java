package de.rovedmc.lobby.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.manager.ChestManager;
import de.rovedmc.lobby.manager.ChestOpeningManager;
import de.rovedmc.lobby.manager.CoinsManager;
import de.rovedmc.lobby.manager.ExtraManager;
import de.rovedmc.lobby.manager.FriendManager;
import de.rovedmc.lobby.manager.HideManager;
import de.rovedmc.lobby.manager.InvManager;
import de.rovedmc.lobby.manager.NavigatorManager;
import de.rovedmc.lobby.manager.ProfilManager;
import de.rovedmc.lobby.manager.ScoreManager;
import de.rovedmc.lobby.manager.SettingsManager;

public class ItemListener implements Listener{
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onItemKlick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if (!Lobby.Builders.contains(player)) {
			event.setCancelled(true);
			if (event.getInventory().getTitle().equals("§5Spieler Sichtbarkeit")) {
				if (event.getRawSlot() == 11) {
					if (HideManager.getHide(player).equals("All")){
						player.sendMessage(Lobby.Prefix + "§cDu siehst bereits alle Spieler!");
						return;
					}
					HideManager.HideSetting.put(player, "All");
					HideManager.setHideItem(player);
					HideManager.openHiderInventory(player);
					player.sendMessage(Lobby.Prefix + "§7Du siehst nun §aalle Spieler§7!");
					HideManager.manageHide(player);
				} else if (event.getRawSlot() == 13) {
					if (HideManager.getHide(player).equals("VIP")){
						player.sendMessage(Lobby.Prefix + "§cDu siehst bereits nur noch VIPs!");
						return;
					}
					HideManager.HideSetting.put(player, "VIP");
					HideManager.setHideItem(player);
					HideManager.openHiderInventory(player);
					player.sendMessage(Lobby.Prefix + "§7Du siehst nun §5nur noch VIPs§7!");
					HideManager.manageHide(player);
				} else if (event.getRawSlot() == 15) {
					if (HideManager.getHide(player).equals("None")){
						player.sendMessage(Lobby.Prefix + "§cDu siehst bereits keine Spieler!");
						return;
					}
					HideManager.HideSetting.put(player, "None");
					HideManager.setHideItem(player);
					HideManager.openHiderInventory(player);
					player.sendMessage(Lobby.Prefix + "§7Du siehst nun §ckeine Spieler§7!");
					HideManager.manageHide(player);
				} else if (event.getRawSlot() == 37) {
					if (event.getCurrentItem().getType() == Material.getMaterial(351)) {
						if (HideManager.getHideVIPList(player).contains("Team")) {
							HideManager.rmvHideVIP("Team", player);
							player.sendMessage(Lobby.Prefix + "§7Du siehst nun nicht mehr §cTeammitglieder§7!");
							HideManager.openHiderInventory(player);
						} else {
							HideManager.addHideVIP("Team", player);
							player.sendMessage(Lobby.Prefix + "§7Du siehst nun §cTeammitglieder§7!");
							HideManager.openHiderInventory(player);
						}
					}
				} else if (event.getRawSlot() == 39) {
					if (event.getCurrentItem().getType() == Material.getMaterial(351)) {
						if (HideManager.getHideVIPList(player).contains("YouTuber")) {
							HideManager.rmvHideVIP("YouTuber", player);
							player.sendMessage(Lobby.Prefix + "§7Du siehst nun nicht mehr §5YouTuber§7!");
							HideManager.openHiderInventory(player);
						} else {
							HideManager.addHideVIP("YouTuber", player);
							player.sendMessage(Lobby.Prefix + "§7Du siehst nun §5YouTuber§7!");
							HideManager.openHiderInventory(player);
						}
					}
				} else if (event.getRawSlot() == 41) {
					if (event.getCurrentItem().getType() == Material.getMaterial(351)) {
						if (HideManager.getHideVIPList(player).contains("Premium")) {
							HideManager.rmvHideVIP("Premium", player);
							player.sendMessage(Lobby.Prefix + "§7Du siehst nun nicht mehr §6Premium§7!");
							HideManager.openHiderInventory(player);
						} else {
							HideManager.addHideVIP("Premium", player);
							player.sendMessage(Lobby.Prefix + "§7Du siehst nun §6Premium§7!");
							HideManager.openHiderInventory(player);
						}
					}
				} else if (event.getRawSlot() == 43) {
					if (event.getCurrentItem().getType() == Material.getMaterial(351)) {
						if (HideManager.getHideVIPList(player).contains("Freunde")) {
							HideManager.rmvHideVIP("Freunde", player);
							player.sendMessage(Lobby.Prefix + "§7Du siehst nun nicht mehr §aFreunde§7!");
							HideManager.openHiderInventory(player);
						} else {
							HideManager.addHideVIP("Freunde", player);
							player.sendMessage(Lobby.Prefix + "§7Du siehst nun §aFreunde§7!");
							HideManager.openHiderInventory(player);
						}
					}
				}
			} else if (event.getInventory().getTitle().equals("§cNavigator")) {
				boolean isNavLoc = false;
				String Tel = "";
				if (event.getRawSlot() == 21) {
					player.closeInventory();
					player.teleport(Lobby.Spawn);
					player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 3);
					player.sendMessage(Lobby.Prefix + "§7Du wurdest zum §aSpawn §7teleportiert!");
					ScoreManager.setScore(player);
				}
				if (event.getRawSlot() == 29) {
					isNavLoc = true;
					Tel = "BuildFFA";
				}
				if (event.getRawSlot() == 31) {
					isNavLoc = true;
					Tel = "GunGame";
				}
				if (isNavLoc == true) {
					player.closeInventory();
					if (!NavigatorManager.getNavLocs().contains(Tel)) {
						player.sendMessage(Lobby.Prefix + "§cDie Location für diesen Warp wurde noch nicht gesetzt!");
						return;
					}
					player.teleport(NavigatorManager.getNavLocation(Tel));
					player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 3, 3);
					player.sendMessage(Lobby.Prefix + "§7Du wurdest zu §a" + Tel + " §7teleportiert");
					ScoreManager.setScore(player);
				}
			} else if (event.getInventory().getTitle().equals("§aChestOpening §7Menü")) {
				String UUID = player.getUniqueId().toString();
				if (event.getRawSlot() == 49) {
					int Coins = CoinsManager.getCoins(UUID);
					int Chests = ChestManager.getChests(UUID);
					if (Chests >= 36) {
						player.sendMessage(Lobby.Prefix + "§cDu darfst nur maximal §436 §cKisten besitzen!");
						return;
					}
					if (Coins < 10000) {
						player.sendMessage(Lobby.Prefix + "§cDu hast nich genügend Coins!");
						return;
					}
					CoinsManager.updateCoins(UUID, Coins -1000);
					ChestManager.updateChests(UUID, Chests +1);
					player.closeInventory();
					ChestOpeningManager.openMenuInventory(player);
					player.sendMessage(Lobby.Prefix + "§7Du hast eine §aKiste §7für §e10.000 Coins §7gekauft");
					ScoreManager.setScore(player);
				}
				if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aKiste")) {
					int Chests = ChestManager.getChests(UUID);
					ChestManager.updateChests(UUID, Chests -1);
					ChestOpeningManager.playChestopening(player);
					ScoreManager.setScore(player);
				}
			} else if (event.getInventory().getTitle().equals("§bExtras §7Menü")) {
				if (event.getRawSlot() == 4) {
					ExtraManager.openExtraHeads(player);
				}
			} else if (event.getInventory().getTitle().equals("§bExtras §aKöpfe")) {
				if (event.getCurrentItem().getType() == Material.SKULL_ITEM && 
						!event.getCurrentItem().getItemMeta().getDisplayName().equals("§cKeine Köpfe vorhanden!")) {
//					String Name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§5", "").replace("§4", "").replace("§a", "").replace("§b", "").replace("§e", "")
//							  .replace("§d", "").replace("§1", "").replace("§2", "").replace("§3", "").replace("§6", "").replace("§7", "").replace("§9", "").replace(" Schuhe", "");
					String Name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§5", "").replace("§4", "");
					ExtraManager.SellItem.put(player, "Heads;" + Name + ";10000;");
					ExtraManager.openSellShop(player, event.getCurrentItem());
					
					//					player.closeInventory();
//					player.getInventory().setHelmet(event.getCurrentItem());
//					player.playSound(player.getLocation(), Sound.BURP, 3, 3);
//					player.sendMessage(Lobby.Prefix + "§7Du hast " + event.getCurrentItem().getItemMeta().getDisplayName() + " §7aufgesetzt!");
				}
				if (event.getRawSlot() == 49) {
					player.getInventory().setHelmet(null);
					player.playSound(player.getLocation(), Sound.BURP, 3, 3);
					player.sendMessage(Lobby.Prefix + "§7Du hast deine Maske abgesetzt!");
				}
			} else if (event.getInventory().getTitle().startsWith("§aItemverkauf")) { 
				if (event.getRawSlot() == 15) {
					List<String> selllist = new ArrayList<String>();
					for (String split : ExtraManager.SellItem.get(player).split(";")) {
						selllist.add(split);
					}
					String ExtraKategorie = selllist.get(0);
					String ExtraName = selllist.get(1);
					int Coins = Integer.valueOf(selllist.get(2));
					
					player.closeInventory();
					ExtraManager.rmvExtra(player.getUniqueId().toString(), ExtraKategorie, ExtraName);
					CoinsManager.updateCoins(player.getUniqueId().toString(), CoinsManager.getCoins(player.getUniqueId().toString()) + Coins);
					ScoreManager.setScore(player);
					if (player.getInventory().getHelmet() == event.getInventory().getItem(13)) {
						player.getInventory().setHelmet(null);
						player.sendMessage(Lobby.Prefix + "§cDu hattest das verkaufte Item activiert! Es wurde nun deaktiviert!");
					}
					player.sendMessage(Lobby.Prefix + "§7Du hast ein Item verkauft und §e" + Coins + " Coins §7erhalten!");
					
					player.playSound(player.getLocation(), Sound.BURP, 3, 3);
				}
				if (event.getRawSlot() == 11) {
					ItemStack item = event.getInventory().getItem(13);	
					player.getInventory().setHelmet(item);
					player.playSound(player.getLocation(), Sound.BURP, 3, 3);
					player.sendMessage(Lobby.Prefix + "§7Du hast " + item.getItemMeta().getDisplayName() + " §7aufgesetzt!");
					player.closeInventory();
				}
			} else if (event.getInventory().getTitle().equals("§bExtras §aBoots")) {
				if (event.getCurrentItem().getType() == Material.LEATHER_BOOTS && 
						!event.getCurrentItem().getItemMeta().getDisplayName().equals("§cKeine Boots vorhanden")) {
					player.closeInventory();
					player.getInventory().setHelmet(event.getCurrentItem());
					player.playSound(player.getLocation(), Sound.BURP, 3, 3);
					player.sendMessage(Lobby.Prefix + "§7Du hast " + event.getCurrentItem().getItemMeta().getDisplayName() + " §7aufgesetzt!");
				}
				if (event.getRawSlot() == 49) {
					player.getInventory().setHelmet(null);
					player.playSound(player.getLocation(), Sound.BURP, 3, 3);
					player.sendMessage(Lobby.Prefix + "§7Du hast deine Maske abgesetzt!");
				}
			} else if (event.getInventory().getTitle().equals("§aFreunde")) {
				if (event.getRawSlot() == 49) {
					ProfilManager.openSettingMenü(player);
				}
			} else if (event.getInventory().getTitle().equals("§cEinstellungen")) {
				if (event.getRawSlot() == 10) {
					ProfilManager.openSettingGlassPlate(player);
				}
				if (event.getRawSlot() == 49) {
					ProfilManager.openSettingMenü(player);
				}
				if (event.getRawSlot() == 45) {
					ProfilManager.openFriendMenü(player, 1);
				}
				if (event.getRawSlot() == 19) {
					int GlassSubID = Integer.valueOf(SettingsManager.getSetting(player.getUniqueId().toString(), "GlassItem"));
					if (GlassSubID == 7) {
						player.sendMessage(Lobby.Prefix + "§cDu hast bereits diese Einstellung auf default!");
						return;
					}
					SettingsManager.setSettings(player.getUniqueId().toString(), "GlassItem", String.valueOf(7));
					ProfilManager.openSettingMenü(player);
					player.sendMessage(Lobby.Prefix + "§7Du hast eine Einstellung geändert!");
					player.playSound(player.getLocation(), Sound.ANVIL_USE, 3, 3);
				}
			} else if (event.getInventory().getTitle().equals("§cEinstellungen §aGlass")) {
				if (event.getRawSlot() == 49) {
					ProfilManager.openSettingMenü(player);
				}
				if (event.getRawSlot() == 45) {
					ProfilManager.openFriendMenü(player, 1);
				}
				if (event.getRawSlot() >= 9 && event.getRawSlot() <= 30) {
					if (event.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
						int SubID = event.getCurrentItem().getDurability();
						SettingsManager.setSettings(player.getUniqueId().toString(), "GlassItem", String.valueOf(SubID));
						ProfilManager.openSettingGlassPlate(player);
						player.sendMessage(Lobby.Prefix + "§7Du hast eine Einstellung geändert!");
						player.playSound(player.getLocation(), Sound.ANVIL_USE, 3, 3);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if (!Lobby.Builders.contains(player)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onItemInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getItem().getType() == Material.getMaterial(351)) {
			HideManager.openHiderInventory(player);
		}
		if (event.getItem().getType() == Material.COMPASS) {
			InvManager.openNavigatorInventory(player);
		}
		if (event.getItem().getType() == Material.CHEST) {
			ExtraManager.openExtraMenuInventory(player);
		}
		if (event.getItem().getType() == Material.SKULL_ITEM) {
			ProfilManager.openFriendMenü(player, 1);
		}
	}
	
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!Lobby.Builders.contains(player)) {
			event.setCancelled(true);
			if (event.getClickedBlock().getType() == Material.CHEST) {
				if (event.getClickedBlock().getLocation().equals(Lobby.ChestLocation)) {
					if (ChestOpeningManager.PlayerChestOpeningInventory.containsKey(player)) {
						player.sendMessage(Lobby.Prefix + "§cDu hast ein Opening am laufen bereits!");
						player.openInventory(ChestOpeningManager.PlayerChestOpeningInventory.get(player));
						return;
					}
					ChestOpeningManager.openMenuInventory(player);
				}
			}
		}
	}

}
