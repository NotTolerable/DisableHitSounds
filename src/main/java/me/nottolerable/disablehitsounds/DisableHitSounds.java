package me.nottolerable.disablehitsounds;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class DisableHitSounds extends JavaPlugin {

    @Override
    public void onEnable() {
        ProtocolLibrary.getProtocolManager().addPacketListener(
                new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.ENTITY_SOUND) {
                    @Override
                    public void onPacketSending(PacketEvent event) {
                        Player player = event.getPlayer();
                        ItemStack heldItem = player.getInventory().getItemInMainHand();

                        if (heldItem != null &&
                                (heldItem.getType() == Material.DIAMOND_SWORD && heldItem.getItemMeta().getDisplayName().equals("§bDagger")) ||
                                (heldItem.getType() == Material.GOLDEN_SWORD) ||
                                (heldItem.getType() == Material.NETHERITE_HOE)) {

                            EnumWrappers.SoundCategory soundCategory = event.getPacket().getSoundCategories().read(0);
                            String soundName = event.getPacket().getStrings().read(0);

                            if (soundCategory == EnumWrappers.SoundCategory.PLAYERS &&
                                    (soundName.equals("entity.player.attack.crit") ||
                                            soundName.equals("entity.player.attack.knockback") ||
                                            soundName.equals("entity.player.attack.nodamage") ||
                                            soundName.equals("entity.player.attack.strong") ||
                                            soundName.equals("entity.player.attack.sweep") ||
                                            soundName.equals("entity.player.attack.weak"))) {
                                event.setCancelled(true);
                            }
                        }
                    }
                });
    }

    @Override
    public void onDisable() {
        ProtocolLibrary.getProtocolManager().removePacketListeners(this);
    }

}
