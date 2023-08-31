package me.nottolerable.disablehitsounds;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SnowballFlightPlugin extends JavaPlugin implements Listener {

        @Override
        public void onEnable() {
                getLogger().info("SnowballRemovalPlugin has been enabled!");
                getServer().getPluginManager().registerEvents(this, this);

                // Schedule a task to remove snowballs every 8 ticks (0.4 seconds)
                new BukkitRunnable() {
                        @Override
                        public void run() {
                                removeSnowballs();
                        }
                }.runTaskTimer(this, 0, 8);
        }

        @Override
        public void onDisable() {
                getLogger().info("SnowballRemovalPlugin has been disabled!");
        }

        @EventHandler
        public void onProjectileHit(ProjectileHitEvent event) {
                if (event.getEntityType() == EntityType.SNOWBALL) {
                        Snowball snowball = (Snowball) event.getEntity();
                        snowball.remove();
                }
        }

        private void removeSnowballs() {
                for (Snowball snowball : getServer().getWorlds().get(0).getEntitiesByClass(Snowball.class)) {
                        snowball.remove();
                }
        }
}
