package de.rovedmc.lobby;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import de.rovedmc.lobby.command.SetupCommand;
import de.rovedmc.lobby.listener.BlockListener;
import de.rovedmc.lobby.listener.ChatListener;
import de.rovedmc.lobby.listener.DamageListener;
import de.rovedmc.lobby.listener.FoodListener;
import de.rovedmc.lobby.listener.ItemListener;
import de.rovedmc.lobby.listener.JoinListener;
import de.rovedmc.lobby.listener.MoveListener;
import de.rovedmc.lobby.listener.QuitListener;
import de.rovedmc.lobby.listener.WeatherListener;
import de.rovedmc.lobby.manager.ChestOpeningManager;
import de.rovedmc.lobby.manager.ConfigManager;
import de.rovedmc.lobby.manager.InvManager;
import de.rovedmc.lobby.manager.MapManager;
import de.rovedmc.lobby.manager.NavigatorManager;
import de.rovedmc.lobby.manager.SkullManager;
import de.rovedmc.lobby.manager.UpdateManager;
import de.rovedmc.lobby.util.Friend_MySQL;
import de.rovedmc.lobby.util.MySQL;

public class Lobby extends JavaPlugin{
	
	//===============================
	public static Lobby instance;
	//===============================
	public static String Prefix;
	public static String NoPerms;
	//===============================
	public static String HOST;
	public static String DATABASE;
	public static String USER;
	public static String PASSWORD;
	
	public static String Friend_HOST;
	public static String Friend_DATABASE;
	public static String Friend_USER;
	public static String Friend_PASSWORD;
	//===============================
	public static Location Spawn;
	public static Location LobbyEcke1;
	public static Location LobbyEcke2;
	public static Location ChestLocation;
	//===============================
	public static boolean isConfigurated;
	//===============================
	public static ArrayList<Player> AllPlayer = new ArrayList<Player>();
	public static ArrayList<Player> Builders = new ArrayList<Player>();
	
	public static ArrayList<Player> AllChatter = new ArrayList<Player>();
	//===============================
	
	@Override
	public void onEnable() {
		instance = this;
		isConfigurated = true;
		ConfigManager.createAndLoadFile();
		UpdateManager.updateCount();
		NavigatorManager.createAndLoadFile();
		ChestOpeningManager.createAndLoadFile();
		MySQL.connect();
		MySQL.createTable();
		Friend_MySQL.connect();
		if (isConfigurated) {
			Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
			Bukkit.getPluginManager().registerEvents(new QuitListener(), this);
			Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
			Bukkit.getPluginManager().registerEvents(new MoveListener(), this);
			Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
			Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
			Bukkit.getPluginManager().registerEvents(new FoodListener(), this);
			Bukkit.getPluginManager().registerEvents(new ItemListener(), this);
			Bukkit.getPluginManager().registerEvents(new WeatherListener(), this);
		}
		getCommand("setup").setExecutor(new SetupCommand());
		updateMap();
		SkullManager.registerSkulls();
		InvManager.animateHealth();
	}
	
	@Override
	public void onDisable() {
		for (Player all : Bukkit.getOnlinePlayers()) {
			all.kickPlayer(Lobby.Prefix + "§7Das System wird §cneugeladen§7!");
		}
	}
	
	public static void updateMap() {
		for (World worlds : Bukkit.getWorlds()) {
			worlds.setAnimalSpawnLimit(0);
			worlds.setMonsterSpawnLimit(0);
			worlds.setAmbientSpawnLimit(0);
			worlds.setTime(0);
		}
		new BukkitRunnable() {
			
			@Override
			public void run() {
				updateMap();
				
			}
		}.runTaskLater(instance, 1);
	}

}
