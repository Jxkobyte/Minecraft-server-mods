package jacob.Multip.mininghelmet;


import jacob.Multip.ItemManager.ItemManager;
import jacob.Multip.Multip;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Light;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getServer;

public class MiningHelmet implements Listener {

    private static Integer task;

    @EventHandler
    public static void onPlayerLoginEvent(PlayerJoinEvent event) {
        updateHelmet(event.getPlayer());
    }

    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent event) {
        try {
            Bukkit.getScheduler().cancelTask(task);
        } catch (Exception e) {

        }
    }


    public static void updateHelmet(Player player) {
        task = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(getPluginManager().getPlugin("Jakobi"), new Runnable() {
            @Override
            public void run() {
                if(!player.isOnline()) return;
                ItemStack miningHelmet = ItemManager.MiningHelmet;
                Location helmet = player.getLocation().add(0,1,0);
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().equals(miningHelmet)) {


                    if (helmet.getBlock().getType() == Material.AIR) {
                        helmet.getBlock().setType(Material.LIGHT);
//                        BlockData blockData = Material.LIGHT.createBlockData();
//                        Light light = (Light) blockData;
//                        light.setLevel(15);
//                        helmet.getBlock().setBlockData(light);

                    }
                }
                removeLight(helmet, player);
            }
        }, 0, 1L);


    }
    public static void removeLight(Location light, Player player) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getPluginManager().getPlugin("Jakobi"), new Runnable() {
            @Override
            public void run() {
                Location loc = player.getLocation().add(0,1,0);
                if (!(loc.getBlock().getLocation().equals(light.getBlock().getLocation()))) {
                    light.getBlock().setType(Material.AIR);
                }
            }
        }, 1L);
    }
}
