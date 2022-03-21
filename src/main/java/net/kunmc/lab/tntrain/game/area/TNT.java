package net.kunmc.lab.tntrain.game.area;

import net.kunmc.lab.tntrain.TNTRain;
import net.kunmc.lab.tntrain.game.Manager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.scheduler.BukkitRunnable;

public class TNT extends BukkitRunnable {

  private TNTPrimed tnt;
  private boolean isBombed;

  public TNT(Location location) {
    Manager.blockSetTask.offer(
        new BukkitRunnable() {
          @Override
          public void run() {
            tnt = (TNTPrimed) location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
          }
        }
    );

    runTaskTimerAsynchronously(
        TNTRain.plugin,
        0,
        1
    );
  }

  @Override
  public void run() {
    this.tnt.setFuseTicks(1000);

    Location location = tnt.getLocation();
    Material underBlockType = new Location(
        tnt.getWorld(),
        location.getBlockX(),
        location.getBlockY() - 3,
        location.getBlockZ())
        .getBlock()
        .getType();

    if (underBlockType == Material.AIR ||
        underBlockType == Material.CAVE_AIR) {
      return;
    }

    // 爆破
    this.tnt.setFuseTicks(0);
    this.isBombed = true;
    cancel();
  }

  public boolean isBombed() {
    return isBombed;
  }
}
