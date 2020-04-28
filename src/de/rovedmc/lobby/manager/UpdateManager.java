package de.rovedmc.lobby.manager;

import org.bukkit.scheduler.BukkitRunnable;

import de.rovedmc.lobby.Lobby;

public class UpdateManager {

	public static void updateCount() {
		ConfigManager.loadStates();
		MapManager.loadStates();
		new BukkitRunnable() {
			
			@Override
			public void run() {
				updateCount();
				
			}
		}.runTaskLater(Lobby.instance, 20*60*60);
	}
	
}
