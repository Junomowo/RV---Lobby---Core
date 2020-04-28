package de.rovedmc.lobby.manager;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.rovedmc.lobby.Lobby;

public class ConfigManager {
	
	public static File file = new File("plugins//Lobby//config.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
	
	public static void save() {
		try {
			cfg.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createAndLoadFile() {
		if (file.exists()) {
			try {
				cfg.load(file);
			} catch (IOException | InvalidConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cfg.set("Prefix", "&8[&cGunGame&8] ");
			cfg.set("NoPerms", "%Prefix%&cDafürt hast du leider keine Berechtigung!");
			cfg.set("MySQL.HOST", "localhost");
			cfg.set("MySQL.DATABASE", "133");
			cfg.set("MySQL.USER", "123");
			cfg.set("MySQL.PASSWORD", "123");
			
			cfg.set("Friend_MySQL.Friend_HOST", "localhost");
			cfg.set("Friend_MySQL.Friend_HDATABASE", "133");
			cfg.set("Friend_MySQL.Friend_HUSER", "123");
			cfg.set("Friend_MySQL.Friend_HPASSWORD", "123");
			
			cfg.set("AllowCloudNetPrefix", "false");
			save();
		}
	}
	
	public static void loadStates() {
		Lobby.Prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefix"));
		Lobby.NoPerms = ChatColor.translateAlternateColorCodes('&', 
				cfg.getString("NoPerms").replace("%Prefix%", Lobby.Prefix));
		Lobby.HOST = cfg.getString("MySQL.HOST");
		Lobby.DATABASE = cfg.getString("MySQL.DATABASE");
		Lobby.USER = cfg.getString("MySQL.USER");
		Lobby.PASSWORD = cfg.getString("MySQL.PASSWORD");
		
		Lobby.Friend_HOST = cfg.getString("Friend_MySQL.Friend_HOST");
		Lobby.Friend_DATABASE = cfg.getString("Friend_MySQL.Friend_DATABASE");
		Lobby.Friend_USER = cfg.getString("Friend_MySQL.Friend_USER");
		Lobby.Friend_PASSWORD = cfg.getString("Friend_MySQL.Friend_PASSWORD");
	}

}
