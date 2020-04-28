  package de.rovedmc.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.dytanic.cloudnet.bridge.CloudServer;
import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.manager.ArchivementManager;
import de.rovedmc.lobby.manager.ChestManager;
import de.rovedmc.lobby.manager.CoinsManager;
import de.rovedmc.lobby.manager.ExtraManager;
import de.rovedmc.lobby.manager.HideManager;
import de.rovedmc.lobby.manager.InvManager;
import de.rovedmc.lobby.manager.ScoreManager;
import de.rovedmc.lobby.manager.SettingsManager;
import de.rovedmc.lobby.manager.StatsManager;

public class JoinListener implements Listener{
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		event.setJoinMessage(null);
		
		StatsManager.managePlayer(player.getUniqueId().toString(), player.getName());
		ExtraManager.managePlayer(player.getUniqueId().toString(), player.getName());
		ExtraManager.UsedGadGet.put(player, "");
		CoinsManager.managePlayer(player.getUniqueId().toString(), player.getName());
		ChestManager.managePlayer(player.getUniqueId().toString(), player.getName());
		HideManager.managePlayer(player.getUniqueId().toString(), player.getName());
		SettingsManager.managePlayer(player.getUniqueId().toString(), player.getName());
		HideManager.loadStats(player);
		if (SettingsManager.getSetting(player.getUniqueId().toString(), "AllowChat").equals("true")) {
			Lobby.AllChatter.add(player);
		}
		
		player.teleport(Lobby.Spawn);
		
		Lobby.AllPlayer.add(player);
		for (Player all : Lobby.AllPlayer) {
			ScoreManager.setScore(all);
			CloudServer.getInstance().updateNameTags(all);
		}
		
		InvManager.setLobbyInventory(player);
		HideManager.manageAllHide();
		
	}

}
