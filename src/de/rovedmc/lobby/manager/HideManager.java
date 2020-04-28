package de.rovedmc.lobby.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.util.ItemBuilder;
import de.rovedmc.lobby.util.MySQL;

public class HideManager {
	
	public static HashMap<Player, String> HideSetting = new HashMap<Player, String>();
	public static HashMap<Player, String> HideVIPs = new HashMap<Player, String>();
	
	public static void managePlayer(String UUID, String Name) {
		if (!existPlayer(UUID)) {
			MySQL.update("INSERT INTO Hide(UUID, Name, Hide, HideVIP) VALUES ('" + UUID + "','" + Name + "','all','Team;YouTuber;')");
		} else {
			if (!getNameByUUID(UUID).equals(Name)) {
				MySQL.update("UPDATE Hide SET Name='" + Name + "' WHERE UUID='" + UUID + "'");
			}
		}
	}
	
	public static void loadStats(Player player) {
		HideSetting.put(player, getHide(player.getUniqueId().toString()));
		HideVIPs.put(player, get("HideVIP", "Hide", "UUID", player.getUniqueId().toString()));
	}
	
	public static void saveStats(Player player) {
		setHide(player.getUniqueId().toString(), "Hide", HideSetting.get(player));
		setHide(player.getUniqueId().toString(), "HideVIP", HideVIPs.get(player));
	}
	
	public static void setHide(String UUID, String What, String Setting) {
		MySQL.update("UPDATE Hide SET " + What + "='" + Setting + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void addHideVIP(String What, Player player) {
		String ListRaw = HideVIPs.get(player) + What + ";";
		HideVIPs.put(player, ListRaw);
	}
	
	public static void rmvHideVIP(String What, Player player) {
		String ListRaw = HideVIPs.get(player).replace(What + ";", "");
		HideVIPs.put(player, ListRaw);
	}
	
	public static List<String> getHideVIPList(Player player){
		String RawList = HideVIPs.get(player);
		List<String> list = new ArrayList<String>();
		if (RawList.isEmpty()) return list;
		for (String split : RawList.split(";")) {
			list.add(split);
		}
		return list;
	}
	
	public static String getHide(String UUID) {
		return get("Hide", "Hide", "UUID", UUID);
	}
	
	public static String getHide(Player player) {
		return HideSetting.get(player);
	}
	
	public static String getNameByUUID(String UUID) {
		return get("Name", "Hide", "UUID", UUID);
	}
	
	public static String getUUIDByName(String Name) {
		return get("UUID", "Hide", "Name", Name);
	}
	
	public static boolean existPlayer(String UUID) {
		ResultSet rs = MySQL.query("SELECT UUID FROM " + "Hide" + " WHERE UUID='" + UUID + "'");
		try {
			if (rs.next()) {
				return rs.getString("UUID") != null;
			}
			rs.close();
			return false;
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	public static String get(String Select, String Database, String Where, String Whereresult) {
		ResultSet rs = MySQL
				.query("SELECT " + Select + " FROM " + Database + " WHERE " + Where + "='" + Whereresult + "'");
		try {
			if (rs.next()) {
				String result = rs.getString(Select);
				return result;
			}
		} catch (SQLException ex) {
			return "ERROR";
		}
		return "ERROR";
	}
	
	public static void manageAllHide() {
		for (Player all : Lobby.AllPlayer) {
			manageHide(all);
		}
	}
	
	public static void manageHide(Player player) {
		String Setting = HideSetting.get(player);
		if (Setting.equals("All")) {
			for (Player allPlayer : Lobby.AllPlayer) {
				player.showPlayer(allPlayer);
			}
		} else if (Setting.equals("VIP")) {
			for (Player allPlayer : Lobby.AllPlayer) {
				player.hidePlayer(allPlayer);
			}
			if (getHideVIPList(player).contains("Team")) {
				for (Player allPlayer : Lobby.AllPlayer) {
					if (allPlayer.hasPermission("Hider.Team")) {
						player.showPlayer(allPlayer);
					}					
				}
			}
			if (getHideVIPList(player).contains("YouTuber")) {
				for (Player allPlayer : Lobby.AllPlayer) {
					if (allPlayer.hasPermission("Hider.YouTuber")) {
						player.showPlayer(allPlayer);
					}					
				}
			}
			if (getHideVIPList(player).contains("Freunde")) {
				for (Player allPlayer : Lobby.AllPlayer) {
					if (FriendManager.getFriendList(player.getUniqueId().toString()).contains(allPlayer.getUniqueId().toString())) {
						player.showPlayer(allPlayer);
					}					
				}
			}
		} else {
			for (Player allPlayer : Lobby.AllPlayer) {
				player.hidePlayer(allPlayer);
			}
		}
		
	}
	
	public static void setHideItem(Player player) {
		String Setting = HideSetting.get(player);
		Material material = Material.getMaterial(351);
		int SubID = 0;
		String Name = "";
		if (Setting.equals("None")) {
			Name = "§cKeine Spieler anzeigen §8| §7rechtsklick";
			SubID = 1;
		} else if (Setting.equals("VIP")) {
			Name = "§5Nur VIPs anzeigen §8| §7rechtsklick";
			SubID = 5;
		} else {
			Name = "§aAlle Spieler anzeigen §8| §7rechtsklick";
			SubID = 10;
		}
		player.getInventory().setItem(1, new ItemBuilder(material, 1, (byte) SubID)
				.setItemName(Name).setLore(Arrays.asList("§7Stelle ein, welche Spieler du auf der "
						+ "","§7Lobby sehen willst und welche nicht!")).getItemstack());
	}
	
	public static void openHiderInventory(Player player) {
		String Setting = HideSetting.get(player);
		if (Setting.equals("None")) {
			Inventory inventory = Bukkit.createInventory(player, 3*9, "§5Spieler Sichtbarkeit");
			inventory.setItem(11, new ItemBuilder(Material.getMaterial(351), 1, (byte) 10)
					.setItemName("§aAlle anzeigen").getItemstack());
			inventory.setItem(13, new ItemBuilder(Material.getMaterial(351), 1, (byte) 5)
					.setItemName("§5Nur VIPs anzeigen").getItemstack());
			inventory.setItem(15, new ItemBuilder(Material.getMaterial(351), 1, (byte) 1)
					.setItemName("§cNiemanden anzeigen").addEnchant(Enchantment.DIG_SPEED, 1).getItemstack());
			player.openInventory(inventory);
		} else if (Setting.equals("VIP")) {
			Inventory inventory = Bukkit.createInventory(player, 6*9, "§5Spieler Sichtbarkeit");
			inventory.setItem(11, new ItemBuilder(Material.getMaterial(351), 1, (byte) 10)
					.setItemName("§aAlle anzeigen").getItemstack());
			inventory.setItem(13, new ItemBuilder(Material.getMaterial(351), 1, (byte) 5)
					.setItemName("§5Nur VIPs anzeigen").addEnchant(Enchantment.DIG_SPEED, 1).getItemstack());
			inventory.setItem(15, new ItemBuilder(Material.getMaterial(351), 1, (byte) 1)
					.setItemName("§cNiemanden anzeigen").getItemstack());
			
			inventory.setItem(28, CraftItemStack.asBukkitCopy(SkullManager.RegisterSkulls.get("Team")));
			if (getHideVIPList(player).contains("Team")) {
				inventory.setItem(37, new ItemBuilder(Material.getMaterial(351), 1, (byte) 10)
						.setItemName("§aactiv").getItemstack());
			} else {
				inventory.setItem(37, new ItemBuilder(Material.getMaterial(351), 1, (byte) 1)
						.setItemName("§aactiv").getItemstack());
			}
			
			inventory.setItem(30, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
					.setSkull("http://textures.minecraft.net/texture/"
							+ "d2f6c07a326def984e72f772ed645449f5ec96c6ca256499b5d2b84a8dce")
					.setItemName("§5YouTuber")
					.setLore(Arrays.asList("§7Alle Spieler mit dem YouTuberrang!")).getItemstack());
			if (getHideVIPList(player).contains("YouTuber")) {
				inventory.setItem(39, new ItemBuilder(Material.getMaterial(351), 1, (byte) 10)
						.setItemName("§aactiv").getItemstack());
			} else {
				inventory.setItem(39, new ItemBuilder(Material.getMaterial(351), 1, (byte) 1)
						.setItemName("§aactiv").getItemstack());
			}
			
			inventory.setItem(32, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
					.setSkull("http://textures.minecraft.net/texture/"
							+ "211ab3a1132c9d1ef835ea81d972ed9b5cd8ddff0a07c55a749bcfcf8df5")
					.setItemName("§6Premium")
					.setLore(Arrays.asList("§7Alle Spieler mit dem Premiumrang!")).getItemstack());
			if (getHideVIPList(player).contains("Premium")) {
				inventory.setItem(41, new ItemBuilder(Material.getMaterial(351), 1, (byte) 10)
						.setItemName("§aactiv").getItemstack());
			} else {
				inventory.setItem(41, new ItemBuilder(Material.getMaterial(351), 1, (byte) 1)
						.setItemName("§aactiv").getItemstack());
			}
			
			inventory.setItem(34, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
					.setSkull("http://textures.minecraft.net/texture/"
							+ "451b78fbe4df1d74cdf9aa495641d0aab9d5c0367c774dbb0e7facff36dff")
					.setItemName("§aFreunde")
					.setLore(Arrays.asList("§7Alle Spieler aus deiner Freundesliste!")).getItemstack());
			if (getHideVIPList(player).contains("Freunde")) {
				inventory.setItem(43, new ItemBuilder(Material.getMaterial(351), 1, (byte) 10)
						.setItemName("§aactiv").getItemstack());
			} else {
				inventory.setItem(43, new ItemBuilder(Material.getMaterial(351), 1, (byte) 1)
						.setItemName("§aactiv").getItemstack());
			}
			
			
			player.openInventory(inventory);
		} else {
			Inventory inventory = Bukkit.createInventory(player, 3*9, "§5Spieler Sichtbarkeit");
			inventory.setItem(11, new ItemBuilder(Material.getMaterial(351), 1, (byte) 10)
					.setItemName("§aAlle anzeigen").addEnchant(Enchantment.DIG_SPEED, 1).getItemstack());
			inventory.setItem(13, new ItemBuilder(Material.getMaterial(351), 1, (byte) 5)
					.setItemName("§5Nur VIPs anzeigen").getItemstack());
			inventory.setItem(15, new ItemBuilder(Material.getMaterial(351), 1, (byte) 1)
					.setItemName("§cNiemanden anzeigen").getItemstack());
			player.openInventory(inventory);
		}
	}
	
}
