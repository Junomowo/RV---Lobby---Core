package de.rovedmc.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.bridge.CloudServer;
import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.manager.HideManager;
import de.rovedmc.lobby.manager.ScoreManager;

public class QuitListener implements Listener{
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();

		event.setQuitMessage(null);
		HideManager.saveStats(player);
		
		Lobby.AllPlayer.remove(player);
		for (Player all : Lobby.AllPlayer) {
			ScoreManager.setScore(all);
			CloudServer.getInstance().updateNameTags(all);
		}
	
	}

}
