package de.rovedmc.lobby.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.util.MySQL;


public class ArchivementManager {

	public static void managePlayer(String UUID, String Name) {
		if (!existPlayer(UUID)) {
			MySQL.update("INSERT INTO Archivements(UUID, Name, Archivements) VALUES ('" + UUID + "','" + Name + "','')");
		} else {
			if (!getNameByUUID(UUID).equals(Name)) {
				MySQL.update("UPDATE Archivements SET Name='" + Name + "' WHERE UUID='" + UUID + "'");
			}
		}
	}
	
	public static String getListRaw(String UUID) {
		return get("Archivements", "Archivements", "UUID", UUID);
	}
	
	public static List<String> getList(String UUID){
		String ListRaw = getListRaw(UUID);
		List<String> list = new ArrayList<String>();
		if (ListRaw.isEmpty()) return list;
		for (String split : ListRaw.split(";")) {
			list.add(split);
		}
		return list;
	}
	
	public static void addArchivement(Player player, String Title, String Desc, String SQLTitle) {
		String UUID = player.getUniqueId().toString();
		if (!getList(UUID).contains(SQLTitle)) {
			player.sendMessage("");
			player.sendMessage("§8===========================================");
			player.sendMessage("");
			player.sendMessage(Lobby.Prefix + "§7Du hast ein neues §aArchivement §7erhalten §8[§a+50 Punkte§8]:");
			player.sendMessage("");
			player.sendMessage(" §8- §7Bezeichnung§8: §a" + Title);
			player.sendMessage(" §8- §7Beschreibung§8: §a" + Desc);
			player.sendMessage(" §8- §7Belohnung§8: §a50 Punkte");
			player.sendMessage("");
			player.sendMessage("§8===========================================");
			player.sendMessage("");
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 3, 3);
			StatsManager.updateStats("Points", StatsManager.getStats("Points", UUID) +50, UUID);
			String ListRaw = getListRaw(UUID) + SQLTitle + ";";
			MySQL.update("UPDATE Archivements SET Archivements='" + ListRaw + "' WHERE UUID='" + UUID + "'");
		}
	}
	
	public static String getNameByUUID(String UUID) {
		return get("Name", "Archivements", "UUID", UUID);
	}
	
	public static String getUUIDByName(String Name) {
		return get("UUID", "Archivements", "Name", Name);
	}
	
	public static boolean existPlayer(String UUID) {
		ResultSet rs = MySQL.query("SELECT UUID FROM " + "Archivements" + " WHERE uuid='" + UUID + "'");
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
	
}
