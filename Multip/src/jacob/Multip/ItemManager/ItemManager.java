package jacob.Multip.ItemManager;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {
    //honeycomb
    public static ItemStack FlowerBoots;
    public static ItemStack CloudBottle;
    public static ItemStack HoneyComb;
    public static ItemStack MiningHelmet;
    public static ItemStack SporeSac;

    public static void init() {
        createFlowerBoots();
        createCloudBottle();
        createHoneyComb();
        createMiningHelmet();
        createSporeSac();
    }


    private static void createFlowerBoots() {
        ItemStack item = new ItemStack(Material.LEATHER_BOOTS, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        meta.setColor(Color.GREEN);
        meta.setDisplayName("§2Flower Boots");
        List<String> lore = new ArrayList<>();
        lore.add("§7Nature's best friend!");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_DYE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        FlowerBoots = item;
    }

    public static void createCloudBottle() {
        ItemStack item = new ItemStack(Material.GLASS_BOTTLE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Cloud in a Bottle");
        List<String> lore = new ArrayList<>();
        lore.add("§7Double Jump!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        CloudBottle = item;
    }

    public static void createHoneyComb() {
        ItemStack item = new ItemStack(Material.HONEYCOMB, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§eHoneyComb");
        List<String> lore = new ArrayList<>();
        lore.add("§fReleases bees when damaged");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        HoneyComb = item;
    }

    public static void createMiningHelmet() {
        ItemStack item = new ItemStack(Material.GOLDEN_HELMET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§6Mining Helmet");
        List<String> lore = new ArrayList<>();
        lore.add("§fProvides light for mining");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        MiningHelmet = item;
    }

    public static void createSporeSac() {
        ItemStack item = new ItemStack(Material.SLIME_BALL, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aSpore Sac");
        List<String> lore = new ArrayList<>();
        lore.add("§fSummons spores over time");
        lore.add("§fthat will damage enemies");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        SporeSac = item;
    }



}
