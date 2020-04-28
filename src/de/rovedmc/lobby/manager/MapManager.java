package de.rovedmc.lobby.manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.generator.SkyLandsChunkGenerator;

import de.rovedmc.lobby.Lobby;

public class MapManager {
  public static File file = new File("plugins//Lobby","map.yml");
  
  public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
  
  public static void save() {
    try {
      cfg.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void createLocation(String locationName, Location location) {
	  cfg.set(locationName + ".X", location.getX());
	  cfg.set(locationName + ".Y", location.getY());
	  cfg.set(locationName + ".Z", location.getZ());
	  cfg.set(locationName + ".Yaw", location.getYaw());
	  cfg.set(locationName + ".Pitch", location.getPitch());
	  cfg.set(locationName + ".World", location.getWorld().getName());
	  save();
  }
  
  public static Location getLocation(String locationName) {
	  int x = cfg.getInt(locationName + ".X");
	  int y = cfg.getInt(locationName + ".Y");
	  int z = cfg.getInt(locationName + ".Z");
	  float yaw = (float) cfg.getDouble(locationName + ".Yaw");
	  float pitch = (float) cfg.getDouble(locationName + ".Pitch");
	  String world = cfg.getString(locationName + ".World");
	  return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
  }
  
  public static void setSpawn(Location location) {
    cfg.set("Spawn", location);
    save();
  }
  
  public static void setTop3(Location location) {
	    cfg.set("Top3", location);
	    save();
	  }
  
  public static void setLocation(Location location, int Postion) {
    cfg.set("Locations." + Postion, location);
    save();
  }
  
  public static void addNavLoc(String Name, Location location) {
	  
  }
  
  public static void loadStates() {
    if (getLocation("Spawn") != null) {
      Lobby.Spawn = getLocation("Spawn");
    } else {
      Lobby.isConfigurated = false;
    } 
    if (getLocation("LobbyEcke1") != null) {
    	Lobby.LobbyEcke1 = getLocation("LobbyEcke1");
    } else {
    	Lobby.isConfigurated = false;
    } 
    if (getLocation("LobbyEcke2") != null) {
    	Lobby.LobbyEcke2 = getLocation("LobbyEcke2");
    } else {
    	Lobby.isConfigurated = false;
    } 
    if (getLocation("ChestLocation") != null) {
    	Lobby.ChestLocation = getLocation("ChestLocation");
    } else {
    	Lobby.isConfigurated = false;
    } 
  }
}