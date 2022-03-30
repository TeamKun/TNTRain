package net.kunmc.lab.tntrain.game;

import net.kunmc.lab.tntrain.TNTRain;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class Limit implements Listener {

  @EventHandler(ignoreCancelled = true)
  public void onPlayerInteract(PlayerInteractEvent event) {
    if (!TNTRain.config.doDeleteWater.value()) {
      return;
    }
    if (event.getMaterial() == Material.WATER_BUCKET
        || event.getMaterial() == Material.LAVA_BUCKET) {
      event.setCancelled(true);
    }
  }
}
