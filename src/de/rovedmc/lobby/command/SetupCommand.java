package de.rovedmc.lobby.command;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.rovedmc.lobby.Lobby;
import de.rovedmc.lobby.manager.MapManager;
import de.rovedmc.lobby.manager.NavigatorManager;

public class SetupCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		Player player = (Player) sender;
		if (!player.hasPermission("Lobby.Setup")) {
			player.sendMessage(Lobby.NoPerms);
			return true;
		}
		if (args.length == 0) {
			sendHelp(player); return true;
		} else if (args[0].equalsIgnoreCase("setSpawn")) {
			if (args.length == 1) {
				MapManager.createLocation("Spawn", player.getLocation());
				player.sendMessage(Lobby.Prefix + "§7Du hast den §aSpawn §7gesetzt!");
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 3, 3);
			} else {
				sendHelp(player); return true;
			}
		} else if (args[0].equalsIgnoreCase("setEcke")) {
			if (args.length == 2) {
				String What = "";
				 if (args[1].equalsIgnoreCase("1")) {
					 What = "1";
				 } else if (args[1].equalsIgnoreCase("2")) {
					 What = "2";
				 } else {
					 sendHelp(player);
				 }
				 
				 MapManager.createLocation("LobbyEcke" + What, player.getLocation());
				 MapManager.save();
				 player.sendMessage(Lobby.Prefix + "§7Du hast den §e" + What + ". §aEckpunkt §7gesetzt!");
				 
			} else {
				sendHelp(player); return true;
			}
		} else if (args[0].equalsIgnoreCase("setChest")) {
			if (args.length == 1) {
				Block block = player.getTargetBlock((Set<Material>) null, 5);
				if (block.getType() != Material.CHEST) {
					player.sendMessage(Lobby.Prefix + "§cDu musst bei diesem Setup eine §4Kiste §canschauen!");
					return true;
				}
			
				MapManager.createLocation("ChestLocation", block.getLocation());
				MapManager.save();
				player.sendMessage(Lobby.Prefix + "§7Du hast das §5ChestOpening §7gesetzt!");
				
			} else {
				sendHelp(player); return true;
			}
		} else if (args[0].equalsIgnoreCase("setNavLoc")) {
			if (args.length == 2) {
				String Name = args[1];
				if (Name.length() < 1 || Name.length() > 16) {
					player.sendMessage(Lobby.Prefix + "§cDer §4Name §cmuss zwischen §e1 §cund §e16 §cZeichen lang sein!");
					return true;
				}
				if (NavigatorManager.getNavLocs().contains(Name)) {
					NavigatorManager.cfg.set("NavLoc." + Name, player.getLocation());
					NavigatorManager.save();
					player.sendMessage(Lobby.Prefix + "§7Du hast eine neue Location für §a" + Name + " §7festgelegt!");
				} else {
					NavigatorManager.addNavLoc(Name, player.getLocation());
					player.sendMessage(Lobby.Prefix + "§7Du hast einen neuen Warp names §a" + Name + " §7erstellt!");
				}
				
			} else {
				sendHelp(player); return true;
			}
		}
		return false;
	}
	
	public static void sendHelp(Player player) {
		player.sendMessage("");
		player.sendMessage(Lobby.Prefix + "§7help §8(§cSetup§8):");
		player.sendMessage("");
		player.sendMessage("§c/setup setSpawn §7Setze den Spawn");
		player.sendMessage("§c/setup setEcke <1|2> §7Setze die Eckpunkte");
		player.sendMessage("§c/setup setChest §7Setze das ChestOpening");
		player.sendMessage("§c/setup setNavLoc <Name> §7Setze das ChestOpening");
		player.sendMessage("");
	}

}
