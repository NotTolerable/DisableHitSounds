package me.nottolerable.disablehitsounds;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SwordShieldDisablePlugin extends JavaPlugin implements Listener {

        @Override
        public void onEnable() {
                getServer().getPluginManager().registerEvents(this, this);
        }

        @EventHandler
        public void onPlayerInteract(PlayerInteractEvent event) {
                ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

                // Check if the item is a sword
                if (item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.IRON_SWORD
                                || item.getType() == Material.GOLDEN_SWORD || item.getType() == Material.STONE_SWORD
                                || item.getType() == Material.WOODEN_SWORD) {

                        // Check if the player is using a shield
                        if (event.getPlayer().isBlocking()) {
                                event.setCancelled(true);
                        }
                }
        }
}
