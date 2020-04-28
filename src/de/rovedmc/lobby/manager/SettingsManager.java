package de.rovedmc.lobby.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import de.rovedmc.lobby.util.MySQL;

public class SettingsManager {
	
	public static void managePlayer(String UUID, String Name) {
		if (!existPlayer(UUID)) {
			MySQL.update("INSERT INTO Setting(UUID, Name, AllowChat, GlassItem) VALUES ('" + UUID + "','" + Name + "','true','7')");
		} else {
			if (!getNameByUUID(UUID).equals(Name)) {
				MySQL.update("UPDATE Setting SET Name='" + Name + "' WHERE UUID='" + UUID + "'");
			}
		}
	}
	
	public static void setSettings(String UUID,  String What, String Setting) {
		MySQL.update("UPDATE Setting SET " + What + "='" + Setting + "' WHERE UUID='" + UUID + "'");
	}
	
	public static String getSetting(String UUID, String What) {
		return get(What, "Setting", "UUID", UUID);
	}
	
	public static String getNameByUUID(String UUID) {
		return get("Name", "Setting", "UUID", UUID);
	}
	
	public static String getUUIDByName(String Name) {
		return get("UUID", "Setting", "Name", Name);
	}
	
	public static boolean existPlayer(String UUID) {
		ResultSet rs = MySQL.query("SELECT UUID FROM " + "Setting" + " WHERE uuid='" + UUID + "'");
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
