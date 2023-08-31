package me.nottolerable.disablehitsounds;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class DisableHitSounds extends JavaPlugin implements Listener {

        @Override
        public void onEnable() {
                getLogger().info("SnowballRemovalPlugin has been enabled!");
                getServer().getPluginManager().registerEvents(this, this);
        }

        @Override
        public void onDisable() {
                getLogger().info("SnowballRemovalPlugin has been disabled!");
        }

        @EventHandler
        public void onSnowballThrow(ProjectileLaunchEvent event) {
                if (event.getEntityType() == EntityType.SNOWBALL) {
                        Snowball snowball = (Snowball) event.getEntity();
                        Location snowballLocation = snowball.getLocation();

                        if (snowballLocation.getY() > 120) {
                                // Schedule a task to remove the snowball every 2 ticks
                                new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                                snowball.remove();
                                        }
                                }.runTaskLater(this, 2);
                        }
                }
        }
}
