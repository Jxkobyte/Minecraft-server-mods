package jacob.Multip.honeycomb;

import jacob.Multip.ItemManager.ItemManager;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.bukkit.Bukkit.getPluginManager;
import static org.bukkit.Bukkit.getServer;

public class SporeSac implements Listener {

    Player player;
    private static BukkitTask task;

    @EventHandler
    public void onPlayerLoginEvent(PlayerJoinEvent event) {
        spawnSpore(20L);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        try {
            task.cancel();
        } catch (Exception e) {
            getServer().getConsoleSender().sendMessage(e.toString());
        }
    }



    public void spawnSpore(Long tickDelay) {
        task = Bukkit.getScheduler().runTaskTimer(getPluginManager().getPlugin("Jakobi"), new Runnable() {
            @Override
            public void run() {

                player = Bukkit.getPlayer("jakobirox");
                ItemStack sporesac = ItemManager.SporeSac;
                if (!player.getInventory().contains(sporesac)) return;

                World w = Bukkit.getWorld("world");
                assert w != null;
                int x = (int) getRandom(-3, 3);
                int y = (int) getRandom(-3, 3);
                int z = (int) getRandom(-3, 3);


                Material block = player.getWorld().getBlockAt(player.getLocation().add(x, y + 1, z)).getType();
                if (block == Material.AIR) {
                    Location loc2 = player.getLocation().add(x, y+1, z);
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.LIME, 2);
                    player.getWorld().spawnParticle(Particle.REDSTONE, loc2, 10, .2, .2, .2, .2, dustOptions);

                    Entity entity = w.spawnEntity(player.getLocation().add(x, y + 1, z), EntityType.BEE);
                    LivingEntity bee = (LivingEntity) entity;
                    Bee b = (Bee) bee;
                    b.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000, 1, false, false));
                    b.setBaby();
                    b.setAnger(5);
                    b.setAI(false);
                    b.setCollidable(false);
                    b.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000, 3, false, false));
                    b.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 1000, 3, false, false));

                    checkStinger(200L, b, true);
                    for (Long j = 1L; j < 200; j++) {
                        checkStinger(j, b, false);
                    }
                    for (Long j = 1L; j < 200; j+=2) {
                        particles(j, b);
                        attackMobs(b, player);
                    }



                }

            }
        }, 0, tickDelay); //20 Tick (1 Second) delay before run() is called
    }

    public void attackMobs(Bee bee, Player player) {
        List<Entity> mobs = player.getNearbyEntities(10,10, 10);
        for (Entity mob : mobs) {
            if (mobs != null && mob instanceof Monster) {
                LivingEntity livingMob = (LivingEntity) mob;
                bee.setAI(true);
                bee.setTarget(livingMob);
            }
        }
    }

    public void checkStinger(Long tickDelay, Bee bee, Boolean kill) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getPluginManager().getPlugin("Jakobi"), new Runnable() {
            @Override
            public void run() {
                //Bukkit.broadcastMessage("This message is shown after one second");
                if (bee.hasStung()||kill) bee.teleport(bee.getLocation().add(0, 400, 0));
            }
        }, tickDelay); //20 Tick (1 Second) delay before run() is called
    }

    public void particles(Long tickDelay, Bee bee) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getPluginManager().getPlugin("Jakobi"), new Runnable() {
            @Override
            public void run() {
                //Bukkit.broadcastMessage("This message is shown after one second");
                if (!bee.hasStung()) {
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.LIME, 2);
                    player.getWorld().spawnParticle(Particle.REDSTONE, bee.getLocation(), 2, .01, .01, .01, .01, dustOptions);
                }
            }
        }, tickDelay); //20 Tick (1 Second) delay before run() is called
    }

    double getRandom(double min, double max){
        double x = (Math.random()*((max-min)+1))+min;
        return x;
    }



}
