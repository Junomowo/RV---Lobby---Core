package de.rovedmc.lobby.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.bukkit.Bukkit;

import de.rovedmc.lobby.Lobby;


 
public class MySQL {
        private static String HOST = Lobby.HOST;
        private static String DATABASE = Lobby.DATABASE;
        private static String USER = Lobby.USER;
        private static String PASSWORD = Lobby.PASSWORD;
       
        private static Connection con;
       
        @SuppressWarnings("static-access")
		public MySQL(String host, String database, String user, String password) {
                this.HOST = host;
                this.DATABASE = database;
                this.USER = user;
                this.PASSWORD = password;
               
                connect();
        }
 
        public static void connect() {
                try {
                        con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
                        Bukkit.getConsoleSender().sendMessage(Lobby.Prefix + "§aMySQL wurde verbunden..."); 
                } catch (SQLException e) {
                        System.out.println("[MySQL] Die Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
                }
        }
       
        public static void close() {
                try {
                        if(con != null) {
                                con.close();
                                System.out.println("[MySQL] Die Verbindung zur MySQL wurde Erfolgreich beendet!");
                        }
                } catch (SQLException e) {
                        System.out.println("[MySQL] Fehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
                }
        }
       
        public static void update(String qry) {
                try {
                        Statement st = con.createStatement();
                        st.executeUpdate(qry);
                        st.close();
                } catch (SQLException e) {
                        connect();
                        System.err.println(e);
                }
        }
       
        public static ResultSet query(String qry) {
                ResultSet rs = null;
               
                try {
                        Statement st = con.createStatement();
                        rs = st.executeQuery(qry);
                } catch (SQLException e) {
                        connect();
                        System.err.println(e);
                }
                return rs;
        }
        
        public static void createTable() {
    		MySQL.update("CREATE TABLE IF NOT EXISTS Stats ("
    				+ "UUID TEXT, "
    				+ "Name TEXT, "
    				+ "Kills INT(20), "
    				+ "Deaths INT(20), "
    				+ "Points INT(20))");
    		MySQL.update("CREATE TABLE IF NOT EXISTS Coins ("
    				+ "UUID TEXT, "
    				+ "Name TEXT, "
    				+ "Coins BIGINT)");
    		MySQL.update("CREATE TABLE IF NOT EXISTS Tickets ("
    				+ "UUID TEXT, "
    				+ "Name TEXT, "
    				+ "Tickets BIGINT)");
    		MySQL.update("CREATE TABLE IF NOT EXISTS Chests ("
    				+ "UUID TEXT, "
    				+ "Name TEXT, "
    				+ "Chests BIGINT)");
    		MySQL.update("CREATE TABLE IF NOT EXISTS Extras ("
    				+ "UUID TEXT, "
    				+ "Name TEXT, "
    				+ "Heads TEXT, "
    				+ "Boots TEXT,"
    				+ "GadGets TEXT, "
    				+ "Effects TEXT)");
    		MySQL.update("CREATE TABLE IF NOT EXISTS Hide ("
    				+ "UUID TEXT, "
    				+ "Name TEXT, "
    				+ "Hide TEXT, "
    				+ "HideVIP TEXT)");
    		MySQL.update("CREATE TABLE IF NOT EXISTS Setting ("
    				+ "UUID TEXT, "
    				+ "Name TEXT, "
    				+ "AllowChat TEXT, "
    				+ "GlassItem INT(42))");
    		
    	}
}