package de.rovedmc.lobby.manager;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import de.dytanic.cloudnet.api.CloudAPI;

public class ScoreManager {
	
	public static void setScore(Player player) {
		
		//====================================================================>
		Scoreboard score = Bukkit.getScoreboardManager().getNewScoreboard();
		Objective obj = score.registerNewObjective("aaa", "bbb");
		String UUID = player.getUniqueId().toString();
		//====================================================================>		
		
		List<String> onlineuuid = new ArrayList<String>();
		for (String uuids : FriendManager.getFriendList(player.getUniqueId().toString())) {
			if (FriendManager.get("Online", "Friend", "UUID", uuids).equals("true")) {
				onlineuuid.add(uuids);
			}
		}
		int FriendSize = FriendManager.getFriendList(player.getUniqueId().toString()).size();
		int OnlineFriendSize = onlineuuid.size();
		
		//====================================================================>
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName("§aROVEDMC.DE");
			//===========================>
			obj.getScore("§a ").setScore(20);
			obj.getScore("§7Dein Rang§8:").setScore(19);
			obj.getScore("§8» " + PrefixManager.getRawPrefix(player)).setScore(18); 
			obj.getScore("§b ").setScore(17);
			obj.getScore("§7Deine Coins§8:").setScore(16);
			obj.getScore("§8» §e" + CoinsManager.getCoins(player.getUniqueId().toString()) + " Coins").setScore(15);
			obj.getScore("§c ").setScore(14);
			obj.getScore("§7Dein Freunde§8:").setScore(13);
			obj.getScore("§8» §a" + OnlineFriendSize + "§7/§2" + FriendSize + " §7Freunde").setScore(12);
			obj.getScore("§d ").setScore(11);
			obj.getScore("§7Dein Clan§8:").setScore(10);
			obj.getScore("§8» §bKein Clan").setScore(9);
			obj.getScore("§e ").setScore(7);
			obj.getScore("§7Dein Onlinezeit§8:").setScore(6);
			obj.getScore("§8» §60 Sekunden").setScore(5);
			//===========================>
		player.setScoreboard(score);
		//====================================================================>
	}

}
