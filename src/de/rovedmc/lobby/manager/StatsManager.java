package de.rovedmc.lobby.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import de.rovedmc.lobby.util.MySQL;


public class StatsManager {

	public static HashMap<Player, Integer> Streaks = new HashMap<Player, Integer>();
	
	public static void managePlayer(String UUID, String Name) {
		if (!existPlayer(UUID)) {
			MySQL.update("INSERT INTO Stats(UUID, Name, Kills, Deaths, Points) VALUES ('" + UUID + "','" + Name + "','0','0','0')");
		} else {
			if (!getNameByUUID(UUID).equals(Name)) {
				MySQL.update("UPDATE Stats SET Name='" + Name + "' WHERE UUID='" + UUID + "'");
			}
		}
	}
	
	public static List<String> getRankingUUIDList() {
		List<String> list = new ArrayList<String>();
		ResultSet rs = MySQL.query("SELECT UUID FROM Stats ORDER BY Points desc");
		int i = 1;
		try {
			while (rs.next()) {
				list.add(rs.getString("UUID"));
				i++;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return list;
	}
	
	public static int getRank(String UUID) {
		int result = 0;
		ResultSet rs = MySQL.query("SELECT UUID FROM Stats ORDER BY Points desc");
		int i = 1;
		try {
			while (rs.next()) {
				if (rs.getString("UUID").equals(UUID)) {
					result = i;
				} else {
					i++;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public static void updateStats(String What, int Amount, String UUID) {
		MySQL.update("UPDATE Stats SET " + What + "='" + Amount + "' WHERE UUID='" + UUID +"'");
	}
	
	public static int getStats(String What, String UUID) {
		return Integer.valueOf(get(What, "Stats", "UUID", UUID));
	}
	
	public static String getNameByUUID(String UUID) {
		return get("Name", "Stats", "UUID", UUID);
	}
	
	public static String getUUIDByName(String Name) {
		return get("UUID", "Stats", "Name", Name);
	}
	
	public static boolean existPlayer(String UUID) {
		ResultSet rs = MySQL.query("SELECT UUID FROM " + "Stats" + " WHERE uuid='" + UUID + "'");
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
