package jacob.Multip.Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.BroadcastMessageEvent;

import java.net.http.WebSocket;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Sound.BLOCK_NOTE_BLOCK_COW_BELL;
import static org.bukkit.Sound.ENTITY_PLAYER_LEVELUP;

public class PlayerMessage implements Listener {

    @EventHandler
    public static void onPlayerMessage(AsyncPlayerChatEvent message) {
        //getServer().broadcastMessage(message.getMessage());
    }

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.AQUA + "Welcome to the server!");
        player.playSound(player.getLocation(), ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

    }




}
