package de.rovedmc.lobby.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.manager.RegionManager;

public class MoveListener implements Listener{
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if (!RegionManager.inRegion(player.getLocation(), Lobby.LobbyEcke1, Lobby.LobbyEcke2)) {
			player.teleport(Lobby.Spawn);
			player.sendMessage(Lobby.Prefix + "ßcDu darfst dich nicht auﬂerhalb der Lobby aufhalten!");
		}
		
	}

}
