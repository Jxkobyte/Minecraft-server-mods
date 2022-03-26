package jacob.Multip.Commands;

import jacob.Multip.ItemManager.ItemManager;
import org.bukkit.Color;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use that command.");
            return true;
        }


        Player player = (Player) sender;

        // /heal
        if (cmd.getName().equalsIgnoreCase("heal")) {
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
            player.setHealth(maxHealth);
            player.sendMessage("<3");
            //player.openWorkbench(null, true);
            return true;
        }


        // /feed
        if (cmd.getName().equalsIgnoreCase("feed")) {
            player.setFoodLevel(20);
            player.setSaturation(20);
            player.sendMessage("yummy");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("flowerboots")) {
            player.getInventory().addItem(ItemManager.FlowerBoots);
        }
        if (cmd.getName().equalsIgnoreCase("cloudbottle")) {
            player.getInventory().addItem(ItemManager.CloudBottle);
        }
        if (cmd.getName().equalsIgnoreCase("honeycomb")) {
            player.getInventory().addItem(ItemManager.HoneyComb);
        }
        if (cmd.getName().equalsIgnoreCase("mininghelmet")) {
            player.getInventory().addItem(ItemManager.MiningHelmet);
        }
        if (cmd.getName().equalsIgnoreCase("sporesac")) {
            player.getInventory().addItem(ItemManager.SporeSac);
        }



        return true;
    }
}
