package de.rovedmc.lobby.listener;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.manager.SettingsManager;

public class ChatListener implements Listener{
	
	public static HashMap<Player, Long> ChatDelay = new HashMap<Player, Long>();
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (!ChatDelay.containsKey(player)) {
			long setTimer = 0;
			ChatDelay.put(player, setTimer);
		}
		long LastChat = ChatDelay.get(player);
		String ChatSetting = SettingsManager.getSetting(player.getUniqueId().toString(), "AllowChat");
		if (ChatSetting.equals("false")) {
			player.sendMessage(Lobby.Prefix + "§cDu hast den Chat deaktiviert!");
		}
		if (!player.hasPermission("Lobby.UnlimitedChat")) {
			if (LastChat > System.currentTimeMillis()) {
				event.setCancelled(true);
				player.sendMessage("§cBitte warte zwischen deinen Nachrichten eine kurze Zeit!");
			}
		}
		String MSG = event.getMessage();
		event.setCancelled(true);
		for (Player chatter : Lobby.AllChatter) {
			chatter.sendMessage("§a" + player.getDisplayName() + " §8: §f" + MSG); 
		}
	}

}
