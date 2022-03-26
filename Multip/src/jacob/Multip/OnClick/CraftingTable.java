package jacob.Multip.OnClick;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class CraftingTable implements Listener {

    @EventHandler
    public static void onItemClick(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        //check if item in main hand is table
        if ((event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) &&
                (p.getInventory().getItemInMainHand().getType() == Material.CRAFTING_TABLE)) {

            p.openWorkbench(null, true);

        }
    }




}
