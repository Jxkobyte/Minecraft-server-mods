package jacob.Multip;

import jacob.Multip.Commands.CustomCommand;
import jacob.Multip.Events.PlayerMessage;
import jacob.Multip.FlowerShoes.Flower;
import jacob.Multip.ItemManager.ItemManager;
import jacob.Multip.OnClick.CraftingTable;
import jacob.Multip.cloud.CloudBottle;
import jacob.Multip.honeycomb.HoneyComb;
import jacob.Multip.mininghelmet.MiningHelmet;
import jacob.Multip.honeycomb.SporeSac;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Multip extends JavaPlugin {

    @Override
    public void onEnable() {
        CustomCommand command = new CustomCommand();
        ItemManager.init();
        getCommand("heal").setExecutor(command);
        getCommand("feed").setExecutor(command);
        getCommand("flowerboots").setExecutor(command);
        getCommand("cloudbottle").setExecutor(command);
        getCommand("honeycomb").setExecutor(command);
        getCommand("mininghelmet").setExecutor(command);
        getCommand("sporesac").setExecutor(command);


        getServer().getPluginManager().registerEvents(new PlayerMessage(), this);
        getServer().getPluginManager().registerEvents(new CraftingTable(), this);
        getServer().getPluginManager().registerEvents(new Flower(), this);
        getServer().getPluginManager().registerEvents(new CloudBottle(), this);
        getServer().getPluginManager().registerEvents(new HoneyComb(), this);
        getServer().getPluginManager().registerEvents(new MiningHelmet(), this);
        getServer().getPluginManager().registerEvents(new SporeSac(), this);



        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Multip]: Plugin is enabled.");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Multip]: Plugin is disabled.");
    }
}

