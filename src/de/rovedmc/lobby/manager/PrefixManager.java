package de.rovedmc.lobby.manager;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import de.dytanic.cloudnet.api.CloudAPI;

public class PrefixManager {
	
	public static String getRawPrefix(Player player) {
		String Prefix = "";
		Prefix = CloudAPI.getInstance().getOfflinePlayer(player.getName()).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool()).getPrefix();
		Prefix = ChatColor.translateAlternateColorCodes('&', Prefix.replace("●", ""));
		return Prefix;
	}

}
