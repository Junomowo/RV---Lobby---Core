package de.rovedmc.lobby.manager;

import java.sql.ResultSet;
import java.sql.SQLException;

import de.rovedmc.lobby.util.MySQL;

public class CoinsManager {

	public static void managePlayer(String UUID, String Name) {
		if (!existPlayer(UUID)) {
			MySQL.update("INSERT INTO Coins(UUID, Name, Coins) VALUES ('" + UUID + "','" + Name + "','0')");
		} else {
			if (!getNameByUUID(UUID).equals(Name)) {
				MySQL.update("UPDATE Coins SET Name='" + Name + "' WHERE UUID='" + UUID + "'");
			}
		}
	}
	
	public static void updateCoins(String UUID, int Amount) {
		MySQL.update("UPDATE Coins SET Coins='" + Amount + "' WHERE UUID='" + UUID + "'");
	}
	
	public static int getCoins(String UUID) {
		return Integer.valueOf(get("Coins", "Coins", "UUID", UUID));
	}
	
	public static String getNameByUUID(String UUID) {
		return get("Name", "Coins", "UUID", UUID);
	}
	
	public static String getUUIDByName(String Name) {
		return get("UUID", "StatCoinss", "Name", Name);
	}
	
	public static boolean existPlayer(String UUID) {
		ResultSet rs = MySQL.query("SELECT UUID FROM " + "Coins" + " WHERE uuid='" + UUID + "'");
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
