package de.rovedmc.lobby.manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import de.rovedmc.lobby.util.Friend_MySQL;
import de.rovedmc.lobby.util.MySQL;

public class FriendManager { 

	public static void createPlayer(Player player) {
		String Name = player.getName();
		String UUID = player.getUniqueId().toString();
		if (!existPlayer(player.getUniqueId().toString(), "Friend")) {			
			Friend_MySQL.update("INSERT INTO `Friend`(`UUID`, `Name`, `FriendList`, `RequestList`, `AllowRequest`, `AllowJump`, `AllowMSG`, `Server`, `Online`, `LastOnline`, `Status`)" 
					+ "VALUES ('" + player.getUniqueId().toString() + "','" + player.getName() + "','','','true','true','true','Lobby','true','0','')");
		} else {
			if (!getNameByUUID(UUID).equals(player.getName())) {
				Friend_MySQL.update("UPDATE Friend SET Name='" + Name + "' WHERE UUID='" + UUID + "'");
			}
		}
	}
	
	// <_-_-_-_-_-_-_-_- < --Set|add|remove-- > -_-_-_-_-_-_-_-_>
	
	public static void setStatus(String Status, String UUID) {
		Friend_MySQL.update("UPDATE Friend SET Status='" + Status + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void setOnline(String What, String UUID) {
		Friend_MySQL.update("UPDATE Friend SET Online='" + What + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void setServer(String Server, String UUID) {
		Friend_MySQL.update("UPDATE Friend SET Server='" + Server + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void addFriend(String UUID, String UUID2) {
		String RawList = getFriendListRaw(UUID) + UUID2 + ";";
		Friend_MySQL.update("UPDATE Friend SET FriendList='" + RawList + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void rmvFriend(String UUID, String UUID2) {
		String RawList = getFriendListRaw(UUID).replace(UUID2 + ";", "");
		Friend_MySQL.update("UPDATE Friend SET FriendList='" + RawList + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void addRequest(String UUID, String UUID2) {
		String RawList = getRequestListRaw(UUID) + UUID2 + ";";
		Friend_MySQL.update("UPDATE Friend SET RequestList='" + RawList + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void rmvRequest(String UUID, String UUID2) {
		String RawList = getRequestListRaw(UUID).replace(UUID2 + ";", "");
		Friend_MySQL.update("UPDATE Friend SET RequestList='" + RawList + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void setAllowRequest(String Number, String UUID) {
		Friend_MySQL.update("UPDATE Friend SET AllowRequest='" + Number + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void setAllowJump(String Number, String UUID) {
		Friend_MySQL.update("UPDATE Friend SET AllowJump='" + Number + "' WHERE UUID='" + UUID + "'"); 
	}
	
	public static void setAllowMSG(String Number, String UUID) {
		Friend_MySQL.update("UPDATE Friend SET AllowMSG='" + Number + "' WHERE UUID='" + UUID + "'"); 
	}
	
	
	// <_-_-_-_-_-_-_-_- < --Get-- > -_-_-_-_-_-_-_-_>
	
	public static String getAllowJump(String UUID) {
		return get("AllowJump", "Friend", "UUID", UUID);
	}
	
	public static String getAllowMSG(String UUID) {
		return get("AllowMSG", "Friend", "UUID", UUID);
	}
	
	public static String getAllowRequest(String UUID) {
		return get("AllowRequest", "Friend", "UUID", UUID);
	}
	
	public static String getNameByUUID(String UUID) {
		return get("Name", "Friend", "UUID", UUID);
	}
	
	public static String getUUIDByName(String Name) {
		return get("UUID", "Friend", "Name", Name);
	}
	
	public static String getFriendListRaw(String UUID) {
		return get("FriendList", "Friend", "UUID", UUID);
	}
	
	public static String getRequestListRaw(String UUID) {
		return get("RequestList", "Friend", "UUID", UUID);
	}
	

	
	public static List<String> getFriendList(String UUID){
		String RawList = getFriendListRaw(UUID);
		List<String> toreturn = new ArrayList<String>();
		if(RawList.isEmpty())return toreturn;
		for(String listitem : RawList.split(";")) {
			toreturn.add(listitem);
		}
		return toreturn;
	}
	
	public static List<String> getRequestList(String UUID){
		String RawList = getRequestListRaw(UUID);
		List<String> toreturn = new ArrayList<String>();
		if(RawList.isEmpty())return toreturn;
		for(String listitem : RawList.split(";")) {
			toreturn.add(listitem);
		}
		return toreturn;
	}
	
	public static boolean existPlayer(String UUID, String Table) {
		ResultSet rs = Friend_MySQL.query("SELECT UUID FROM " + Table + " WHERE UUID='" + UUID + "'");
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
		ResultSet rs = Friend_MySQL
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
