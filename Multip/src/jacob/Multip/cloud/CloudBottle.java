package jacob.Multip.cloud;

import jacob.Multip.ItemManager.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class CloudBottle implements Listener {

    private List<Player> doubleJump;

    public CloudBottle() {
        doubleJump = new ArrayList<>();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().setAllowFlight(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        doubleJump.remove(event.getPlayer());
    }

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        GameMode gameMode = player.getGameMode();

        if(gameMode == GameMode.CREATIVE || gameMode == GameMode.SPECTATOR || player.isFlying()) {
            return;
        }
        event.setCancelled(true);
        doubleJump.add(player);

        player.setAllowFlight(false);
        player.setFlying(false);

        // if the player has cloud, jump + do effect
        ItemStack cloud = ItemManager.CloudBottle;
        if ((player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().equals(cloud)) ||
        (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().equals(cloud))) {

            String command = "particle minecraft:cloud ~ ~.1 ~ 0.4 .05 .4 .01 100 normal";
            //player.getLocation().getWorld().spawnParticle(Particle.VILLAGER_HAPPY, player.getLocation(), 1);


            player.performCommand(command);

            //player.setVelocity(player.getLocation().getDirection().multiply(4));
            player.setVelocity(new Vector(player.getVelocity().getX()*1.3, 0.5D, player.getVelocity().getZ()*1.3));
        }
    }

    @EventHandler
    public void onHitGround(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        ItemStack cloud = ItemManager.CloudBottle;
        if ((player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().equals(cloud)) ||
                (player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().equals(cloud))) {
            if ((((Entity)player).isOnGround() || event.getTo().getBlock().isLiquid()) && doubleJump.remove(player)) {
                player.setAllowFlight(true);
            }
        }
    }
}
