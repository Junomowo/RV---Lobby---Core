package de.rovedmc.lobby.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import de.rovedmc.lobby.util.ItemBuilder;

public class ProfilManager {

	public static void openFriendMenü(Player player, int Page) {	
		String UUID = player.getUniqueId().toString();
		Inventory inventory = Bukkit.createInventory(player, 6*9, "§aFreunde");
		
		List<String> OnlineUUIDs = new ArrayList<String>();
		List<String> OfflineUUIDs = new ArrayList<String>();
		
		for (String uuids : FriendManager.getFriendList(UUID)) {
			if (FriendManager.get("Online", "Friend", "UUID", uuids).equals("true")) {
				OnlineUUIDs.add(uuids);
			} else {
				OfflineUUIDs.add(uuids);
			}
		}
		
		if (FriendManager.getFriendList(UUID).size() == 0) {
			inventory.setItem(0, new ItemBuilder(Material.SKULL_ITEM, 0, (byte) 3).setItemName("§cKeine Freunde vorhanden!").getItemstack());
		}
		
		for (String uuids : OnlineUUIDs) {
			String name = FriendManager.getNameByUUID(uuids);
			String Server = FriendManager.get("Server", "Friend", "UUID", uuids);
			inventory.addItem(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(name)
					.setItemName("§7" + name + " §8[§aOnline§8]")
					.setLore(Arrays.asList("§7Onlie auf §a" + Server)).getItemstack());
		}
		
		for (String uuids : OfflineUUIDs) {
			String name = FriendManager.getNameByUUID(uuids);
			long lastonline = Long.valueOf(FriendManager.get("LastOnline", "Friend", "UUID", uuids));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("§7am §add.MM.yyyy §7um §aHH:mm Uhr");
			simpleDateFormat.format(lastonline);
			inventory.addItem(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(name).setItemName("§7" + name + " §8[§cOffline§8]")
					.setLore(Arrays.asList("§7Zuletzt online " + simpleDateFormat + "§7!")).getItemstack());
		}
		
		int GlassSubID = Integer.valueOf(SettingsManager.getSetting(player.getUniqueId().toString(), "GlassItem"));
		
		inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		
		inventory.setItem(45, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkull("http://textures.minecraft.net/texture/"
				+ "451b78fbe4df1d74cdf9aa495641d0aab9d5c0367c774dbb0e7facff36dff")
				.addEnchant(Enchantment.DIG_SPEED, 1).setItemName("§aFreunde").getItemstack());
		inventory.setItem(49, new ItemBuilder(Material.REDSTONE_COMPARATOR).setItemName("§9Einstellungen").getItemstack());
		
		player.openInventory(inventory);
		
	}
	
	public static void openSettingMenü(Player player) {
		
		int GlassSubID = Integer.valueOf(SettingsManager.getSetting(player.getUniqueId().toString(), "GlassItem"));
		String UUID = player.getUniqueId().toString();
		Inventory inventory = Bukkit.createInventory(player, 6*9, "§cEinstellungen");
		
		inventory.setItem(10, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName("§aGlasplattten").getItemstack());
		inventory.setItem(19, new ItemBuilder(Material.REDSTONE).setItemName("§aAuf Default setzen").getItemstack());
		
		inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		
		inventory.setItem(45, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkull("http://textures.minecraft.net/texture/"
				+ "451b78fbe4df1d74cdf9aa495641d0aab9d5c0367c774dbb0e7facff36dff").setItemName("§aFreunde").getItemstack());
		inventory.setItem(49, new ItemBuilder(Material.REDSTONE_COMPARATOR).setItemName("§9Einstellungen")
				.addEnchant(Enchantment.DIG_SPEED, 1).getItemstack());
		
		player.openInventory(inventory);
	}
	
	public static void openSettingGlassPlate(Player player) {
		int GlassSubID = Integer.valueOf(SettingsManager.getSetting(player.getUniqueId().toString(), "GlassItem"));
		String UUID = player.getUniqueId().toString();
		Inventory inventory = Bukkit.createInventory(player, 6*9, "§cEinstellungen §aGlass");
		
		int count = 9; 
		for (int i = 0; i < 15; i++) {
			inventory.setItem(count, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) i).setItemName("§aGlass").getItemstack());
			count++;
		}
		
		inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(37, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(38, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(39, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(40, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(41, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(42, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(43, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) GlassSubID).setItemName(" ").getItemstack());
		
		inventory.setItem(45, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkull("http://textures.minecraft.net/texture/"
				+ "451b78fbe4df1d74cdf9aa495641d0aab9d5c0367c774dbb0e7facff36dff").setItemName("§aFreunde").getItemstack());
		inventory.setItem(49, new ItemBuilder(Material.REDSTONE_COMPARATOR).setItemName("§9Einstellungen")
				.addEnchant(Enchantment.DIG_SPEED, 1).getItemstack());
		
		player.openInventory(inventory);
	}
	
}
