package me.nottolerable.disablehitsounds;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;

public class SnowballFlightPlugin extends JavaPlugin implements Listener {

        @Override
        public void onEnable() {
                getLogger().info("SnowballFlightPlugin has been enabled!");
                getServer().getPluginManager().registerEvents(this, this);
        }

        @Override
        public void onDisable() {
                getLogger().info("SnowballFlightPlugin has been disabled!");
        }

        @EventHandler
        public void onProjectileHit(ProjectileHitEvent event) {
                Projectile projectile = event.getEntity();

                if (projectile instanceof Snowball) {
                        Snowball snowball = (Snowball) projectile;
                        if (snowball.getTicksLived() >= 8) {
                                snowball.remove();
                        }
                }
        }
}
