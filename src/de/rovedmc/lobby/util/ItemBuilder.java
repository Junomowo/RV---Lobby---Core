package de.rovedmc.lobby.util;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

public class ItemBuilder {
	
	/**
	 * Builder by FortyYT
	 *   Date -> 06.11.2019
	 *   Version -> Alpha(1.0)
	 */
	
	ItemStack itemstack;
	
	//Create Item (Material)
	public  ItemBuilder(Material material) {
		this(material, 1);
	}
	
	public ItemBuilder setLeatherArmorColor(Color color){
	     try{
	       LeatherArmorMeta im = (LeatherArmorMeta)itemstack.getItemMeta();
	       im.setColor(color);
	       itemstack.setItemMeta(im);
	     }catch(ClassCastException expected){}
	     return this;
	   }

	//Create Item (Material + Amount)
	public ItemBuilder(Material material, int amount) {
		itemstack = new ItemStack(material, amount);
	}
	
	//Create Item (Material + Amount + SubID)
	public ItemBuilder(Material material, int amount, byte SubID) {
		itemstack = new ItemStack(material, amount, SubID);
	}
	
	//Set Durability)
	public ItemBuilder setDurability(byte Durability) {
		itemstack.setDurability(Durability);
		return this;
	}
	
	//Set ItemName
	public ItemBuilder setItemName(String Name) {
		ItemMeta meta = itemstack .getItemMeta();
		meta.setDisplayName(Name);
		
		itemstack.setItemMeta(meta);
		return this;
	}
	
	//Add Enchant
	public ItemBuilder addEnchant(Enchantment enchant, int level) {
		ItemMeta meta = itemstack.getItemMeta();
		meta.addEnchant(enchant, level, true);
		itemstack.setItemMeta(meta);
		return this;
	}
	
	//Remove Enchant
	public ItemBuilder removeEnchant(Enchantment enchant, int level) {
		ItemMeta meta = itemstack.getItemMeta();
		meta.removeEnchant(enchant);
		itemstack.setItemMeta(meta);
		return this;
	}
	
	//add Enchant <Map>
	public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments){
	    itemstack.addEnchantments(enchantments);
	    return this;
	}
	
	//setLore <asList>
	public ItemBuilder setLore(String[] lore) {
		ItemMeta meta = itemstack.getItemMeta();
		meta.setLore(Arrays.asList(lore));
		itemstack.setItemMeta(meta);
		return this;
	}
	
	public ItemBuilder setSkullOwner(String Name) {
		SkullMeta meta = (SkullMeta) itemstack.getItemMeta();
		meta.setOwner(Name);
		itemstack.setItemMeta(meta);
		return this;
	}
	
    public ItemBuilder setSkull(String url) {
        if(url.isEmpty())return this;
     
     
        SkullMeta headMeta = (SkullMeta) itemstack.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
        itemstack.setItemMeta(headMeta);
        return this;
    }
	
	//setLore <List<String>>
	public ItemBuilder setLore(List<String> lore) {
		ItemMeta meta = itemstack.getItemMeta();
		meta.setLore(lore);
		itemstack.setItemMeta(meta);
		return this;
	}
	
	public ItemStack getItemstack() {
		ItemMeta meta = itemstack.getItemMeta();
		meta.spigot().setUnbreakable(true);
		itemstack.setItemMeta(meta);
		return itemstack;
	}

}
