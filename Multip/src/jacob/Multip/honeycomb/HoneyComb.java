package jacob.Multip.honeycomb;

import jacob.Multip.ItemManager.ItemManager;
import jacob.Multip.Multip;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import static org.bukkit.Bukkit.*;

public class HoneyComb implements Listener {

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Player player;
        LivingEntity mob;

        if (event.getEntity() instanceof Player) {
            player = ((Player) event.getEntity()).getPlayer();
        } else return;

        try {
            mob = (LivingEntity) event.getDamager();
        } catch (Exception e) {
            return;
        }



        ItemStack honeycomb = ItemManager.HoneyComb;
        if (player.getInventory().contains(honeycomb)) {
            //summon bees
            int numOfBees = (int) getRandom(1.0, 3.0);
            World w = Bukkit.getWorld("world");
            assert w != null;
            Entity entity;
            for (int i = 0; i < numOfBees; i++) {
                int x = (int)getRandom(-1,1);
                int y = (int)getRandom(-1,1);
                int z = (int)getRandom(-1,1);



                Material block = player.getWorld().getBlockAt(player.getLocation().add(x,y+1,z)).getType();
                if (block == Material.AIR) {
                    entity = w.spawnEntity(player.getLocation()
                                    .add(x,y+1,z),
                            EntityType.BEE);
                    LivingEntity bee = (LivingEntity) entity;
                    Bee b = (Bee) bee;
                    b.setBaby();
                    b.setAnger(5);
                    b.setTarget(mob);
                    b.setHasStung(true);
                    b.setCollidable(false);
                    //b.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000, 1, false, false));
                    b.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 1000, 1, false, false));
                    b.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000, 3, false, false));


                    /*
                    - potioneffect slowness while no mobs are detected
                    - remove slowness when mob is detected and setTarget(mob)
                    - if b.hasStung() play green smoke particle then despawn bee

                     */



//                    b.addPotionEffect(new PotionEffect(
//                            PotionEffectType.SPEED, 10000, 2, false, false));



                    activateBee(5L, b);
                    killBeeAfterDelay(150+5L, b);
                    for (Long j = 6L; j < 150; j++) {
                        checkStinger(j, b);
                    }
                }
            }
        }
    }


    public void killBeeAfterDelay(Long tickDelay, Bee bee) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getPluginManager().getPlugin("Jakobi"), new Runnable() {
            @Override
            public void run() {
                //Bukkit.broadcastMessage("This message is shown after one second");
                bee.damage(1000);
            }
        }, tickDelay); //20 Tick (1 Second) delay before run() is called
    }

    public void activateBee(Long tickDelay, Bee bee) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getPluginManager().getPlugin("Jakobi"), new Runnable() {
            @Override
            public void run() {
                //Bukkit.broadcastMessage("This message is shown after one second");
                bee.setHasStung(false);
                bee.addPotionEffect(new PotionEffect(
                        PotionEffectType.SPEED, 10000, 2, false, false));
            }
        }, tickDelay); //20 Tick (1 Second) delay before run() is called
    }

    public void checkStinger(Long tickDelay, Bee bee) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(getPluginManager().getPlugin("Jakobi"), new Runnable() {
            @Override
            public void run() {
                //Bukkit.broadcastMessage("This message is shown after one second");
                if (bee.hasStung()) bee.damage(1000);
            }
        }, tickDelay); //20 Tick (1 Second) delay before run() is called
    }




    static double getRandom(double min, double max){
        double x = (Math.random()*((max-min)+1))+min;
        return x;
    }

}
