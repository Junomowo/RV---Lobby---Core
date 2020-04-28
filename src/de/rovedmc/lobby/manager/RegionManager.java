package de.rovedmc.lobby.manager;

import org.bukkit.Location;

public class RegionManager {
	
	public static boolean inRegion(Location loc, Location loc1, Location loc2) {
        Double maxX = Double.valueOf((loc1.getX() > loc2.getX()) ? loc1.getX() : loc2.getX());
        Double minX = Double.valueOf((loc1.getX() < loc2.getX()) ? loc1.getX() : loc2.getX());
 
 
        Double maxY = Double.valueOf((loc1.getY() > loc2.getY()) ? loc1.getY() : loc2.getY());
        Double minY = Double.valueOf((loc1.getY() < loc2.getY()) ? loc1.getY() : loc2.getY());
 
 
        Double maxZ = Double.valueOf((loc1.getZ() > loc2.getZ()) ? loc1.getZ() : loc2.getZ());
        Double minZ = Double.valueOf((loc1.getZ() < loc2.getZ()) ? loc1.getZ() : loc2.getZ());
 
        if (loc.getX() <= maxX.doubleValue() && loc.getX() >= minX.doubleValue() &&
                loc.getY() <= maxY.doubleValue() && loc.getY() >= minY.doubleValue()) {
            return (loc.getZ() <= maxZ.doubleValue() && loc.getZ() >= minZ.doubleValue());
        }
 
        return false;
    }

}
