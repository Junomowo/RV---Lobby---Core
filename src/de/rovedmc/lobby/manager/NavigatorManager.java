package de.rovedmc.lobby.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class NavigatorManager {

	public static File file = new File("plugins//Lobby//navigator.yml");
	public static FileConfiguration cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
	  
	  public static void save() {
	    try {
	      cfg.save(file);
	    } catch (IOException e) {
	      e.printStackTrace();
	    } 
	  }
	  
	  public static void createAndLoadFile() {
	    if (file.exists()) {
	      try {
	        cfg.load(file);
	      } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
	        e.printStackTrace();
	      } 
	    } else {
	      try {
	        file.createNewFile();
	      } catch (IOException e) {
	        e.printStackTrace();
	      } 
	      cfg.set("NavLocs", new ArrayList<String>());
	      save();
	    } 
	  }
	   
	  public static List<String> getNavLocs(){
		  return cfg.getStringList("NavLocs");
	  }
	  
	  public static void addNavLoc(String Name, Location location) {
		  List<String> list = getNavLocs();
		  list.add(Name);
		  cfg.set("NavLocs", list);
		  cfg.set("NavLoc." + Name , location);
		  save();
	  }
	  
	  public static void rmvNavLoc(String Name) {
		  cfg.set("NavLoc." + Name, null);
		  List<String> list = getNavLocs();
		  list.remove(Name);
		  cfg.set("NavLocs", list);
		  save();
	  }
	  
	  public static Location getNavLocation(String Name) {
		  return (Location) cfg.get("NavLoc." + Name);
	  }
	
}
