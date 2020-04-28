package de.rovedmc.lobby.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.rovedmc.lobby.util.ItemBuilder;
import de.rovedmc.lobby.util.MySQL;

public class ExtraManager {

	public static HashMap<Player, String> UsedGadGet = new HashMap<Player, String>();
	public static HashMap<Player, String> SellItem = new HashMap<Player, String>();
	
	public static void managePlayer(String UUID, String Name) {
		if (!existPlayer(UUID)) {
			MySQL.update("INSERT INTO Extras(UUID, Name, Heads, Boots, GadGets, Effects) VALUES "
					+ "('" + UUID + "','" + Name + "','','','','')");
		} else {
			if (!getNameByUUID(UUID).equals(Name)) {
				MySQL.update("UPDATE Extras SET Name='" + Name + "' WHERE UUID='" + UUID + "'");
			}
		}
	}
	
	public static void addExtra(String UUID, String What, String Extra) {
		String listRaw = getExtraListRaw(UUID, What) + Extra + ";";
		MySQL.update("UPDATE Extras SET " + What + "='" + listRaw + "' WHERE UUID='" + UUID + "'");
	}
	
	public static void rmvExtra(String UUID, String What, String Extra) {
		String listRaw = getExtraListRaw(UUID, What).replace(Extra + ";", "");
		MySQL.update("UPDATE Extras SET " + What + "='" + listRaw + "' WHERE UUID='" + UUID + "'");
	}
	
	public static String getExtraListRaw(String UUID, String What) {
		return get(What, "Extras", "UUID", UUID);
	}
	
	public static List<String> getExtraList(String UUID, String What){
		String ListRaw = getExtraListRaw(UUID, What);
		List<String> list = new ArrayList<String>();
		if (ListRaw.isEmpty()) return list;
		for (String split : ListRaw.split(";")) {
			list.add(split);
		}
		return list;
	}
	
	public static String getNameByUUID(String UUID) {
		return get("Name", "Extras", "UUID", UUID);
	}
	
	public static String getUUIDByName(String Name) {
		return get("UUID", "Extras", "Name", Name);
	}
	
	public static boolean existPlayer(String UUID) {
		ResultSet rs = MySQL.query("SELECT UUID FROM " + "Extras" + " WHERE uuid='" + UUID + "'");
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
	
	public static void openExtraMenuInventory(Player player) {
		Inventory inventory = Bukkit.createInventory(player, 5*9, "§bExtras §7Menü");
		
		int GlassSubID = Integer.valueOf(SettingsManager.getSetting(player.getUniqueId().toString(), "GlassItem"));
		int Coins = CoinsManager.getCoins(player.getUniqueId().toString());
		
		inventory.setItem(22, new ItemBuilder(Material.GOLD_INGOT).setItemName("§eDeine Coins")
				.setLore(Arrays.asList("§7Du hast §e" + Coins + " Coins§7!")).getItemstack());
		
		inventory.setItem(12, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(13, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(14, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(21, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(23, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(30, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(31, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(32, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		
		inventory.setItem(4, new ItemBuilder(Material.SKULL_ITEM).setItemName("§aKöpfe").getItemstack());
		inventory.setItem(20, new ItemBuilder(Material.STRING).setItemName("§cEffekte").getItemstack());
		inventory.setItem(24, new ItemBuilder(Material.LEATHER_BOOTS).setLeatherArmorColor(Color.AQUA).setItemName("§9Boots").getItemstack());
		inventory.setItem(40, new ItemBuilder(Material.FISHING_ROD).setItemName("§bGadGets").getItemstack());
		
		player.openInventory(inventory);
		
	}
	
	public static void openExtraHeads(Player player) {
		
		int GlassSubID = Integer.valueOf(SettingsManager.getSetting(player.getUniqueId().toString(), "GlassItem"));
		Inventory inventory = Bukkit.createInventory(player, 6*9, "§bExtras §aKöpfe");
		List<String> heads = ExtraManager.getExtraList(player.getUniqueId().toString(), "Heads");
		
		inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		
		inventory.setItem(49, new ItemBuilder(Material.BARRIER).setItemName("§4Extra entfernen").getItemstack());
		
		if (heads.size() == 0) {
			inventory.setItem(0, new ItemBuilder(Material.SKULL_ITEM, 0).setItemName("§cKeine Köpfe vorhanden!").getItemstack());
		} else {
			for (int i = 0; i < heads.size(); i++) {
				inventory.setItem(i, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setItemName("§5" + heads.get(i)).setSkullOwner(heads.get(i)).getItemstack());
			}
		}
		player.openInventory(inventory);
	}
	
	public static void openSellShop(Player player, ItemStack itemStack) {
		
		List<String> selllist = new ArrayList<String>();
		for (String split : SellItem.get(player).split(";")) {
			selllist.add(split);
		}
		String ExtraKategorie = selllist.get(0);
		String ExtraName = selllist.get(1);
		int Coins = Integer.valueOf(selllist.get(2));
		Inventory inventory = Bukkit.createInventory(player, 3*9, "§aItemverkauf §a" + ExtraKategorie);
		inventory.setItem(13, itemStack);
		inventory.setItem(11, new ItemBuilder(Material.REDSTONE).setItemName("§aItem benutzen").getItemstack());
		inventory.setItem(15, new ItemBuilder(Material.BARRIER).setItemName("§cItem verkaufen")
				.setLore(Arrays.asList("§7Verlaufswert§8: §e" + Coins + " Coins")).getItemstack());
		player.openInventory(inventory);
	}
	
	public static void openExtraBoots(Player player) {
		int GlassSubID = Integer.valueOf(SettingsManager.getSetting(player.getUniqueId().toString(), "GlassItem"));
		Inventory inventory = Bukkit.createInventory(player, 6*9, "§bExtras §aBoots");
		List<String> boots = ExtraManager.getExtraList(player.getUniqueId().toString(), "Boots");
		
		if (boots.size() == 0) {
			inventory.setItem(0, new ItemBuilder(Material.LEATHER_BOOTS, 0).setLeatherArmorColor(Color.BLUE)
					.setItemName("§cKeine Boots vorhanden").getItemstack());
		}
		
		setBoots(0, inventory, player, "Gelbe", Color.YELLOW, "§eGelbe Schuhe");
		setBoots(1, inventory, player, "Aqua", Color.AQUA, "§bAqua Schuhe");
		
		inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
	}
	
	public static void setBoots(int Slot, Inventory inventory, Player player, String What, Color color, String Name) {
		List<String> boots = ExtraManager.getExtraList(player.getUniqueId().toString(), "Boots");
		if (boots.contains(What)) {
			inventory.setItem(Slot, new ItemBuilder(Material.LEATHER_BOOTS).setItemName(Name).setLeatherArmorColor(color).getItemstack());
		}
	}
	
}
