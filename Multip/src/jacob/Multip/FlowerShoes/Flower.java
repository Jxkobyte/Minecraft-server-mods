package jacob.Multip.FlowerShoes;

import jacob.Multip.ItemManager.ItemManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class Flower implements Listener {
    @EventHandler
    public static void onPlayerWalk(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();

        Material block = player.getWorld().getBlockAt(x, y, z).getType();
        Material block2 = player.getWorld().getBlockAt(x, y-1, z).getType();

        if (block == Material.AIR && block2 == Material.GRASS_BLOCK) {
            ItemStack boots = ItemManager.FlowerBoots;
            if (player.getInventory().getBoots() != null && player.getInventory().getBoots().equals(boots)) {
                player.getLocation().getBlock().setType(flower());
            }
        }
    }

    public static Material flower() {
        Material[] available_cards = {
                Material.DANDELION,
                Material.POPPY,
                Material.BLUE_ORCHID,
                Material.ALLIUM,
                Material.AZURE_BLUET,
                Material.RED_TULIP,
                Material.PINK_TULIP,
                Material.ORANGE_TULIP,
                Material.OXEYE_DAISY,
                Material.CORNFLOWER,
                Material.LILY_OF_THE_VALLEY
                //Material.GRASS, Material.GRASS,Material.GRASS,Material.GRASS,Material.GRASS,Material.GRASS,Material.GRASS,Material.GRASS,Material.GRASS,Material.GRASS,Material.GRASS,
                //Material.GRASS, Material.GRASS,Material.GRASS,Material.GRASS
        };

        java.util.Random random = new java.util.Random();
        int random_computer_card = random.nextInt(available_cards.length);
        Material plant = (available_cards[random_computer_card]);
        return plant;
    }


}
