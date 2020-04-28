package de.rovedmc.lobby.manager;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;

import de.rovedmc.lobby.util.ItemBuilder;
import net.minecraft.server.v1_8_R3.ItemStack;

public class SkullManager {
	
	public static HashMap<String, ItemStack> RegisterSkulls = new HashMap<String, ItemStack>();
	
	public static void registerSkulls() {
		RegisterSkulls.put("Team", CraftItemStack.asNMSCopy(new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3)
				.setSkullOwner("PaperPlugins").setItemName("§4Team")
				.setLore(Arrays.asList("§7Alle Spieler aus dem Team!")).getItemstack()));
	}

}
